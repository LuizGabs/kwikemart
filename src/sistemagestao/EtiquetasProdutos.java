package sistemagestao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.TextArea;

public class EtiquetasProdutos {

	JFrame frmKwikemart;


	/**
	 * Create the application.
	 */
	public EtiquetasProdutos() {
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
		
		TextArea textEtiquetas = new TextArea();
		textEtiquetas.setBounds(0, 0, 434, 261);
		textEtiquetas.setText(controle.listarEtiquetas());
		frmKwikemart.getContentPane().add(textEtiquetas);
	}

}
