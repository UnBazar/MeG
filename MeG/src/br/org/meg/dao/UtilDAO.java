package org.meg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.meg.exception.DAOException;
import org.meg.model.History;
import org.meg.model.Note;

import java.util.List;

import org.meg.model.Error;

public class UtilDAO {
	private Connection connection;

	public UtilDAO() {
		this.connection = ConnectionFactory.getConnection();
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
	public void addHistory(String path) {
		try {
			String add = "UPDATE Historico SET acessos = acessos + 1 WHERE "
					+ "nome = '"+path+"'";
			PreparedStatement stmt = this.connection.prepareStatement(add);
			stmt.execute();
			stmt.close();
		} catch (SQLException sqlException) {
			System.err.println(sqlException);
			throw new DAOException("Erro ao adicionar no historico!", 
					this.getClass().getName());
		}
	}
	/**
	 * Get 3 randomic notes
	 * 
	 * @return {@link ArrayList} of Note
	 */
	public ArrayList<Note> getNotes() {
		// SQL command to get randomic notes
		String sql = "SELECT * FROM Noticias ORDER BY RAND() LIMIT 3";
		// Create an arrayList of notes
		ArrayList<Note> notes = new ArrayList<Note>();
		PreparedStatement preparedStatement;
		try {
			// Get global connection to prepare statement
			preparedStatement = this.connection.prepareStatement(sql);
			// Get result query
			ResultSet resultSet = preparedStatement.executeQuery();
			// Runs while exist to the next
			while (resultSet.next()) {
				// Get each data from note in the database
				int noteID = resultSet.getInt("id");
				String message = resultSet.getString("noticia");
				String image = resultSet.getString("imagem");
				// Create and set an new note
				Note note = new Note();
				note.setNoticia(message);
				note.setId(noteID);
				note.setImagem(image);
				
				notes.add(note);
			}
		} catch (SQLException e) {
			throw new DAOException("Error to obtain notes", this.getClass().getName());
		}
		return notes;
	}

	/**
	 * Registra uma excecao no banco de dados
	 * 
	 * @param erro
	 *            contém informacoes
	 */
	public void registraErro(Error erro) {
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
	public List<Error> obterErros() {
		String sql = "SELECT * FROM Erro";
		List<Error> erros = new ArrayList<Error>();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Error erro = new Error();
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
	
	public History getHistory(int id){
		History history = null;
		try {
			String sql = "SELECT * FROM Historico WHERE id = ?";
			PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int access = resultSet.getInt("acessos");
				history = new History(id, access);
			}
		} catch (SQLException sqlException) {
			throw new DAOException("Error fetching the history of id 1 = "+id, 
					this.getClass().getName());
		}
		return history;
	}
}
