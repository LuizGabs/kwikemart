package sistemagestao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sistema {

	private JFrame frmKwikemart;
	private JTextField loginInput;
	private JTextField senhaInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sistema window = new Sistema();
					window.frmKwikemart.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Sistema() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ControleDAO controle = new ControleDAO();
		frmKwikemart = new JFrame();
		frmKwikemart.setFont(new Font("Arial", Font.PLAIN, 12));
		frmKwikemart.setTitle("Kwik-E-Mart");
		frmKwikemart.setBounds(100, 100, 450, 300);
		frmKwikemart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmKwikemart.getContentPane().setLayout(null);
		
		JLabel sistemainfoLabel = DefaultComponentFactory.getInstance().createLabel("Bem Vindo ao Sistema de Gestão Kwik-E-Mart! Insira suas informações abaixo: ");
		sistemainfoLabel.setFont(new Font("Arial", Font.BOLD, 10));
		sistemainfoLabel.setBounds(10, 0, 414, 35);
		frmKwikemart.getContentPane().add(sistemainfoLabel);
		
		JLabel loginLabel = DefaultComponentFactory.getInstance().createLabel("Digite seu Login:");
		loginLabel.setFont(new Font("Arial", Font.BOLD, 13));
		loginLabel.setBounds(32, 64, 112, 14);
		frmKwikemart.getContentPane().add(loginLabel);
		
		loginInput = new JTextField();
		loginInput.setBounds(154, 61, 126, 20);
		frmKwikemart.getContentPane().add(loginInput);
		loginInput.setColumns(10);
		
		JLabel senhaLabel = new JLabel("Digite sua Senha: ");
		senhaLabel.setFont(new Font("Arial", Font.BOLD, 13));
		senhaLabel.setBounds(32, 109, 126, 14);
		frmKwikemart.getContentPane().add(senhaLabel);
		
		senhaInput = new JTextField();
		senhaInput.setBounds(154, 106, 126, 20);
		frmKwikemart.getContentPane().add(senhaInput);
		senhaInput.setColumns(10);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String login = loginInput.getText();
				String senha = senhaInput.getText();
				if (controle.verificarSenha(login).equals(senha)) {
					ManagerWindow managerWindow = new ManagerWindow();
					managerWindow.frmKwikemart.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos.");
				}
			}
		});
		btnEntrar.setBounds(165, 179, 89, 23);
		frmKwikemart.getContentPane().add(btnEntrar);
		
		JButton btnVisitante = new JButton("Entrar como visitante");
		btnVisitante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerWindow managerWindow = new ManagerWindow();
				managerWindow.frmKwikemart.setVisible(true);
			}
		});
		btnVisitante.setBounds(142, 216, 144, 23);
		frmKwikemart.getContentPane().add(btnVisitante);
	}
}
