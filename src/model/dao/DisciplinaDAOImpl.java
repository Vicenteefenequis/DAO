package model.dao;

import model.db.DB;
import model.entities.Disciplina;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAOImpl implements  DisciplinaDAO {

    private Connection conexao;

    public DisciplinaDAOImpl(Connection conexao) {
        this.conexao = conexao;
    }


    @Override
    public void insert(Disciplina obj) {
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = conexao.prepareStatement("INSERT INTO disciplina (nomedisciplina,cargahoraria) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1,obj.getNomeDisciplina());
            pst.setInt(2,obj.getCargahoraria());

            int linhas = pst.executeUpdate();

            if(linhas > 0) {
                rs = pst.getGeneratedKeys();
                if(rs.next()){
                    obj.setIdDisciplina(rs.getInt(1));
                }
                System.out.println("    Disciplina [ "
                        + obj.getIdDisciplina() + " | "
                        + obj.getNomeDisciplina() + " ] "
                        + " foi criada com sucesso!");
            }else {
                System.out.println("    Não foi possível cadastrar a Disciplina!");
            }


        }catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DB.fechaPreparedStatement(pst);
            DB.fechaResultSet(rs);
        }
    }

    @Override
    public void update(Disciplina obj) {
        PreparedStatement pst = null;
        try {
            pst = conexao.prepareStatement("UPDATE disciplina SET nomedisciplina = ?, cargahoraria=? "
                    + "	WHERE iddisciplina=?");
            pst.setString(1, obj.getNomeDisciplina());
            pst.setInt(2, obj.getCargahoraria());
            pst.setInt(3,obj.getIdDisciplina());


            int linhas = pst.executeUpdate();
            if (linhas > 0) {
                System.out.println("    Disciplina [ "
                        + obj.getNomeDisciplina() + " | "
                        + obj.getCargahoraria() + " ] "
                        + " alterada com sucesso!");
            }
            else {
                System.out.println("    Não foi realizada a alteração da Disciplina!");
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
    public void deleteById(Integer id) {
        PreparedStatement pst = null;
        try {
            pst = conexao.prepareStatement("DELETE FROM disciplina WHERE iddisciplina = ?");
            pst.setInt(1, id);

            int linhas = pst.executeUpdate();
            if (linhas > 0) {
                System.out.println("    Disciplina [" + id + "] excluída com sucesso!");
            }
            else {
                System.out.println("    Não foi possível excluir a Disciplina  id[" + id + "] !");
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
    public Disciplina findById(Integer id) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conexao.prepareStatement(
                    "SELECT * FROM disciplina  WHERE iddisciplina = ?");
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                Disciplina d = new Disciplina();
                d.setIdDisciplina(rs.getInt("iddisciplina"));
                d.setNomeDisciplina(rs.getString("nomedisciplina"));
                d.setCargahoraria(rs.getInt("cargahoraria"));
                return d;
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
    public List<Disciplina> findAll() {
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Disciplina> lista = new ArrayList<>();

        try {
            pst = conexao.prepareStatement("SELECT * FROM disciplina");
            rs = pst.executeQuery();
            while (rs.next()) {
                Disciplina a = new Disciplina(rs.getInt("iddisciplina"), rs.getString("nomedisciplina"), rs.getInt("cargahoraria"));
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
