package sistemagestao;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.TextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ControleEstoque {

	JFrame frmKwikemart;


	/**
	 * Create the application.
	 */
	public ControleEstoque() {
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
		textArea.setText(teste.listarProdutos());
		textArea.setEditable(false);
		textArea.setBounds(0, 0, 434, 207);
		frmKwikemart.getContentPane().add(textArea);
		
		JButton btnadd = new JButton("Adicionar Produto");
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigo = JOptionPane.showInputDialog("Digite o código de barras do produto:");
				String desc = JOptionPane.showInputDialog("Digite a descrição do produto:");
				String valor = JOptionPane.showInputDialog("Digite o valor do produto:");
				String quant = JOptionPane.showInputDialog("Digite a quantidade de produtos:");
				String unidade = JOptionPane.showInputDialog("Digite a unidade do produto:");
				int confirm = JOptionPane.showConfirmDialog(null, "Confirme a inserção: Código: " + codigo + " Descrição: " + desc + " Valor: " + valor + " Quantidade: " + quant +" Unidade: " + unidade);
				if (confirm == 0) {
					teste.adicionarProduto(Integer.parseInt(codigo), desc, Double.parseDouble(valor), Integer.parseInt(quant), unidade);
					JOptionPane.showMessageDialog(null, "Produto adicionado.");
				}
			}
		});
		btnadd.setBounds(10, 227, 117, 23);
		frmKwikemart.getContentPane().add(btnadd);
		
		JButton btnremove = new JButton("Remover Produto");
		btnremove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigo = JOptionPane.showInputDialog("Digite o código de barras do produto:");
				if (teste.procurarProduto(Integer.parseInt(codigo)) != false) {
					int confirm = JOptionPane.showConfirmDialog(null, "Confirme a remoção: " + codigo);
					if (confirm == 0) {
						teste.removerProd(Integer.parseInt(codigo));
						JOptionPane.showMessageDialog(null, "Produto: " + codigo + " removido.");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Prouto não encontrado.");
				}
			}
		});
		btnremove.setBounds(151, 227, 124, 23);
		frmKwikemart.getContentPane().add(btnremove);
		
		JButton btnalterar = new JButton("Alterar Produto");
		btnalterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigo = JOptionPane.showInputDialog("Digite o código de barras do produto:");
				if (teste.procurarProduto(Integer.parseInt(codigo)) != false) {
					int confirm = JOptionPane.showConfirmDialog(null, "Confirme a alteração do produto: " + codigo);
					if (confirm == 0) {
						String desc = JOptionPane.showInputDialog("Digite a nova descrição do produto:");
						String valor = JOptionPane.showInputDialog("Digite o novo valor do produto:");
						String quant = JOptionPane.showInputDialog("Digite a nova quantidade de produtos:");
						String unidade = JOptionPane.showInputDialog("Digite a nova unidade do produto:");
						int confirm2 = JOptionPane.showConfirmDialog(null, "Confirme a alteração: Código: " + codigo + " Descrição: " + desc + " Valor: " + valor + " Quantidade: " + quant +" Unidade: " + unidade);
						if (confirm2 == 0) {
							teste.atualizarProduto(Integer.parseInt(codigo), desc, Double.parseDouble(valor), Integer.parseInt(quant), unidade);
							JOptionPane.showMessageDialog(null, "Produto atualizado.");
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Produto não encontrado.");
				}
			}
		});
		btnalterar.setBounds(302, 227, 111, 23);
		frmKwikemart.getContentPane().add(btnalterar);
		
	}
}
