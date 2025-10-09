import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;

import java.awt.Checkbox;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.UIManager;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.JScrollBar;
import java.awt.SystemColor;

public class Proyecto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField profesion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Proyecto frame = new Proyecto();
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
	public Proyecto() {
		setBackground(UIManager.getColor("Button.toolBarBorderBackground"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 449, 572);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("CheckBox.disabledText"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Profesión");
		lblNewLabel.setBounds(29, 12, 81, 15);
		contentPane.add(lblNewLabel);

		profesion = new JTextField();
		profesion.setBounds(121, 10, 114, 19);
		contentPane.add(profesion);
		profesion.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nº Hermanos: ");
		lblNewLabel_1.setBounds(29, 58, 107, 15);
		contentPane.add(lblNewLabel_1);

		JSpinner spinner = new JSpinner();
		spinner.setBounds(154, 56, 41, 20);
		contentPane.add(spinner);

		JLabel lblNewLabel_2 = new JLabel("Edad:");
		lblNewLabel_2.setBounds(223, 58, 46, 15);
		contentPane.add(lblNewLabel_2);

		JComboBox comboBoxEdad = new JComboBox();
		comboBoxEdad.setModel(
				new DefaultComboBoxModel(new String[] { "", "Entre 18 y 30", "Entre 31 y 40", "Entre 41 y 60" }));
		comboBoxEdad.setBounds(270, 53, 131, 24);
		contentPane.add(comboBoxEdad);
		ButtonGroup bg = new ButtonGroup();
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("CheckBox.disabledText"));
		panel.setBorder(
				new TitledBorder(null, "Sexo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(29, 100, 228, 49);
		contentPane.add(panel);
		panel.setLayout(null);

		JRadioButton rdbtnHombre = new JRadioButton("Hombre");
		rdbtnHombre.setBackground(UIManager.getColor("CheckBox.disabledText"));
		rdbtnHombre.setBounds(8, 18, 80, 23);
		bg.add(rdbtnHombre);
		panel.add(rdbtnHombre);

		JRadioButton rdbtnMujer = new JRadioButton("Mujer");
		rdbtnMujer.setBackground(UIManager.getColor("CheckBox.disabledText"));
		rdbtnMujer.setBounds(140, 18, 80, 23);
		bg.add(rdbtnMujer);
		panel.add(rdbtnMujer);

		JCheckBox chckbxdeporte = new JCheckBox("¿Practica algun deporte?");
		chckbxdeporte.setBackground(UIManager.getColor("CheckBox.disabledText"));
		chckbxdeporte.setBounds(29, 169, 201, 23);
		contentPane.add(chckbxdeporte);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(UIManager.getColor("CheckBox.foreground"));
		panel_1.setBorder(UIManager.getBorder("Button.border"));
		panel_1.setBounds(246, 200, 155, 80);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(126, 12, 17, 61);
		panel_1.add(scrollBar);

		JList list = new JList();
		list.setBackground(UIManager.getColor("CheckBox.disabledText"));
		list.setModel(new AbstractListModel() {
			String[] values = new String[] { "Tennis", "Futbol", "Baloncesto"};

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		list.setBounds(12, 12, 131, 56);
		panel_1.add(list);

		JLabel lblMarqueDel = new JLabel("Marque del 1 al 10 su grado de afición a:");
		lblMarqueDel.setBounds(12, 304, 296, 15);
		contentPane.add(lblMarqueDel);

		JLabel lblCompras = new JLabel("Compras:");
		lblCompras.setBounds(29, 331, 70, 15);
		contentPane.add(lblCompras);

		JSlider slider1 = new JSlider();
		slider1.setBackground(UIManager.getColor("CheckBox.disabledText"));
		slider1.setValue(5);
		slider1.setMaximum(10);
		slider1.setBounds(35, 358, 200, 16);
		contentPane.add(slider1);

		JLabel lblVerTelevision = new JLabel("Ver television");
		lblVerTelevision.setBounds(29, 386, 107, 15);
		contentPane.add(lblVerTelevision);

		JSlider slider2 = new JSlider();
		slider2.setBackground(UIManager.getColor("CheckBox.disabledText"));
		slider2.setValue(5);
		slider2.setMaximum(10);
		slider2.setBounds(35, 410, 200, 16);
		contentPane.add(slider2);

		JLabel lblIrAlCine = new JLabel("Ir al cine: ");
		lblIrAlCine.setBounds(29, 444, 70, 15);
		contentPane.add(lblIrAlCine);

		JSlider slider3 = new JSlider();
		slider3.setBackground(UIManager.getColor("CheckBox.disabledText"));
		slider3.setValue(5);
		slider3.setMaximum(10);
		slider3.setBounds(35, 469, 200, 16);
		contentPane.add(slider3);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(284, 381, 117, 25);
		btnAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (profesion.getText().isEmpty() || bg.getSelection() == null
						|| comboBoxEdad.getSelectedIndex() == 0) {

					JOptionPane.showMessageDialog(null, "Tienes que rellenar todos los campos.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else if ((int) spinner.getValue() < 0) {
					JOptionPane.showInputDialog(null, "No puedes tener un numero negativo de hermanos.", "Error");
				} else if (chckbxdeporte.isSelected() && list.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "Tienes que rellenar campo de deporte.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Rellenado Correctamente", "Correcto",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		contentPane.add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(284, 418, 117, 25);
		btnCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				profesion.setText(null);
				comboBoxEdad.setSelectedIndex(0);
				bg.clearSelection();
				chckbxdeporte.setSelected(false);
				slider1.setValue(5);
				slider2.setValue(5);
				slider3.setValue(5);
				spinner.setValue(0);
				list.clearSelection();

			}
		});
		contentPane.add(btnCancelar);

	}
}
