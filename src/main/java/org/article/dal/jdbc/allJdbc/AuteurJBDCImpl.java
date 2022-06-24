package org.article.dal.jdbc.allJdbc;

import eu.unareil.bo.produit.cartePostale.Auteur;
import eu.unareil.dal.DALException;
import eu.unareil.dal.DAO;
import eu.unareil.dal.jdbc.JdbcTools;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuteurJBDCImpl implements DAO<Auteur> {

    private static final String SQL_INSERT="insert into auteur (nom, prenom) values(? , ?)";

    private static final String SQL_UPDATE="update auteur set nom = ?, prenom = ? where id = ?";
    private static final String SQL_DELETE="delete from auteur where id = ?";
    private static final String SQL_SELECT_ALL="select * from auteur";
    private static final String SQL_SELECT_BY_ID="select * from auteur where id = ?";

    @Override
    public void insert(Auteur data) throws DALException {
        PreparedStatement pstmt = null;
        Connection cnx = JdbcTools.getConnection();
        try {
            pstmt = cnx.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(2, data.getPrenom());
            pstmt.setString(1, data.getNom());
            int nbRow = pstmt.executeUpdate();
            if (nbRow == 1) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    data.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new DALException("erreur du insert - data=" + data, e.getCause());
        }
        finally {
            try {
                if(pstmt!=null) {
                    pstmt.close();
                }
                if(cnx!=null) {
                    cnx.close();
                }
            } catch (SQLException e) {
                throw new DALException("erreur du insert au niveau du close - data=" + data, e.getCause());
            }
        }
    }

    @Override
    public void delete(Auteur data) throws DALException {
        PreparedStatement pstmt = null;
        long id = data.getId();
        Connection cnx = JdbcTools.getConnection();
        try {
            pstmt = cnx.prepareStatement(SQL_DELETE);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DALException("erreur du delete - data=" + data, e.getCause());
        }
        finally {
            try {
                if(pstmt!=null) {
                    pstmt.close();
                }
                if(cnx!=null) {
                    cnx.close();
                }
            } catch (SQLException e) {
                throw new DALException("erreur du delete au niveau du close - data=" + data, e.getCause());
            }
        }
    }

    @Override
    public void update(Auteur data) throws DALException {
        PreparedStatement pstmt = null;
        long id = data.getId();
        Connection cnx = JdbcTools.getConnection();
        try {
            pstmt = cnx.prepareStatement(SQL_UPDATE);
            pstmt.setString(2, data.getPrenom());
            pstmt.setString(1, data.getNom());
            pstmt.setLong(5, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DALException("erreur du update - data=" + data, e.getCause());
        }
        finally {
            try {
                if(pstmt!=null) {
                    pstmt.close();
                }
                if(cnx!=null) {
                    cnx.close();
                }
            } catch (SQLException e) {
                throw new DALException("erreur du update au niveau du close - data=" + data, e.getCause());
            }
        }
    }

    @Override
    public Auteur selectById(long id) throws DALException {
        PreparedStatement pstmt = null;
        ResultSet rs = null ;
        Auteur auteur = null;
        Connection cnx = JdbcTools.getConnection();
        try {
            pstmt = cnx.prepareStatement(SQL_SELECT_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                auteur = new Auteur(
                        rs.getString(3),
                        rs.getString(2)
                );
            }
        } catch (SQLException e) {
            throw new DALException("erreur du select by id - id=" + id, e.getCause());
        }
        finally {
            try {
                if(pstmt!=null) {
                    pstmt.close();
                }
                if(cnx!=null) {
                    cnx.close();
                }
            } catch (SQLException e) {
                throw new DALException("erreur du select by id au niveau du close - id=" + id, e.getCause());
            }
        }
        return auteur;
    }

    @Override
    public List<Auteur> selectAll() throws DALException {
        Statement stmt = null;
        ResultSet rs = null ;
        List<Auteur> lesAuteurs = new ArrayList<>();
        Auteur auteur = null;
        Connection cnx = JdbcTools.getConnection();
        try {
            stmt = cnx.createStatement();
            rs = stmt.executeQuery(SQL_SELECT_ALL);
            while (rs.next()) {
                auteur = new Auteur(
                        rs.getString(3),
                        rs.getString(2)
                );
                lesAuteurs.add(auteur);
            }
        } catch (SQLException e) {
            throw new DALException("erreur du select all" + e.getCause());
        }
        finally {
            try {
                if(stmt!=null) {
                    stmt.close();
                }
                if(cnx!=null) {
                    cnx.close();
                }
            } catch (SQLException e) {
                throw new DALException("erreur du select all au niveau du close" + e.getCause());
            }
        }
        return lesAuteurs;
    }
}
