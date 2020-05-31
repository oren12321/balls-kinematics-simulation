import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class BallsPlayground extends JPanel {
	private static final long serialVersionUID = -7529336433213962317L;

	/*
	 * List of balls on the component
	 */
	private List<Ball> balls;
	
	/*
	 * List of all balls Information
	 */
	private JList<String> ballsInfoList;
	private DefaultListModel<String> model;
	
	/*
	 *Playground default constructor
	 */
	public BallsPlayground() {
		this.balls = new ArrayList<Ball>();
		this.handleBallsInfoList();
	}
	
	public JList<String> getBallsInfoList() {
		return this.ballsInfoList;
	}
	
	private void handleBallsInfoList() {
		this.model = new DefaultListModel<String>();
		this.ballsInfoList = new JList<String>(this.model);
		this.ballsInfoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.ballsInfoList.setFont(new Font("Arial", Font.PLAIN, 15));
		this.ballsInfoList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				
				Integer selectedIndex = ballsInfoList.getSelectedIndex();
				
				if(selectedIndex >= 0 && balls.size() > 0) {
				
					for(Integer i = 0; i < balls.size(); i++) {
						balls.get(i).setGlow(false);
					}
					
					balls.get(selectedIndex).setGlow(true);
				}
			}
		});
	}
	
	/*
	 * Add new random ball to the playground and check
	 * that there is no collision with the balls that already exists
	 */
	public void addBall() {
		Ball ball = null;
		boolean collision = false;
		do {
			collision = false;
			ball = BallsFactory.createBall(this.balls.size() + 1, this);
			for(Integer i = 0; i < this.balls.size() && !collision; i++) {
				collision = this.balls.get(i).detectCollision(ball);
			}
		} while(collision);
		ball.move();
		this.balls.add(ball);
		
		this.model.addElement(ball.toString());
	}
	
	/*
	 * Remove the last ball that added to playground
	 */
	public void removeLastBall() {
		if(this.balls.size() > 0) {
			this.balls.get(this.balls.size() - 1).stop();
			this.balls.remove(this.balls.size() - 1);
			
			DefaultListModel<String> l = (DefaultListModel<String>)this.ballsInfoList.getModel();
			l.remove(l.size() - 1);
		}
		this.repaint();
	}
	
	/*
	 * Update all balls motion on the playground according to the physical data
	 */
	private void updateBallsMotion() {
		this.handleBallsAcceleration();
		this.moveAllBalls();
		this.handleCollisions();
	}
	
	/*
	 * Update position of all balls
	 */
	private void moveAllBalls() {
		for(Ball b : this.balls) {
			b.getPosition().move(
					b.getVelocity().x(), 
					b.getVelocity().y());
		}
	}
	
	/*
	 * Update balls velocity according to the accelerations
	 */
	private void handleBallsAcceleration() {
		// TODO : ACCELERATION NEED TO BE STABILIZED
//		for(Ball b : this.balls) {
//			b.getVelocity().moveXY(
//					b.getAcceleration().getX() * ((b.getVelocity().getX() < 0) ? -1 : 1), 
//					b.getAcceleration().getY() * ((b.getVelocity().getY() < 0) ? -1 : 1));
//		}
	}
	
	/*
	 * Handle balls collision in general
	 */
	private void handleCollisions() {
		this.handleCollisionWithWall();
		this.handleCollisionWithBalls();
	}
	
	/*
	 * Handle collision of the balls with the "walls" of the component
	 */
	private void handleCollisionWithWall() {
		for(Ball b : this.balls) {
			if(b.getPosition().x() + b.getRadius() >= this.getWidth() ||
					b.getPosition().x() - b.getRadius() <= 0) {
				b.getVelocity().x(
						-b.getVelocity().x());
			}
			if(b.getPosition().y() + b.getRadius() >= this.getHeight() ||
					b.getPosition().y() - b.getRadius() <= 0) {
				b.getVelocity().y(
						-b.getVelocity().y());
			}
		}
	}
	
	/*
	 * Handle collisions between balls in the playground
	 */
	private void handleCollisionWithBalls() {
		for(Integer i = 0; i < this.balls.size(); i++) {
			for(Integer j = i + 1; j < this.balls.size(); j++) {
				if(this.balls.get(i).detectCollision(this.balls.get(j))) {
					this.balls.get(i).resolveCollision(this.balls.get(j));
				}
			}
		}
		
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		// Update balls physical data
		this.updateBallsMotion();
		
		// Redraw the balls and update the info list
		for(Integer i = 0; i < this.balls.size(); i++) {
			this.balls.get(i).draw(g);
			this.model.set(i, this.balls.get(i).toString());
		}
	}
	
	/*
	 * Check if there are balls in the playground
	 */
	public boolean thereAreBallsInPlayground() {
		return this.balls.size() > 0;
	}
}
