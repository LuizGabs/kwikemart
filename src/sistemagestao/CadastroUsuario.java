package sistemagestao;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroUsuario {

	JFrame frmKwikemart;
	private JTextField nomeInput;
	private JTextField loginInput;
	private JTextField senhaInput;
	private final ButtonGroup buttonGroup = new ButtonGroup();


	/**
	 * Create the application.
	 */
	public CadastroUsuario() {
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
		
		JLabel lblNome = DefaultComponentFactory.getInstance().createLabel("Digite o Nome:");
		lblNome.setBounds(36, 60, 92, 14);
		frmKwikemart.getContentPane().add(lblNome);
		
		nomeInput = new JTextField();
		nomeInput.setBounds(138, 57, 286, 20);
		frmKwikemart.getContentPane().add(nomeInput);
		nomeInput.setColumns(10);
		
		JLabel lblLogin = DefaultComponentFactory.getInstance().createLabel("Digite o Login:");
		lblLogin.setBounds(36, 102, 72, 14);
		frmKwikemart.getContentPane().add(lblLogin);
		
		loginInput = new JTextField();
		loginInput.setBounds(138, 99, 286, 20);
		frmKwikemart.getContentPane().add(loginInput);
		loginInput.setColumns(10);
		
		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("Área de cadastro no sistema");
		lblNewJgoodiesTitle.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewJgoodiesTitle.setBounds(138, 11, 164, 14);
		frmKwikemart.getContentPane().add(lblNewJgoodiesTitle);
		
		JLabel lblSenha = DefaultComponentFactory.getInstance().createLabel("Digite a Senha:");
		lblSenha.setBounds(36, 145, 92, 14);
		frmKwikemart.getContentPane().add(lblSenha);
		
		senhaInput = new JTextField();
		senhaInput.setBounds(138, 142, 286, 20);
		frmKwikemart.getContentPane().add(senhaInput);
		senhaInput.setColumns(10);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Escolha a Permissão:");
		lblNewJgoodiesLabel.setBounds(36, 195, 109, 14);
		frmKwikemart.getContentPane().add(lblNewJgoodiesLabel);
		
		JRadioButton rdbtnUsuario = new JRadioButton("Usuário");
		rdbtnUsuario.setSelected(true);
		buttonGroup.add(rdbtnUsuario);
		rdbtnUsuario.setBounds(151, 191, 109, 23);
		frmKwikemart.getContentPane().add(rdbtnUsuario);
		
		JRadioButton rdbtnAdmin = new JRadioButton("Admin");
		buttonGroup.add(rdbtnAdmin);
		rdbtnAdmin.setBounds(279, 191, 109, 23);
		frmKwikemart.getContentPane().add(rdbtnAdmin);
		
		JButton btnNewButton = new JButton("Cadastrar");
		
		ControleDAO controle = new ControleDAO();
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = nomeInput.getText();
				String login = loginInput.getText();
				if(controle.verificarLogin(login)) {
					JOptionPane.showMessageDialog(null, "Login já existente, escolha outro.");
				}
				else {
					String senha = senhaInput.getText();
					String permissao;
					if (rdbtnUsuario.isSelected()) {
						permissao = "Usuario";
					}else {
						permissao = "Admin";
					}
					Usuario user = new Usuario(nome,login,senha,permissao);
					controle.adicionarUsuario(user);
					JOptionPane.showMessageDialog(null, "Usuário Cadastrado.");	
				}
			}
		});
		btnNewButton.setBounds(161, 227, 89, 23);
		frmKwikemart.getContentPane().add(btnNewButton);
	}
}
