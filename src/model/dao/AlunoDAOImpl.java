package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.db.DB;
import model.entities.Aluno;

public class AlunoDAOImpl implements AlunoDAO{

	private Connection conexao;
		
	public AlunoDAOImpl(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void insert(Aluno obj) {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conexao.prepareStatement(
					"INSERT INTO aluno (nome, sexo, dt_nasc) VALUES (?, ?, ?) ", 
					Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, obj.getNome());
			pst.setString(2, obj.getSexo());
			pst.setDate(3, new java.sql.Date(obj.getDt_nasc().getTime()));

			int linhas = pst.executeUpdate();
				
			if (linhas > 0) {
				rs = pst.getGeneratedKeys();
				if (rs.next()) {
						obj.setId(rs.getInt(1));
				}
				System.out.println("    Aluno [ " 
									+ obj.getId() + " | " 
									+ obj.getNome() + " ] "
									+ " foi criado com sucesso!");
			}
			else {
				System.out.println("    Não foi possível cadastrar o Aluno!");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.fechaPreparedStatement(pst);
			DB.fechaResultSet(rs);
		}
	}

	@Override
	public void update(Aluno obj) {
		
		PreparedStatement pst = null;
		try {
			pst = conexao.prepareStatement("UPDATE aluno SET nome = ?, sexo=?, dt_nasc=? "
										+ "	WHERE idaluno=?");
			pst.setString(1, obj.getNome());
			pst.setString(2, obj.getSexo());
			pst.setDate(3, new java.sql.Date(obj.getDt_nasc().getTime()));
			pst.setInt(4, obj.getId());
			
			int linhas = pst.executeUpdate();
			if (linhas > 0) {	
				System.out.println("    Aluno [ " 
								+ obj.getId() + " | " 
								+ obj.getNome() + " ] "
								+ " alterado com sucesso!");
			}
			else {
				System.out.println("    Não foi realizada a alteração do Aluno!");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.fechaPreparedStatement(pst);
		}
	}

	@Override
	public void deleteByid(Integer id) {
		
		PreparedStatement pst = null;
		try {
			pst = conexao.prepareStatement("DELETE FROM aluno WHERE idaluno = ?");
			pst.setInt(1, id);
			
			int linhas = pst.executeUpdate();
			if (linhas > 0) {	
				System.out.println("    Aluno [" + id + "] excluído com sucesso!");
			}
			else {
				System.out.println("    Não foi possível excluir o Aluno id[" + id + "] !");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.fechaPreparedStatement(pst);
		}
	}

	@Override
	public Aluno findByid(Integer id) {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conexao.prepareStatement(
					"SELECT a.*, c.nomecurso FROM aluno a WHERE idaluno = ?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				Aluno a = new Aluno();
				a.setId(rs.getInt(0));
				a.setNome(rs.getString(1));
				a.setSexo(rs.getString(2));
				a.setDt_nasc(rs.getDate(3));
				return a;
			}
			return null;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.fechaPreparedStatement(pst);
			DB.fechaResultSet(rs);
		}		
		return null;
	}

	@Override
	public List<Aluno> findAll() {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Aluno> lista = new ArrayList<>();	
		
		try {
			pst = conexao.prepareStatement("SELECT * FROM aluno");
			rs = pst.executeQuery();
			while (rs.next()) {   
				Aluno a = new Aluno(rs.getInt("idaluno"), rs.getString("nome"), rs.getString("sexo"), rs.getDate("dt_nasc"));
				lista.add(a);
			}
		}
		catch (SQLException e) {
			System.out.println(e);
		}
		finally {
			DB.fechaPreparedStatement(pst);
			DB.fechaResultSet(rs);
		}
		return lista;	
	}
}