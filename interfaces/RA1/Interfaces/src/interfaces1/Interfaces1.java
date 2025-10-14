package interfaces1;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Interfaces1 extends JFrame {

	public static final String pass= "Aloha1234";


	public static Boolean checkear(String s) {

		if (s.equals(pass)) {
			return true;
		}else {
			return false;
		}
	}

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaces1 frame = new Interfaces1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});		
	}




	public Interfaces1() {


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblIntroduceNombreDe = new JLabel("Introduce nombre de usuario: ");
		lblIntroduceNombreDe.setBounds(12, 33, 215, 15);
		contentPane.add(lblIntroduceNombreDe);

		textField = new JTextField();
		textField.setBounds(229, 31, 195, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Ingrese clave: ");
		lblNewLabel.setBounds(124, 88, 120, 15);
		contentPane.add(lblNewLabel);

		textField_1 = new JTextField();
		textField_1.setBounds(229, 86, 195, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JButton aceptar = new JButton("Aceptar");
		aceptar.setBounds(47, 155, 127, 45);
		contentPane.add(aceptar);

		JButton cancelar = new JButton("Cancelar");
		cancelar.setBounds(254, 155, 127, 45);
		contentPane.add(cancelar);

		JLabel correcto = new JLabel("CORRECTO");
		correcto.setBounds(182, 215, 97, 38);
		correcto.setVisible(false);
		correcto.setForeground(Color.GREEN);
		contentPane.add(correcto);

		JLabel incorrecto = new JLabel("ERROR");
		incorrecto.setBounds(182, 212, 97, 38);
		incorrecto.setVisible(false);
		incorrecto.setForeground(Color.RED);
		contentPane.add(incorrecto);

		aceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkear(textField_1.getText())) {
					correcto.setVisible(true);
					incorrecto.setVisible(false);
				}else {
					incorrecto.setVisible(true);
					correcto.setVisible(false);
				}
			}
		});


		cancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				textField.setText("");
				textField_1.setText("");
				incorrecto.setVisible(false);
				correcto.setVisible(false);

			}
		});




	}
}
