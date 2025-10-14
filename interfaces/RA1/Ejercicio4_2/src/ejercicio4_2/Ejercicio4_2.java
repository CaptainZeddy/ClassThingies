package ejercicio4_2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class Ejercicio4_2 extends JFrame {

	
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ejercicio4_2 frame = new Ejercicio4_2();
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
	public Ejercicio4_2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDineroAExtraer = new JLabel("Dinero a extraer");
		lblDineroAExtraer.setBounds(29, 34, 123, 15);
		contentPane.add(lblDineroAExtraer);
		
		JLabel lblTipoDeCuenta = new JLabel("Tipo de cuenta");
		lblTipoDeCuenta.setBounds(241, 34, 123, 15);
		contentPane.add(lblTipoDeCuenta);
		
		JComboBox comboCantidad = new JComboBox();
		comboCantidad.setModel(new DefaultComboBoxModel(new String[] {"0", "50", "100", "150", "200", "250", "300", "350", "400", "450", "500"}));
		comboCantidad.setBounds(29, 73, 123, 24);
		contentPane.add(comboCantidad);
		
		JComboBox comboCantidad_1 = new JComboBox();
		comboCantidad_1.setModel(new DefaultComboBoxModel(new String[] {"Cuenta de ahorro", "Cuenta corriente"}));
		comboCantidad_1.setBounds(241, 73, 158, 24);
		contentPane.add(comboCantidad_1);
		
		JButton btnExtraer = new JButton("Extraer");
		btnExtraer.setBounds(35, 164, 117, 25);
		contentPane.add(btnExtraer);
		
		JLabel Cantidad = new JLabel("3000");
		Cantidad.setBounds(170, 121, 139, 15);
		contentPane.add(Cantidad);
		
		JLabel Resultado = new JLabel("");
		Resultado.setBounds(170, 169, 70, 15);
		contentPane.add(Resultado);
		
		JLabel lblNewLabel = new JLabel("Total actual:");
		lblNewLabel.setBounds(62, 121, 96, 15);
		contentPane.add(lblNewLabel);
		
		btnExtra

	}
}
