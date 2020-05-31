import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class ApplicationWindow extends JFrame {
	private static final long serialVersionUID = -3356471150162006116L;
	
	private BallsPlayground playground;
	
	private JPanel panelBtns;
	
	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnClear;
	
	
	private JScrollPane scrollPane;
	
	public ApplicationWindow() {
		
		this.setLayout(new BorderLayout());
		
		this.playground = new BallsPlayground();
		this.playground.setBackground(Color.BLACK);
		
		this.scrollPane = new JScrollPane(this.playground.getBallsInfoList());
		
		panelBtns = new JPanel();
		
		this.handleAddBtn();
		this.handleRemoveBtn();
		this.handleClearBtn();
		
		this.add(this.playground, BorderLayout.CENTER);
		this.add(panelBtns, BorderLayout.SOUTH);
		this.add(this.scrollPane, BorderLayout.NORTH);
		
		this.setSize(640, 480);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void handleAddBtn() {
		btnAdd = new JButton("Add Ball");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				playground.addBall();
			}
		});
		panelBtns.add(btnAdd);
	}
	
	private void handleRemoveBtn() {
		btnRemove = new JButton("Remove Last Ball");
		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(playground.thereAreBallsInPlayground()) {
					playground.removeLastBall();
				}
			}
		});
		panelBtns.add(btnRemove);
	}
	
	private void handleClearBtn() {
		btnClear = new JButton("Clear Playground");
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				while(playground.thereAreBallsInPlayground()) {
					playground.removeLastBall();
				}
			}
		});
		panelBtns.add(btnClear);
	}
	
	public static void main(String[] args) {
		new ApplicationWindow();
	}

}
