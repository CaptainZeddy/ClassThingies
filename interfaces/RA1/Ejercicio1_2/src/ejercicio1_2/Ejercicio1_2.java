package ejercicio1_2;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;




public class Ejercicio1_2 extends JFrame {


	public static String direction(int actual, int presionado) {
		String resultado="";

		if (actual<presionado) {
			resultado="sube";

		}else if(actual>presionado) {
			resultado="baja";

		}else if(actual==presionado) {
			resultado="Piso actual.";
		}
		return resultado;
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
					Ejercicio1_2 frame = new Ejercicio1_2();
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
	public Ejercicio1_2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 457, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btn1 = new JButton("1");
		btn1.setBounds(26, 261, 85, 61);
		contentPane.add(btn1);

		JButton btn2 = new JButton("2");
		btn2.setBounds(26, 188, 85, 61);
		contentPane.add(btn2);

		JButton btn3 = new JButton("3");
		btn3.setBounds(26, 115, 85, 61);
		contentPane.add(btn3);

		JButton btn4 = new JButton("4");
		btn4.setBounds(26, 42, 85, 61);
		contentPane.add(btn4);

		JLabel lblNewLabel = new JLabel("Piso");
		lblNewLabel.setBounds(181, 65, 70, 15);
		contentPane.add(lblNewLabel);

		JLabel piso = new JLabel("1");
		piso.setBounds(263, 65, 70, 15);
		contentPane.add(piso);

		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(181, 115, 70, 15);
		contentPane.add(lblDireccion);

		JLabel direccion = new JLabel("baja");
		direccion.setBounds(263, 115, 102, 15);
		contentPane.add(direccion);

		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				direccion.setText(direction(Integer.parseInt(piso.getText()),Integer.parseInt(btn1.getText())));
				piso.setText("1");
			}
		});
		
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				direccion.setText(direction(Integer.parseInt(piso.getText()),Integer.parseInt(btn2.getText())));
				piso.setText("2");
			}
		});
		
		btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				direccion.setText(direction(Integer.parseInt(piso.getText()),Integer.parseInt(btn3.getText())));
				piso.setText("3");
			}
		});
		
		btn4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				direccion.setText(direction(Integer.parseInt(piso.getText()),Integer.parseInt(btn4.getText())));
				piso.setText("4");
			}
		});
	}
}
