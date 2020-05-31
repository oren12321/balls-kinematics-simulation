import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.Timer;


/*
 * The ball class contains physical information of 2D ball, its functionality
 * and its drawing functionality on the component, including its own independent timer.
 */
public class Ball {

	private Integer tagging;
	
	/*
	 * The ball position
	 */
	private Vector2 position;

	/*
	 * Ball radius
	 */
	private Double radius;

	/*
	 * The ball velocity
	 */
	private Vector2 velocity;

	/*
	 * The ball acceleration
	 */
	private Vector2 acceleration;

	/*
	 * The ball color
	 */
	private Color color;

	/*
	 * The ball mass
	 */
	private Double mass;
	
	/*
	 * The ball graphic device service, the component which its draws on
	 * and the timer responsible on the painting refreshment.
	 */
	private Component component;
	private Timer refresher;

	private boolean glow;
	
	
	
	/*
	 * Parameterized constructor
	 */
	public Ball(Integer tagging, Vector2 position, Double radius, Vector2 velocity, Vector2 acceleration, Color color, Double mass, Component component) {
		this.tagging = tagging;
				
		this.position = position;
		this.radius = radius;
		this.velocity = velocity;
		this.acceleration = acceleration;
		this.color = color;
		this.mass = mass;
		
		this.component = component;

		this.initializeRefresher();
		
		this.glow = false;
	}

	public Vector2 getPosition() {
		return this.position;
	}
	
	public Vector2 getVelocity() {
		return this.velocity;
	}
	
	public Vector2 getAcceleration() {
		return this.acceleration;
	}
	
	public Double getRadius() {
		return this.radius;
	}
	
	public Double getMass() {
		return this.mass;
	}
	
	public void setGlow(boolean glow) {
		this.glow = glow;
	}
	
	/*
	 * Initiate the timer according to the ball velocity
	 */
	public void initializeRefresher() {
		this.refresher = new Timer(1, new BallRefresher(this));
	}

	/*
	 * Start moving the ball on the component
	 */
	public void move() {
		this.refresher.start();
	}

	/*
	 * Stop ball movement
	 */
	public void stop() {
		this.refresher.stop();
	}
	
	/*
	 * Draw the ball on the component
	 */
	public void draw(Graphics g) {
		// Take component graphic device
		Graphics2D g2d = (Graphics2D)g;

		// Set ball painting color
		g2d.setColor(this.color);

		Integer oX = new Double(this.position.x() - this.radius).intValue();
		Integer oY = new Double(this.position.y() - this.radius).intValue();
		Integer oW = new Double(2 * this.radius).intValue();
		Integer oH = new Double(2 * this.radius).intValue();
		
		// Draw the ball
		g2d.fillOval(oX, oY, oW, oH);
		
		if(this.glow) {
			g2d.setColor(Color.YELLOW);
			g2d.setStroke(new BasicStroke(5));
			g2d.drawOval(oX, oY, oW, oH);
		}
		
		// Mark the ball with its tagging
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Arial", Font.BOLD, 15));
		g2d.drawString(this.tagging.toString(), this.position.x().intValue(), this.position.y().intValue());
	}
	
	public void repaint() {
		this.component.repaint();
	}
	
	/*
	 * Detect collision with other ball
	 */
	public boolean detectCollision(Ball ball) {
		return this.getPosition().distance(ball.getPosition()) <= this.getRadius() + ball.getRadius();
	}
	
	/*
	 * Handle collision with other ball (resolve and update balls parameters)
	 */
	public void resolveCollision(Ball ball) {
		Double m1 = this.mass;
		Double m2 = ball.mass;
		
		Double v1X = 
				(this.getVelocity().x() * (m1 - m2) + 2 * m2 * ball.getVelocity().x()) /
				(m1 + m2);
		Double v1Y = 
				(this.getVelocity().y() * (m1 - m2) + 2 * m2 * ball.getVelocity().y()) /
				(m1 + m2);
		
		Double v2X = 
				(ball.getVelocity().x() * (m2 - m1) + 2 * m1 * this.getVelocity().x()) /
				(m1 + m2);
		Double v2Y = 
				(ball.getVelocity().y() * (m2 - m1) + 2 * m1 * this.getVelocity().y()) /
				(m1 + m2);
		
		this.getVelocity().set(v1X, v1Y);
		ball.getVelocity().set(v2X, v2Y);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(String.format("[ID:%2d;", this.tagging));
		builder.append(String.format("X(%6s,%6s);", 
				this.position.x().toString().substring(0, 6), 
				this.position.y().toString().substring(0, 6)));
		builder.append(String.format("V(%6s,%6s);", 
				this.velocity.x().toString().substring(0, 6), 
				this.velocity.y().toString().substring(0, 6)));
		builder.append(String.format("A(%6s,%6s);", 
				this.acceleration.x().toString().substring(0, 6), 
				this.acceleration.y().toString().substring(0, 6)));
		builder.append(String.format("M:%3f]", this.mass));
		
		return builder.toString();
	}
}
