package org.article.dal.jdbc;

import eu.unareil.dal.DALException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTools {

    public static Connection getConnection() throws DALException {
        StringBuilder sb = new StringBuilder();
        sb.append("jdbc:mariadb://");
        sb.append(Settings.getProperty("server"));
        sb.append(":");
        sb.append(Settings.getProperty("port"));
        sb.append("/");
        sb.append(Settings.getProperty("db"));
        sb.append("?");
        sb.append("user=");
        sb.append(Settings.getProperty("user"));
        sb.append("&");
        sb.append("password=");
        sb.append(Settings.getProperty("mdp"));

        System.out.println(sb.toString());
        Connection cnx;
        try {
            cnx = DriverManager.getConnection(sb.toString());
        } catch (SQLException e) {
            throw new DALException("Erreur de connection à la base de données", e.getCause());
        }
        return cnx;
    }

}
