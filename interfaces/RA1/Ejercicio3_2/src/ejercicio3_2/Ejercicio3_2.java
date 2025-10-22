package ejercicio3_2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Ejercicio3_2 extends JFrame {

	public static String medir(int a, int b, int c) {
		int valor=0;
		String total="";
		valor= a+b+c;
		
		if(valor<=100) {
			total="Caudal bajo: "+ valor + "m³/s";
		}else if (valor>100&&valor<=200) {
			total="Caudal medio: "+ valor + "m³/s";
		}else if(valor>200&&valor<=300){
			total="Caudal alto: "+ valor + "m³/s";
		}
		
		return total;
	}
	

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ejercicio3_2 frame = new Ejercicio3_2();
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
	public Ejercicio3_2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSpinner spinner1 = new JSpinner();
		spinner1.setBounds(12, 40, 60, 20);
		contentPane.add(spinner1);
		
		JSpinner spinner2 = new JSpinner();
		spinner2.setBounds(12, 93, 60, 20);
		contentPane.add(spinner2);
		
		JSpinner spinner3 = new JSpinner();
		spinner3.setBounds(12, 153, 60, 20);
		contentPane.add(spinner3);
		
		JLabel lblCompuerta = new JLabel("Compuerta 1");
		lblCompuerta.setBounds(90, 42, 103, 15);
		contentPane.add(lblCompuerta);
		
		JLabel lblCompuerta_3 = new JLabel("Compuerta 2");
		lblCompuerta_3.setBounds(90, 95, 103, 15);
		contentPane.add(lblCompuerta_3);
		
		JLabel lblCompuerta_1 = new JLabel("Compuerta 3");
		lblCompuerta_1.setBounds(90, 155, 103, 15);
		contentPane.add(lblCompuerta_1);
		
		JButton btnCaudal = new JButton("Actualizar Caudal.");
		btnCaudal.setBounds(12, 200, 179, 25);
		contentPane.add(btnCaudal);
		
		JLabel Caudal = new JLabel("");
		Caudal.setBounds(205, 210, 231, 15);
		contentPane.add(Caudal);

		btnCaudal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				Caudal.setText(medir((Integer)spinner1.getValue(), (Integer)spinner2.getValue(), (Integer)spinner3.getValue()));;				
			}
		});
	}
}
