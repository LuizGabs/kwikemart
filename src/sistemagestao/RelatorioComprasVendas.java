package sistemagestao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.TextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RelatorioComprasVendas {

	JFrame frame;


	/**
	 * Create the application.
	 */
	public RelatorioComprasVendas() {
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
		
		TextArea textCompras = new TextArea();
		textCompras.setBounds(0, 23, 434, 105);
		textCompras.setText(controle.listarCompras());
		frame.getContentPane().add(textCompras);
		
		TextArea textVendas = new TextArea();
		textVendas.setBounds(0, 156, 424, 105);
		textVendas.setText(controle.listarVendas());
		frame.getContentPane().add(textVendas);
		
		JLabel lblCompras = DefaultComponentFactory.getInstance().createLabel("Relatório de Compras:");
		lblCompras.setBounds(10, 3, 136, 14);
		frame.getContentPane().add(lblCompras);
		
		JLabel lblVendas = DefaultComponentFactory.getInstance().createLabel("Relatório de Vendas: ");
		lblVendas.setBounds(10, 134, 120, 14);
		frame.getContentPane().add(lblVendas);
		
		JButton btnRemoverCompra = new JButton("Remover Compra");
		btnRemoverCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigo = JOptionPane.showInputDialog("Digite o ID da compra:");
				int confirm = JOptionPane.showConfirmDialog(null, "Confirme a remoção: Código: " + codigo);
				if (confirm == 0) {
					controle.removerCompra(Integer.parseInt(codigo));
					JOptionPane.showMessageDialog(null, "Compra removida.");
				}else {
					JOptionPane.showMessageDialog(null, "Operação cancelada.");
				}
			}
		});
		btnRemoverCompra.setBounds(123, 130, 136, 23);
		frame.getContentPane().add(btnRemoverCompra);
		
		JButton btnRemoverVenda = new JButton("Remover Venda");
		btnRemoverVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigo = JOptionPane.showInputDialog("Digite o ID da venda:");
				int confirm = JOptionPane.showConfirmDialog(null, "Confirme a remoção: Código: " + codigo);
				if (confirm == 0) {
					controle.removerCompra(Integer.parseInt(codigo));
					JOptionPane.showMessageDialog(null, "Venda removida.");
				}else {
					JOptionPane.showMessageDialog(null, "Operação cancelada.");
				}
			}
		});
		btnRemoverVenda.setBounds(269, 130, 155, 23);
		frame.getContentPane().add(btnRemoverVenda);
		
	}
}
