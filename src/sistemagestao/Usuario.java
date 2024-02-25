package sistemagestao;

public class Usuario {
	public int id;
	public String nome;
	public String login;
	public String senha;
	public String permissao;
	

	Usuario(String nome, String login, String senha, String permissao){
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.permissao = permissao;
	}
}
