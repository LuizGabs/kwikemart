package sistemagestao;
import java.sql.*;

public class ControleDAO extends BancoDeDados {
	public String listarProdutos() { // lista os produtos cadastrados no banco de dados
		String relatorio = "";
	    try {
	      Statement st = conexao.createStatement();
	      ResultSet rs = st.executeQuery("SELECT * FROM produtos");
	      while (rs.next()) {
	        relatorio += "Produto: " + rs.getString(1) + " Descrição: " + rs.getString(2) + " Valor de venda: " + rs.getString(3) +
	        		" Quantidade disponível: " + rs.getString(4) + " Unidade: " + rs.getString(5) + "\n";

	      }
	    }
	    catch (SQLException e) { }
	    return relatorio;
	  }
	
	public boolean procurarProduto(int codigo) { // procura um produto passando o codigo de barras como parâmetro
		boolean flag = false;
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM produtos");
			while (rs.next()) {
				if (rs.getInt(1) == codigo){
					flag = true;
				}
			}
		}
		catch (SQLException e) { }
		return flag;
	}
	
	public double procurarValorProduto(int codigo) { // procura o valor de um produto passando o codigo de barras como parâmetro
		double valor = 0;
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM produtos");
			while (rs.next()) {
				if (rs.getInt(1) == codigo){
					valor = rs.getDouble(3);
				}
			}
		}
		catch (SQLException e) { }
		return valor;
	}
	public void adicionarVenda(int codbarras, int quant, double valor) { // adiciona uma venda no banco de dados passando o codigo de barras como parâmetro, a quantidade e o valor
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO vendasprodutos (`codbarras`, `quantidade`, `valor`) VALUES ('" + codbarras + "', '" + quant + "', '" +  valor + "');");
		}
		catch (SQLException e) { }
	}
	public void adicionarVendaAtual(int codbarras, int quant, double valor) { // método utilizado para calcular o valor da venda de produtos, inserindo no banco de dados
		double valortotal = quant*valor;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO vendaatual (`codbarras`, `quantidade`, `valor`, `valortotal`) VALUES ('" + codbarras + "', '" + quant + "', '" +  valor + "', '" + valortotal + "');");
		}
		catch (SQLException e) { }
	}
	
	public String vendaFinalizada() { // após finalizar a venda, esse método é chamado para informar os produtos e a quantidade
			String relatorio = "";
			double valorfinal = 0;
		    try {
		      Statement st = conexao.createStatement();
		      ResultSet rs = st.executeQuery("SELECT * FROM vendaatual");
		      while (rs.next()) {
		        relatorio += "Produto: " + rs.getString(2) + " Quantidade: " + rs.getString(3) +
		        		" Valor: " + rs.getString(4) + "\n";
		        valorfinal += rs.getDouble(5);

		      }
		      relatorio += "Valor final para pagar: " + valorfinal;
		    }
		    catch (SQLException e) { }
		    return relatorio;
	}
	
	public double valorFinal() { // informa o valor final da venda
		double valor = 0;
		 try {
		      Statement st = conexao.createStatement();
		      ResultSet rs = st.executeQuery("SELECT valortotal FROM vendaatual");
		      while (rs.next()) {
		        valor += rs.getDouble(1);
		      }
		    }
		    catch (SQLException e) { }
		 return valor;
	}
	
	public void limparDataBase() { // apaga todo o banco de dados "vendaatual", pois ele só é utilizado unicamente para cada venda
		try {
		      Statement st = conexao.createStatement();
		      st.executeUpdate("DELETE FROM vendaatual");
		}
		catch (SQLException e) { }
	}
	
	public void adicionarCompra(int codbarras, String desc, double precocompra, double precovenda, int quantidade, String unidade) { // adiciona uma compra no banco de dados passando o codigo de barras, descrição, preço de compra, preço de venda, quantidade e unidade como parâmetros
		try {
		      Statement st = conexao.createStatement();
		      st.executeUpdate("INSERT INTO comprasprodutos (`codigobarras`, `desc`, `precocompra`, `precovenda`, `quantidade`, `unidade`) VALUES ('" + codbarras + "', '" + desc + "', '" +  precocompra + "', '" + precovenda + "', '" + quantidade + "', '" + unidade + "');");
		}catch (SQLException e) { }
	}
	
	public void adicionarProduto(int codbarras, String desc, double valor, int quantidade, String unidade) { // adiciona um produto no banco de dados dos produtos
		try {
		      Statement st = conexao.createStatement();
		      st.executeUpdate("INSERT INTO produtos (`codbarras`, `desc`, `valor`, `quantidade`, `unidade`) VALUES ('" + codbarras + "', '" + desc + "', '" +  valor + "', '" + quantidade + "', '" + unidade + "');");
		}catch (SQLException e) { }
	}
	
	public void atualizarProduto(int codbarras, String desc, double valor, int quantidade, String unidade) { // atualiza um produto no banco de dados dos produtos
		removerProd(codbarras);
		adicionarProduto(codbarras, desc, valor, quantidade, unidade);
	}
	public void removerProd(int codigo) { // remove um produto no banco de dados dos produtos
		try {
		      Statement st = conexao.createStatement();
		      st.executeUpdate("DELETE FROM produtos WHERE codbarras=" + codigo);
		    }
		    catch (SQLException e) { }
	}
	public String listarUsuarios() { // lista os usuários cadastrados no sistema
		String relatorio = "";
		 try {
		      Statement st = conexao.createStatement();
		      ResultSet rs = st.executeQuery("SELECT * FROM usuarios");
		      while (rs.next()) {
		          relatorio += "ID Usuário: " + rs.getString(1) + " Nome: " + rs.getString(2) + " Login: " +
		      rs.getString(3) + " Senha: " + rs.getString(4) + " Permissão: " + rs.getString(5) + "\n";
		      }
		 }
		 catch (SQLException e) { }
		 return relatorio;
	}
	public void adicionarUsuario(Usuario p) { // adiciona um usuário no sistema
		try {
		      Statement st = conexao.createStatement();
		      st.executeUpdate("INSERT INTO usuarios (`nome`, `login`, `senha`, `permissão`) VALUES ('" + p.nome + "', '" + p.login + "', '" +  p.senha + "', '" + p.permissao + "');");
		}catch (SQLException e) { }
	}
	public boolean verificarLogin(String login) { // verifica se o login é válido
		boolean flag = false;
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM usuarios");
			while (rs.next()) {
				if (rs.getString(3).equals(login)){
					flag = true;
				}
			}
		}
		catch (SQLException e) { }
		return flag;
	}
	public String verificarSenha(String login) { // verifica se a senha é válida
		String senha = "";
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM usuarios WHERE login='" + login + "'");
			if (rs.next()) {
				senha = rs.getString(4);
			}else return null;
		}
		catch (SQLException e) { }
		return senha;
	}
	public void removerUsuario(int id) { // remove um usuário do sistema
		try {
		      Statement st = conexao.createStatement();
		      st.executeUpdate("DELETE FROM usuarios WHERE idusuarios=" + id);
		    }
		    catch (SQLException e) { }
	}
	public boolean procurarUsuario(int id) { // verifica se um usuário está cadastrado no sistema
		boolean flag = false;
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM usuarios");
			while (rs.next()) {
				if (rs.getInt(1) == id){
					flag = true;
				}
			}
		}
		catch (SQLException e) { }
		return flag;
	}
	public void alterarUsuario(Usuario p) { // altera um usuário
		removerUsuario(p.id);
		adicionarUsuario(p);
	}
	public String encontrarPermissao(int codigo) { // encontra a permissão do usuário
		String perm = null;
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM usuarios");
			while (rs.next()) {
				if (rs.getInt(1) == codigo){
					perm = rs.getString(5);
				}
			}
		}
		catch (SQLException e) { }
		return perm;
	}
	public int procurarID(String login) { // encontra o ID do usuário
		int id = 0;
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM usuarios WHERE login='" + login + "'");
			if(rs.next()) {
				id = rs.getInt(1);
			}
		}catch (SQLException e) { }
		return id;
	}
	public int procurarQuantidade(int codigo) { // procura a quantidade de um determinado produto
		int valor = 0;
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM produtos");
			while (rs.next()) {
				if (rs.getInt(1) == codigo){
					valor = rs.getInt(4);
				}
			}
		}
		catch (SQLException e) { }
		return valor;
	}
	public String procurarDescricao(int codigo) {// procura a descrição de um determinado produto
		String valor = "";
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM produtos");
			while (rs.next()) {
				if (rs.getInt(1) == codigo){
					valor = rs.getString(2);
				}
			}
		}
		catch (SQLException e) { }
		return valor;
	}
	public String procurarUnidade(int codigo) { // procura a unidade de um determinado produto
		String valor = "";
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM produtos");
			while (rs.next()) {
				if (rs.getInt(1) == codigo){
					valor = rs.getString(5);
				}
			}
		}
		catch (SQLException e) { }
		return valor;
	}
	public double encontrarGastos() { // encontra os gastos do supermercado
		double gastos = 0;
		try {
		      Statement st = conexao.createStatement();
		      ResultSet rs = st.executeQuery("SELECT * FROM comprasprodutos");
		      while (rs.next()) {
		        gastos += rs.getDouble(4)*rs.getInt(6);
		      }
		    }
		    catch (SQLException e) { }
		return gastos;
	}
	public double encontrarLucros() { // encontra os lucros do supermercado
		double lucros = 0;
		try {
		      Statement st = conexao.createStatement();
		      ResultSet rs = st.executeQuery("SELECT * FROM vendasprodutos");
		      while (rs.next()) {
		        lucros += rs.getDouble(4);
		      }
		    }
		    catch (SQLException e) { }
		return lucros;
	}
	public double encontrarLucroEspecifico(int codbarras) { // encontra um lucro de um produto específico
		double lucros = 0;
		try {
		      Statement st = conexao.createStatement();
		      ResultSet rs = st.executeQuery("SELECT * FROM vendasprodutos");
		      while (rs.next()) {
		    	if (rs.getInt(2) == codbarras) {
			        lucros += rs.getDouble(4);
		    	}
		      }
		    }
		    catch (SQLException e) { }
		return lucros;
	}
	public double encontrarGastoEspecifico(int codbarras) { // encontra o gasto de um produto específico 
		double gastos = 0;
		try {
		      Statement st = conexao.createStatement();
		      ResultSet rs = st.executeQuery("SELECT * FROM comprasprodutos");
		      while (rs.next()) {
		    	if (rs.getInt(2) == codbarras) {
			        gastos += rs.getDouble(4)*rs.getInt(6);
		    	}
		      }
		    }
		    catch (SQLException e) { }
		return gastos;
	}
	public String listarCompras() { // lista as compras feitas no sistema
		String relatorio = "";
	    try {
	      Statement st = conexao.createStatement();
	      ResultSet rs = st.executeQuery("SELECT * FROM comprasprodutos");
	      while (rs.next()) {
	        relatorio += "ID: " + rs.getString(1) + " Produto: " + rs.getString(2) + " Descrição: " + rs.getString(3) +
	        		" Valor de Compra: " + rs.getString(4) + " Valor de Venda: " + rs.getString(5) + 
	        		" Quantidade Adquirida: " + rs.getString(6) + " Unidade: " + rs.getString(7) + "\n";

	      }
	    }
	    catch (SQLException e) { }
	    return relatorio;
	}
	public String listarVendas() { // lista as vendas feitas no sistema
		String relatorio = "";
	    try {
	      Statement st = conexao.createStatement();
	      ResultSet rs = st.executeQuery("SELECT * FROM vendasprodutos");
	      while (rs.next()) {
	        relatorio += "ID: " + rs.getString(1) + " Produto: " + rs.getString(2) + " Quantidade vendida: " + rs.getString(3) +
	        		" Valor da venda: " + rs.getString(4) + "\n";

	      }
	    }
	    catch (SQLException e) { }
	    return relatorio;
	}
	public boolean procurarCompra(int codigo) { // procura se existe uma compra no sistema passando o código como parâmetro
		boolean flag = false;
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM comprasprodutos");
			while (rs.next()) {
				if (rs.getInt(2) == codigo){
					flag = true;
				}
			}
		}
		catch (SQLException e) { }
		return flag;
	}
	public String listarEtiquetas() { // lista as etiquetas dos produtos
		String relatorio = "";
	    try {
	      Statement st = conexao.createStatement();
	      ResultSet rs = st.executeQuery("SELECT * FROM produtos");
	      while (rs.next()) {
	        relatorio += "Produto: " + rs.getString(1) + " Descrição: " + rs.getString(2) + " Valor de venda: " + rs.getString(3) + "\n";
	      }
	    }
	    catch (SQLException e) { }
	    return relatorio;
	}
	public void removerVenda(int codigo) { // remove uma venda
		try {
		      Statement st = conexao.createStatement();
		      st.executeUpdate("DELETE FROM vendasprodutos WHERE id=" + codigo);
		    }
		    catch (SQLException e) { }
	}
	public void removerCompra(int codigo) { // remove uma comora
		try {
		      Statement st = conexao.createStatement();
		      st.executeUpdate("DELETE FROM comprasprodutos WHERE id=" + codigo);
		    }
		    catch (SQLException e) { }
	}
}