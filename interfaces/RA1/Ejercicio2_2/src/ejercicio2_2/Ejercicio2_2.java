package ejercicio2_2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ButtonGroup;

public class Ejercicio2_2 extends JFrame {
	Double[] centimo={0.0,0.10,0.20,0.30,0.40,0.50,0.60,0.70,0.80,0.90};
	Double[] euro= {0.0,1.0,2.0,3.0,4.0};

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup grupoBtn = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ejercicio2_2 frame = new Ejercicio2_2();
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
	public Ejercicio2_2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JRadioButton radioA = new JRadioButton("bebida A");
		grupoBtn.add(radioA);
		radioA.setBounds(25, 37, 149, 23);
		contentPane.add(radioA);

		JRadioButton radioB = new JRadioButton("bebida B");
		grupoBtn.add(radioB);
		radioB.setBounds(25, 72, 149, 23);
		contentPane.add(radioB);

		JRadioButton radioC = new JRadioButton("bebida C");
		grupoBtn.add(radioC);
		radioC.setBounds(25, 107, 149, 23);
		contentPane.add(radioC);

		JComboBox euros = new JComboBox(euro);
		euros.setBounds(445, 36, 60, 24);
		contentPane.add(euros);

		JComboBox centimos = new JComboBox(centimo);
		centimos.setBounds(445, 71, 60, 24);
		contentPane.add(centimos);

		JLabel lblNewLabel = new JLabel("Euros");
		lblNewLabel.setBounds(357, 41, 70, 15);
		contentPane.add(lblNewLabel);

		JLabel lblCentimos = new JLabel("Centimos");
		lblCentimos.setBounds(357, 76, 70, 15);
		contentPane.add(lblCentimos);

		JButton extraer = new JButton("Extraer");
		extraer.setBounds(25, 170, 117, 25);
		contentPane.add(extraer);

		JLabel resultado = new JLabel("Resultado");
		resultado.setBounds(160, 175, 345, 15);
		contentPane.add(resultado);



		extraer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Double ultimo, primero, segundo ,ingresado=0.0;
				primero=(Double) euros.getSelectedItem();
				segundo=(Double) centimos.getSelectedItem();
				String casteo="";
				
				DecimalFormat formato= new DecimalFormat("#.00");

				ingresado=primero+segundo;
				grupoBtn.getSelection();
				
				if(radioA.isSelected()) {
					if((ultimo=ingresado - 0.8)<0) {
						formato.format(ultimo*=(-1));
						resultado.setText("cantidad insuficiente, faltan: "+String.format("%.2f", ultimo)+" euros.");
					}else if ((ultimo=ingresado - 0.8)>0){
						resultado.setText("Cambio: "+String.format("%.2f", ultimo));
					}else if ((ultimo=ingresado - 0.8)==0) {
						resultado.setText("ingreso justo correcto.");
					}
				}else if (radioB.isSelected()) {
					if((ultimo=ingresado - 1.2)<0) {
						formato.format(ultimo*=(-1));
						resultado.setText("cantidad insuficiente. Faltan: "+String.format("%.2f", ultimo)+" euros.");
					}else if ((ultimo=ingresado - 1.2)>0){
						resultado.setText("Cambio: "+String.format("%.2f", ultimo));
					}else if ((ultimo=ingresado - 1.2)==0) {
						resultado.setText("ingreso justo correcto.");
					}
				}else if (radioC.isSelected()) {
					if((ultimo=ingresado - 3.1)<0) {
						formato.format(ultimo*=(-1));
						resultado.setText("cantidad insuficiente, faltan: "+String.format("%.2f", ultimo)+" euros.");
					}else if ((ultimo=ingresado - 3.1)>0){
						resultado.setText("Cambio: "+String.format("%.2f", ultimo));
					}else if ((ultimo=ingresado - 3.1)==0) {
						resultado.setText("ingreso justo correcto.");
					}
				}

			}
		});


	}
}
