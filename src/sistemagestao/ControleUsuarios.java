package sistemagestao;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.TextArea;

public class ControleUsuarios {

	JFrame frmKwikemart;

	/**
	 * Create the application.
	 */
	public ControleUsuarios() {
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
		frmKwikemart.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmKwikemart.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 53, 22);
		frmKwikemart.getContentPane().add(menuBar);
		
		JMenu mnOptions = new JMenu("Opções");
		mnOptions.setFont(new Font("Arial", Font.BOLD, 12));
		menuBar.add(mnOptions);
		
		JMenuItem mntmCadastrar = new JMenuItem("Cadastrar Usuário");
		mntmCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroUsuario cadastro = new CadastroUsuario();
				cadastro.frmKwikemart.setVisible(true);
			}
		});
		mnOptions.add(mntmCadastrar);
		
		JMenuItem mntmAlterar = new JMenuItem("Alterar Usuário");
		mntmAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigo = JOptionPane.showInputDialog("Digite o ID do usuário a ser alterado:");
				if (controle.procurarUsuario(Integer.parseInt(codigo))) {
					String nome = JOptionPane.showInputDialog("Digite o novo nome:");
					String login = JOptionPane.showInputDialog("Digite o novo login:");
					if(controle.verificarLogin(login)) {
						JOptionPane.showMessageDialog(null, "Login já existente, escolha outro.");
					}
					else {
						String senha = JOptionPane.showInputDialog("Digite a nova senha:");
						int confirm = JOptionPane.showConfirmDialog(null, "Confirme a alteração do Usuário: " + codigo + " Nome: " + nome + " Login: " + login + " Senha: " + senha);
						if (confirm == 0) {
							Usuario user = new Usuario(nome,login,senha,controle.encontrarPermissao(Integer.parseInt(codigo)));
							user.id = Integer.parseInt(codigo);
							controle.alterarUsuario(user);
							JOptionPane.showMessageDialog(null, "Usuário alterado.");
						}else {
							JOptionPane.showMessageDialog(null, "Usuário não alterado.");
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
				}
			}
		});
		mnOptions.add(mntmAlterar);
		
		JMenuItem mntmExcluir = new JMenuItem("Excluir Usuário");
		mntmExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigo = JOptionPane.showInputDialog("Digite o ID do usuário a ser excluido:");
				if (controle.procurarUsuario(Integer.parseInt(codigo))) {
					int confirm = JOptionPane.showConfirmDialog(null, "Confirme a exclusão do Usuário: " + codigo);
					if (confirm == 0) {
						controle.removerUsuario(Integer.parseInt(codigo));
						JOptionPane.showMessageDialog(null, "Usuário removido.");
					}else {
						JOptionPane.showMessageDialog(null, "Usuário não removido.");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
				}
			}
		});
		mnOptions.add(mntmExcluir);
		
		JLabel lblUsers = DefaultComponentFactory.getInstance().createLabel("Usuários do Sistema:");
		lblUsers.setFont(new Font("Arial", Font.BOLD, 12));
		lblUsers.setBounds(149, 0, 133, 22);
		frmKwikemart.getContentPane().add(lblUsers);
		
		TextArea textUsers = new TextArea();
		textUsers.setBounds(0, 21, 434, 240);
		textUsers.setText(controle.listarUsuarios());
		frmKwikemart.getContentPane().add(textUsers);
	}
}
