import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BallRefresher implements ActionListener {

	/*
	 * Ball member to refresh on the component
	 */
	private Ball ball;

	/*
	 * Parameterized constructor
	 */
	public BallRefresher(Ball ball) {
		this.ball = ball;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Redraw the ball after its data updated
		this.ball.repaint();
	}

}
