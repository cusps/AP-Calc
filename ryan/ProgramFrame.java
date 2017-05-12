import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ProgramFrame extends JFrame {

	private JPanel contentPane;
	private JTextField quotientFXTextField;
	private JTextField quotientGXTextField;
	private JTextField productFXTextField;
	private JTextField productGXTextField;
	private JTextField powerFXTextField;
	
	private DerivationSolver solver;
	
	private String fx, gx;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProgramFrame frame = new ProgramFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProgramFrame() {
		
		solver = new DerivationSolver();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 711, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDerivativeCalculator = new JLabel("Derivative Calculator");
		lblDerivativeCalculator.setHorizontalAlignment(SwingConstants.CENTER);
		lblDerivativeCalculator.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDerivativeCalculator.setBounds(10, 11, 679, 14);
		contentPane.add(lblDerivativeCalculator);

		JButton calculateButton = new JButton("Calculate");
		calculateButton.setBounds(308, 226, 89, 23);
		contentPane.add(calculateButton);

		JRadioButton productSelector = new JRadioButton("Product Rule");
		productSelector.setHorizontalAlignment(SwingConstants.CENTER);
		productSelector.setBounds(10, 65, 679, 23);
		contentPane.add(productSelector);

		JRadioButton powerSelector = new JRadioButton("Power Rule");
		powerSelector.setHorizontalAlignment(SwingConstants.CENTER);
		powerSelector.setBounds(10, 40, 679, 23);
		contentPane.add(powerSelector);

		JRadioButton quotientSelector = new JRadioButton("Quotient Rule");
		quotientSelector.setHorizontalAlignment(SwingConstants.CENTER);
		quotientSelector.setBounds(10, 90, 679, 23);
		contentPane.add(quotientSelector);

		JPanel equationPanel = new JPanel();
		equationPanel.setBounds(10, 120, 675, 95);
		contentPane.add(equationPanel);
		equationPanel.setLayout(null);
		equationPanel.setVisible(false);

		quotientFXTextField = new JTextField();
		quotientFXTextField.setBounds(237, 11, 225, 20);
		equationPanel.add(quotientFXTextField);
		quotientFXTextField.setColumns(10);

		JLabel quotientFXLabel = new JLabel("f(x) = ");
		quotientFXLabel.setHorizontalAlignment(SwingConstants.CENTER);
		quotientFXLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		quotientFXLabel.setBounds(181, 10, 46, 19);
		equationPanel.add(quotientFXLabel);

		quotientGXTextField = new JTextField();
		quotientGXTextField.setBounds(237, 52, 225, 20);
		equationPanel.add(quotientGXTextField);
		quotientGXTextField.setColumns(10);

		JLabel quotientGXLabel = new JLabel("g(x) =");
		quotientGXLabel.setHorizontalAlignment(SwingConstants.CENTER);
		quotientGXLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		quotientGXLabel.setBounds(181, 52, 46, 17);
		equationPanel.add(quotientGXLabel);
		
		JLabel productFXLabel = new JLabel("f(x) = ");
		productFXLabel.setHorizontalAlignment(SwingConstants.CENTER);
		productFXLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		productFXLabel.setBounds(22, 37, 46, 19);
		equationPanel.add(productFXLabel);
		
		JLabel productGXLabel = new JLabel("g(x) =");
		productGXLabel.setHorizontalAlignment(SwingConstants.CENTER);
		productGXLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		productGXLabel.setBounds(360, 38, 46, 17);
		equationPanel.add(productGXLabel);
		
		productFXTextField = new JTextField();
		productFXTextField.setBounds(78, 38, 225, 20);
		equationPanel.add(productFXTextField);
		productFXTextField.setColumns(10);
		
		productGXTextField = new JTextField();
		productGXTextField.setColumns(10);
		productGXTextField.setBounds(420, 38, 225, 20);
		equationPanel.add(productGXTextField);
		
		powerFXTextField = new JTextField();
		powerFXTextField.setBounds(225, 35, 225, 20);
		equationPanel.add(powerFXTextField);
		powerFXTextField.setColumns(10);
		
		JLabel powerFXLabel = new JLabel("f(x) = ");
		powerFXLabel.setHorizontalAlignment(SwingConstants.CENTER);
		powerFXLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		powerFXLabel.setBounds(160, 37, 46, 14);
		equationPanel.add(powerFXLabel);
		
		// Start RadioButton Block
		
		powerSelector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equationPanel.setVisible(true);
					
				// Deselect other choices
				productSelector.setSelected(false);
				powerSelector.setSelected(true);
				quotientSelector.setSelected(false);
				
				// Turn off product rule graphics
				productFXTextField.setVisible(false);
				productFXLabel.setVisible(false);
				productGXTextField.setVisible(false);
				productGXLabel.setVisible(false);	
				
				// Turn off quotient rule graphics
				quotientFXTextField.setVisible(false);
				quotientFXLabel.setVisible(false);
				quotientGXTextField.setVisible(false);
				quotientGXLabel.setVisible(false);
				
				// Turn on power rule graphics
				powerFXLabel.setVisible(true);
				powerFXTextField.setVisible(true);
			}
		});
		
		productSelector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equationPanel.setVisible(true);
				
				productSelector.setSelected(true);
				powerSelector.setSelected(false);
				quotientSelector.setSelected(false);
				
				// Turn off power rule graphics
				powerFXLabel.setVisible(false);
				powerFXTextField.setVisible(false);
				
				// Turn off quotient rule graphics
				quotientFXTextField.setVisible(false);
				quotientFXLabel.setVisible(false);
				quotientGXTextField.setVisible(false);
				quotientGXLabel.setVisible(false);
				
				// Turn on product rule graphics
				productFXTextField.setVisible(true);
				productFXLabel.setVisible(true);
				productGXTextField.setVisible(true);
				productGXLabel.setVisible(true);
				
			}
		});

		quotientSelector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equationPanel.setVisible(true);
				
				productSelector.setSelected(false);
				powerSelector.setSelected(false);
				quotientSelector.setSelected(true);
				
				// Turn off power rule graphics
				powerFXLabel.setVisible(false);
				powerFXTextField.setVisible(false);
				
				// Turn off product rule graphics
				productFXTextField.setVisible(false);
				productFXLabel.setVisible(false);
				productGXTextField.setVisible(false);
				productGXLabel.setVisible(false);
				
				// Turn on quotient rule graphics
				quotientFXTextField.setVisible(true);
				quotientFXLabel.setVisible(true);
				quotientGXTextField.setVisible(true);
				quotientGXLabel.setVisible(true);

			}
		});

		// End RadioButton Block
		
		// Calculate Button Action Listener
		
		calculateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				solver.purgeOldData();
				
				if(powerSelector.isSelected())
				{
					fx = powerFXTextField.getText();
					solver.powerRule(fx);
				}	
				if(productSelector.isSelected())
				{	
					fx = productFXTextField.getText();
					gx = productGXTextField.getText();
					solver.productRule(fx, gx);
				}	
				else
				{
					fx = quotientFXTextField.getText();
					gx = quotientGXTextField.getText();
					solver.quotientRule(fx, gx);
				}
					
						
			}
		});
		
		
	}

}
