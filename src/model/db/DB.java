package model.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

	private static Connection conexao;

	public static Properties getPropriedades() {
		
		Properties propriedades = new Properties();
		try {
			FileInputStream arquivo = new FileInputStream("./propriedades/db.properties");
			propriedades.load(arquivo);	
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return propriedades;
	}

	public static Connection getConexao() {

		try {
			if (conexao == null) {
				Properties p = DB.getPropriedades();
				conexao = DriverManager.getConnection(	p.getProperty("host"),
														p.getProperty("user"),
														p.getProperty("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conexao;
	}

	public static void fechaConexao() {
		if (conexao != null) {
			try {
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void fechaPreparedStatement(PreparedStatement pst) {

		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void fechaResultSet(ResultSet rs) {

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
