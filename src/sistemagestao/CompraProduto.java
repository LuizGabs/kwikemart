package sistemagestao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CompraProduto {

	JFrame frame;
	private JTextField codbarrasInput;
	private JTextField descInput;
	private JTextField compraInput;
	private JTextField vendaInput;
	private JTextField quantInput;
	private JTextField unidadeInput;


	/**
	 * Create the application.
	 */
	public CompraProduto() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Insira as seguintes informações: ");
		lblNewLabel.setBounds(109, 11, 253, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Código de Barras:");
		lblNewLabel_1.setBounds(21, 41, 106, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		codbarrasInput = new JTextField();
		codbarrasInput.setBounds(164, 38, 196, 20);
		frame.getContentPane().add(codbarrasInput);
		codbarrasInput.setColumns(10);
		
		descInput = new JTextField();
		descInput.setBounds(164, 66, 196, 20);
		frame.getContentPane().add(descInput);
		descInput.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Descrição:");
		lblNewLabel_2.setBounds(31, 69, 62, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Preço de Compra: ");
		lblNewLabel_3.setBounds(31, 107, 95, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		compraInput = new JTextField();
		compraInput.setBounds(164, 104, 196, 20);
		frame.getContentPane().add(compraInput);
		compraInput.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Preço de Venda: ");
		lblNewLabel_4.setBounds(31, 132, 95, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		vendaInput = new JTextField();
		vendaInput.setBounds(164, 135, 196, 20);
		frame.getContentPane().add(vendaInput);
		vendaInput.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Quantidade Comprada: ");
		lblNewLabel_5.setBounds(31, 173, 130, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		quantInput = new JTextField();
		quantInput.setBounds(164, 170, 196, 20);
		frame.getContentPane().add(quantInput);
		quantInput.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Unidade de Medida: ");
		lblNewLabel_6.setBounds(31, 204, 130, 14);
		frame.getContentPane().add(lblNewLabel_6);
		
		unidadeInput = new JTextField();
		unidadeInput.setBounds(164, 201, 196, 20);
		frame.getContentPane().add(unidadeInput);
		unidadeInput.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControleDAO controle = new ControleDAO();
				String cod = codbarrasInput.getText();
				String desc = descInput.getText();
				String valorcompra = compraInput.getText();
				String valorvenda = vendaInput.getText();
				String quantidade = quantInput.getText();
				String unidade = unidadeInput.getText();
				int codigo = Integer.parseInt(cod);
				double valorcmp = Double.parseDouble(valorcompra);
				double valorvend = Double.parseDouble(valorvenda);
				int quant = Integer.parseInt(quantidade);
				controle.adicionarCompra(codigo, desc, valorcmp, valorvend, quant, unidade);
				if (controle.procurarProduto(codigo) == false) {
					controle.adicionarProduto(codigo, desc, valorvend, quant, unidade);
				}else {
					controle.atualizarProduto(codigo, desc, valorvend, (controle.procurarQuantidade(codigo) + quant), unidade);
				}
				JOptionPane.showMessageDialog(null, "Compra Adicionada.");
			}
		});
		btnCadastrar.setBounds(164, 227, 89, 23);
		frame.getContentPane().add(btnCadastrar);
	}

}
