package sistemagestao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import java.awt.Font;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class ManagerWindow {

	JFrame frmKwikemart;
	/**
	 * Create the application.
	 */
	public ManagerWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ControleDAO controle = new ControleDAO();
		frmKwikemart = new JFrame();
		frmKwikemart.setTitle("Kwik-E-Mart");
		frmKwikemart.setBounds(100, 100, 450, 300);
		frmKwikemart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmKwikemart.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Vender Produto");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VendaProduto venda = new VendaProduto();
				venda.frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(65, 116, 278, 23);
		frmKwikemart.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Adicionar Compra de Produto");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CompraProduto compra = new CompraProduto();
				compra.frame.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(65, 150, 278, 23);
		frmKwikemart.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("Consultar Estoque");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControleEstoque controle = new ControleEstoque();
				controle.frmKwikemart.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(65, 82, 278, 23);
		frmKwikemart.getContentPane().add(btnNewButton_3);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 53, 22);
		frmKwikemart.getContentPane().add(menuBar);
		
		JMenu mnOpcoes = new JMenu("Opções");
		mnOpcoes.setFont(new Font("Arial", Font.BOLD, 12));
		menuBar.add(mnOpcoes);
		
		JMenuItem mntmControle = new JMenuItem("Controle de Usuários");
		mntmControle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControleUsuarios controle = new ControleUsuarios();
				controle.frmKwikemart.setVisible(true);
			}
		});
		mnOpcoes.add(mntmControle);
		
		JMenuItem mntmSobre = new JMenuItem("Sobre o Sistema");
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Sistema Kwik-E-Mart v1.0 desenvolvido por: Luiz Gabriel Favacho de Almeida.");
			}
		});
		mnOpcoes.add(mntmSobre);
		
		JLabel lblWelcome = DefaultComponentFactory.getInstance().createLabel("Bem-Vindo(a)! Selecione a opção desejada.");
		lblWelcome.setFont(new Font("Arial", Font.BOLD, 13));
		lblWelcome.setBounds(68, 28, 288, 14);
		frmKwikemart.getContentPane().add(lblWelcome);
		
		JButton btnNewButton_2 = new JButton("Informações Financeiras");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Financas financas = new Financas();
				financas.frame.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(65, 184, 278, 23);
		frmKwikemart.getContentPane().add(btnNewButton_2);
		
		JButton btnEtiquetagem = new JButton("Etiquetagem dos produtos");
		btnEtiquetagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EtiquetasProdutos etiqueta = new EtiquetasProdutos();
				etiqueta.frmKwikemart.setVisible(true);
			}
		});
		btnEtiquetagem.setBounds(65, 214, 278, 23);
		frmKwikemart.getContentPane().add(btnEtiquetagem);
	}
}
