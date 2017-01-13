package br.com.cinexd.dao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConnection()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		String url = "jdbc:mysql://127.0.0.1:3306/cine_xd?user=root&password=1234";
		Connection conexao = DriverManager.getConnection(url);
		return conexao;
	}
}
