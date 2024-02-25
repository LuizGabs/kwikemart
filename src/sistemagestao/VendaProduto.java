package sistemagestao;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;

public class VendaProduto {

	JFrame frame;
	private JButton btnAdicionar;
	private JButton btnFinalizar;
	/**
	 * Create the application.
	 */
	public VendaProduto() {
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
		
		btnAdicionar = new JButton("Adicionar Produto");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControleDAO procurar = new ControleDAO();
				String codigo = JOptionPane.showInputDialog("Digite o código de barras do produto:");
				int codi = Integer.parseInt(codigo);
				if (procurar.procurarProduto(Integer.parseInt(codigo)) == true) {
					String quantidade = JOptionPane.showInputDialog("Digite o a quantidade à ser vendida:");
					int quant = Integer.parseInt(quantidade);
					procurar.adicionarVenda(codi, quant, procurar.procurarValorProduto(codi)*quant);
					procurar.adicionarVendaAtual(codi, quant, procurar.procurarValorProduto(codi));
					procurar.atualizarProduto(codi, procurar.procurarDescricao(codi), procurar.procurarValorProduto(codi), (procurar.procurarQuantidade(codi)-quant), procurar.procurarUnidade(codi));
					JOptionPane.showMessageDialog(null, "Produto Adicionado");
				}
				else {
					JOptionPane.showMessageDialog(null, "Produto Não Cadastrado");
				}
			}
		});
		btnAdicionar.setBounds(97, 112, 204, 23);
		frame.getContentPane().add(btnAdicionar);
		
		btnFinalizar = new JButton("Finalizar Venda");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VendaFinalizada venda = new VendaFinalizada();
				venda.frmKwikemart.setVisible(true);
			}
		});
		btnFinalizar.setBounds(97, 146, 204, 23);
		frame.getContentPane().add(btnFinalizar);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Venda de produtos Kwik-E-Mart");
		lblNewJgoodiesLabel.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewJgoodiesLabel.setBounds(113, 37, 188, 14);
		frame.getContentPane().add(lblNewJgoodiesLabel);
		
		
	}
}
