package sistemagestao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Financas {

	JFrame frame;


	/**
	 * Create the application.
	 */
	public Financas() {
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
		
		JButton btnNewButton = new JButton("Relatório de Compras e Vendas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RelatorioComprasVendas relatorio = new RelatorioComprasVendas();
				relatorio.frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(97, 84, 256, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Lucros do Kwik-E-Mart");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LucrosProdutos lucros = new LucrosProdutos();
				lucros.frame.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(97, 147, 256, 23);
		frame.getContentPane().add(btnNewButton_1);
	}

}
