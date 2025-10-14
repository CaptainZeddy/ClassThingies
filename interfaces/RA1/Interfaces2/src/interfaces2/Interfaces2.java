package interfaces2;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interfaces2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtContraseña;
	private JTextField txtReContraseña;
	private JTextField Resultado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaces2 frame = new Interfaces2();
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
	public Interfaces2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel contraseña1 = new JLabel("Contraseña");
		contraseña1.setBounds(176, 24, 88, 14);
		contraseña1.setForeground(Color.BLUE);
		contentPane.add(contraseña1);

		JLabel contraseña2 = new JLabel("Repita contraseña");
		contraseña2.setBounds(158, 91, 106, 14);
		contraseña2.setForeground(Color.BLUE);
		contentPane.add(contraseña2);

		txtContraseña = new JTextField();
		txtContraseña.setBounds(103, 59, 208, 20);
		contentPane.add(txtContraseña);
		txtContraseña.setColumns(10);

		txtReContraseña = new JTextField();
		txtReContraseña.setColumns(10);
		txtReContraseña.setBounds(103, 116, 208, 20);
		contentPane.add(txtReContraseña);

		Resultado = new JTextField();
		Resultado.setColumns(10);
		Resultado.setBounds(103, 197, 208, 53);
		contentPane.add(Resultado);

		JButton Contact = new JButton("Añadir contacto");
		Contact.setBounds(103, 147, 208, 23);
		contentPane.add(Contact);

		JLabel endRes = new JLabel("");
		endRes.setBounds(169, 181, 142, 14);
		endRes.setVisible(false);
		contentPane.add(endRes);


		Contact.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


				if(txtContraseña.getText().equals(txtReContraseña.getText())&&txtContraseña.getText().equals("")){
					endRes.setText("ERROR");
					endRes.setForeground(Color.RED);
					endRes.setVisible(true);
					Resultado.setText("El campo esta vacío.");
				}else if(txtContraseña.getText().equals(txtReContraseña.getText())&&txtContraseña.getText().length()<8) {
					endRes.setText("ERROR");
					endRes.setForeground(Color.RED);
					endRes.setVisible(true);
					Resultado.setText("La contraseña debe tener 8 caracteres.");
				}else if(!txtContraseña.getText().equals(txtReContraseña.getText())) {
					endRes.setText("ERROR");
					endRes.setForeground(Color.RED);
					endRes.setVisible(true);
					Resultado.setText("Las contraseñas no coinciden.");	
				}else if (txtContraseña.getText().equals(txtReContraseña.getText())) {
					endRes.setText("CORECTO");
					endRes.setForeground(Color.GREEN);
					endRes.setVisible(true);
					Resultado.setText("");
				}

			}
		});;

	}
}
