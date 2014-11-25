package org.meg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.meg.exception.DAOException;
import org.meg.model.Noticia;
import java.util.List;
import org.meg.model.Erro;

public class UtilDAO {
	private Connection connection;

	public UtilDAO() {
		this.connection = ConnectionFactory.getConnection();
	}

	/**
	 * Método que pega o nome de um estado pela id no banco de dados
	 * 
	 * @param id
	 * @return String com o nome do estado
	 */
	public String getNomeEstado(int id) {
		try {
			String sql = "SELECT nome FROM Estado WHERE id = ?";
			String nomeDoEstado = null;
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				nomeDoEstado = rs.getString("nome");
			}
			rs.close();
			stmt.close();
			return nomeDoEstado;
		} catch (SQLException sqlException) {
			throw new DAOException(
					"Erro ao buscar o nome do estado no banco de dados", this
							.getClass().getName());
		}
	}

	/**
	 * Método que pega a sigla do estado pelo id no banco de dados
	 * 
	 * @param id
	 * @return String com a sigla do estado
	 */
	public String getSiglaEstado(int id) {
		try {
			String sql = "SELECT sigla FROM Estado WHERE id = ?";
			String siglaDoEstado = null;
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				siglaDoEstado = rs.getString("sigla");
			}
			stmt.close();
			rs.close();

			return siglaDoEstado;

		} catch (SQLException sqlException) {
			throw new DAOException(
					"Erro ao buscar a sigla do estado no banco de dados", this
							.getClass().getName());
		}
	}

	/**
	 * Método que pega a id do estado pelo nome no banco de dados
	 * 
	 * @param nome
	 * @return int com a id do estado
	 */
	public int getIdEstado(String nome) {
		try {
			String sql = "SELECT id FROM Estado WHERE nome = ?";
			int idEstado = 0;
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, nome);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				idEstado = rs.getInt("id");
			}
			stmt.close();
			rs.close();
			return idEstado;
		} catch (SQLException sqlException) {
			throw new DAOException(
					"Erro ao buscar a id do estado no banco de dados", this
							.getClass().getName());
		}
	}

	/**
	 * Método que retorna o nome da seção no banco de dados
	 * 
	 * @param id
	 * @return String com o nome da secao
	 */
	public String getNomeSecao(int id) {
		try {
			String sql = "SELECT nome FROM Secao WHERE id = ?";
			String nomeSecao = null;
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				nomeSecao = rs.getString("nome");
			}
			stmt.close();
			rs.close();
			return nomeSecao;
		} catch (SQLException sqlException) {
			throw new DAOException(
					"Erro ao buscar o nome da secao no banco de dados", this
							.getClass().getName());
		}
	}

	/**
	 * Método que retorna o id da seção no banco de dados
	 * 
	 * @param nome
	 * @return int com a id da secao
	 */
	public int getIdSecao(String nome) {
		try {
			String sql = "SELECT id FROM Secao WHERE nome = ?";
			int idSecao = 0;
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, nome);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				idSecao = rs.getInt("id");
			}
			stmt.close();
			rs.close();
			return idSecao;
		} catch (SQLException sqlException) {
			throw new DAOException(
					"Erro ao buscar o id da secao no banco de dados", this
							.getClass().getName());
		}

	}

	/**
	 * Método que retorna o nome da descricao no banco de dados
	 * 
	 * @param id
	 * @return String com o nome da descricao
	 */
	public String getNomeDescricao(int id) {
		try {
			String sql = "SELECT nome FROM Descricao WHERE id = ?";
			String nomeDescricao = null;
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				nomeDescricao = rs.getString("nome");
			}
			stmt.close();
			rs.close();
			return nomeDescricao;
		} catch (SQLException sqlException) {
			throw new DAOException(
					"Erro ao buscar o nome da descricao no banco de dados",
					this.getClass().getName());
		}
	}

	/**
	 * Método que retorna o id da descricao no banco de dados
	 * 
	 * @param nome
	 * @return Int com o id da descricao
	 */
	public int getIdDescricao(String nome) {
		try {
			String sql = "SELECT id FROM Descricao WHERE nome = ?";
			int idDescricao = 0;
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, nome);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				idDescricao = rs.getInt("id");
			}
			stmt.close();
			rs.close();
			return idDescricao;
		} catch (SQLException sqlException) {
			throw new DAOException(
					"Erro ao buscar o id da descricao no banco de dados", this
							.getClass().getName());
		}
	}

	/**
	 * Método que pega o salario minimo do banco de dados
	 * 
	 * @param ano
	 * @return float com o salario minimo
	 */
	public float getSalarioMinimo(int ano) {
		try {
			float salarioMinimo = 0;
			String sql = "SELECT valor FROM SalarioMinimo WHERE ano = ?";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, ano);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				salarioMinimo = rs.getFloat("valor");
			}
			stmt.close();
			rs.close();
			return salarioMinimo;
		} catch (SQLException sqlException) {
			throw new DAOException("Erro ao buscar o ano no banco de dados",
					this.getClass().getName());
		}
	}

	/**
	 * Método que adiciona o histórico de uso das servlets do banco de dados
	 * 
	 * @param id
	 */
	public void adicionaHistorico(int id) {
		try {
			String add = "";
			switch (id) {
			case 1:
				add = "UPDATE Historico SET acessos = acessos + 1 WHERE nome = 'ranking'";
				break;
			case 2:
				add = "UPDATE Historico SET acessos = acessos + 1 WHERE nome = 'compara'";
				break;
			case 3:
				add = "UPDATE Historico SET acessos = acessos + 1 WHERE nome = 'projecao'";
				break;
			case 4:
				add = "UPDATE Historico SET acessos = acessos + 1 WHERE nome = 'grafico'";
				break;
			}
			PreparedStatement stmt = this.connection.prepareStatement(add);
			stmt.execute();
			stmt.close();
		} catch (SQLException sqlException) {
			System.err.println(sqlException);
			throw new DAOException("Erro ao adicionar no historico!", this
					.getClass().getName());
		}
	}

	public ArrayList<Noticia> prepararNoticia() {

		String sql = "SELECT * FROM Noticias ORDER BY RAND() LIMIT 3";

		ArrayList<Noticia> noticias = new ArrayList<Noticia>();
		PreparedStatement stmt;
		try {
			stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int db_id = rs.getInt("id");
				String db_noticia = rs.getString("noticia");
				String db_imagem = rs.getString("imagem");

				Noticia noticia = new Noticia();
				noticia.setNoticia(db_noticia);
				noticia.setId(db_id);
				noticia.setImagem(db_imagem);

				noticias.add(noticia);
			}

		} catch (SQLException e) {
			System.err.println(e);
			throw new DAOException("Erro ao obter noticias do banco de dados!", this.getClass().getName());
		}

		return noticias;
	}

	/**
	 * Registra uma excecao no banco de dados
	 * 
	 * @param erro
	 *            contém informacoes
	 */
	public void registraErro(Erro erro) {
		String sql = "INSERT INTO Erro(descricao, nomeDaClasseReferente, data, status) values(?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, erro.getDescricao());
			stmt.setString(2, erro.getNomeDaClasseReferente());
			stmt.setDate(3, erro.getData());
			stmt.setInt(4, erro.getStatus());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// Impossivel salvar excecao
		}
	}

	/**
	 * Remove um certo registro
	 * 
	 * @param id
	 *            Identificador do registro
	 */
	public void removeRegistroErro(int id) {
		String sql = "DELETE FROM Erro WHERE id = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Obtem a lista de erros registrados
	 * 
	 * @return Uma lista todos erros
	 */
	public List<Erro> obterErros() {
		String sql = "SELECT * FROM Erro";
		List<Erro> erros = new ArrayList<Erro>();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Erro erro = new Erro();
				erro.setData(rs.getDate("data"));
				erro.setNomeDaClasseReferente(rs
						.getString("nomeDaClasseReferente"));
				erro.setId(rs.getInt("id"));
				erro.setStatus(rs.getInt("status"));
				erro.setDescricao(rs.getString("descricao"));
				erros.add(erro);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// Impossivel testar
			e.printStackTrace();
		}
		return erros;
	}
	
	public int getHistorico(int id){
		int acesso = 0;
		try {
			String sql = "Select * From Historico Where id = ?";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				acesso = rs.getInt("acessos");
			}
		} catch (SQLException sqlException) {
			throw new DAOException("Erro ao buscar o numero de acessos no banco onde id = "+ id,this.getClass().getName());
		}
		
		return acesso;
	}
}
