package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.db.DB;
import model.entities.Curso;

public class CursoDAOImpl implements CursoDAO {

	private Connection conexao;
	
	public CursoDAOImpl(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void insert(Curso obj) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conexao.prepareStatement("INSERT INTO curso (nomecurso) VALUES (?)", 
											Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, obj.getNomecurso());

			int linhas = pst.executeUpdate();
			
			if (linhas > 0) {
				rs = pst.getGeneratedKeys();
				if (rs.next()) {
					obj.setIdcurso(rs.getInt(1));
				}
				System.out.println("    |     Curso [ " 
									+ obj.getIdcurso() + " | " 
									+  obj.getNomecurso() + " ] "
									+ " foi criado com sucesso!");
			}
			else {
				System.out.println("    Não foi possível cadastrar o Curso!");
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
	public void update(Curso obj) {
		
		PreparedStatement pst = null;
		try {
			pst = conexao.prepareStatement("UPDATE curso SET nomecurso = ? "
										  +"WHERE idcurso = ?");
			pst.setString(1, obj.getNomecurso());
			pst.setInt(2, obj.getIdcurso());
			
			int linhas = pst.executeUpdate();
			if (linhas > 0) {	
				System.out.println("    Curso [ " 
								+ obj.getIdcurso() + " | " 
								+ obj.getNomecurso() + " ] "
								+ " alterado com sucesso!");
			}
			else {
				System.out.println("    Não foi realizada a alteração do Curso!");
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
			pst = conexao.prepareStatement("DELETE FROM curso WHERE idcurso = ?");
			pst.setInt(1, id);

			int linhas = pst.executeUpdate();
			if (linhas > 0) {	
				System.out.println("    Curso [" + id + "] excluído com sucesso!");
			}
			else {
				System.out.println("    Não foi possível excluir o Curso id[" + id + "] !");
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
	public Curso findByid(Integer id) {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conexao.prepareStatement("SELECT * FROM curso WHERE idcurso = ?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				Curso obj = new Curso();
				obj.setIdcurso(rs.getInt(1));
				obj.setNomecurso(rs.getString(2));
				return obj;
			}
			return null;
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			DB.fechaPreparedStatement(pst);
			DB.fechaResultSet(rs);
		}
		return null;
	}

	@Override
	public List<Curso> findAll() {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Curso> lista = new ArrayList<>();	
		
		try {
			pst = conexao.prepareStatement("SELECT * FROM curso");
			rs = pst.executeQuery();
			while (rs.next()) {   
				Curso c = new Curso(rs.getInt("idcurso"), rs.getString("nomecurso"));
				lista.add(c);
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