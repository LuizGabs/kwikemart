package sistemagestao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.TextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LucrosProdutos {

	JFrame frame;
	private JTextField textLucro;


	/**
	 * Create the application.
	 */
	public LucrosProdutos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ControleDAO controle = new ControleDAO();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Relatório de Vendas:");
		lblNewJgoodiesLabel.setBounds(10, 11, 109, 14);
		frame.getContentPane().add(lblNewJgoodiesLabel);
		
		TextArea textVendas = new TextArea();
		textVendas.setBounds(0, 26, 434, 129);
		textVendas.setText(controle.listarVendas());
		frame.getContentPane().add(textVendas);
		
		JLabel lblNewJgoodiesLabel_1 = DefaultComponentFactory.getInstance().createLabel("Lucro do Kwik-E-Mart (Somando as vendas e diminuindo os gastos com compras)");
		lblNewJgoodiesLabel_1.setBounds(10, 176, 394, 14);
		frame.getContentPane().add(lblNewJgoodiesLabel_1);
		
		textLucro = new JTextField();
		textLucro.setEditable(false);
		textLucro.setBounds(54, 199, 309, 20);
		frame.getContentPane().add(textLucro);
		String lucro = Double.toString(controle.encontrarLucros() - controle.encontrarGastos());
		textLucro.setText(lucro);
		textLucro.setColumns(10);
		
		JButton btnLucroEspecifico = new JButton("Encontrar Lucro sobre um produto específico");
		btnLucroEspecifico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigo = JOptionPane.showInputDialog("Digite o código de barras do produto:");
				if (controle.procurarCompra(Integer.parseInt(codigo)) != false) {
					if (controle.encontrarLucroEspecifico(Integer.parseInt(codigo)) - controle.encontrarGastoEspecifico(Integer.parseInt(codigo)) > 0) {
						JOptionPane.showMessageDialog(null, "Lucro de : " + (controle.encontrarLucroEspecifico(Integer.parseInt(codigo)) - controle.encontrarGastoEspecifico(Integer.parseInt(codigo))) + " reais.");
					}else {
						JOptionPane.showMessageDialog(null, "Prejuízo de : " + Math.abs(controle.encontrarLucroEspecifico(Integer.parseInt(codigo)) - controle.encontrarGastoEspecifico(Integer.parseInt(codigo))) + " reais.");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Produto não encontrado");
				}
			}
		});
		btnLucroEspecifico.setBounds(54, 227, 309, 23);
		frame.getContentPane().add(btnLucroEspecifico);
	}
}
