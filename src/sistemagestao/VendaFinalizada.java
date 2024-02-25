package sistemagestao;

import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VendaFinalizada {

	JFrame frmKwikemart;
	private JTextField valorInput;


	/**
	 * Create the application.
	 */
	public VendaFinalizada() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKwikemart = new JFrame();
		frmKwikemart.setTitle("Kwik-E-Mart");
		frmKwikemart.setBounds(100, 100, 450, 300);
		frmKwikemart.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmKwikemart.getContentPane().setLayout(null);
		
		ControleDAO teste = new ControleDAO();
		
		TextArea textArea = new TextArea();
		textArea.setText(teste.vendaFinalizada());
		textArea.setEditable(false);
		textArea.setBounds(0, 0, 434, 160);
		frmKwikemart.getContentPane().add(textArea);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Cartão de Crédito", "Cartão de Débito", "Dinheiro", "Pix"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(256, 183, 153, 22);
		frmKwikemart.getContentPane().add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Escolha a forma de pagamento:");
		lblNewLabel.setBounds(10, 187, 161, 14);
		frmKwikemart.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Informe o valor recebido:");
		lblNewLabel_1.setBounds(10, 212, 161, 14);
		frmKwikemart.getContentPane().add(lblNewLabel_1);
		
		valorInput = new JTextField();
		valorInput.setBounds(256, 209, 153, 20);
		frmKwikemart.getContentPane().add(valorInput);
		valorInput.setColumns(10);
		
		JButton btnNewButton = new JButton("Finalizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String valor = valorInput.getText();
				double troco = Double.parseDouble(valor) - teste.valorFinal();
				JOptionPane.showMessageDialog(null, "Troco: " + troco + "\nVenda Finalizada.");
				teste.limparDataBase();
			}
		});
		btnNewButton.setBounds(158, 237, 89, 23);
		frmKwikemart.getContentPane().add(btnNewButton);
		
	}

}
