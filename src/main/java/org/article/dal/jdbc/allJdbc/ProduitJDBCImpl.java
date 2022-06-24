package org.article.dal.jdbc.allJdbc;

import eu.unareil.bo.produit.Produit;
import eu.unareil.bo.produit.Stylo;
import eu.unareil.bo.produit.cartePostale.Auteur;
import eu.unareil.bo.produit.cartePostale.CartePostale;
import eu.unareil.bo.produit.cartePostale.TypeCartePostale;
import eu.unareil.bo.produit.produitPerissable.Glace;
import eu.unareil.bo.produit.produitPerissable.Pain;
import eu.unareil.bo.produit.produitPerissable.ProduitPerissable;
import eu.unareil.dal.DALException;
import eu.unareil.dal.DAO;
import eu.unareil.dal.jdbc.JdbcTools;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitJDBCImpl implements DAO<Produit> {

    private static final String SQL_INSERT="insert into produit "
            + "(libelle, marque, prixUnitaire, qteStock, type, dateLimiteConso, poids, parfum, temperatureConservation, couleur, typeMine, typeCartePostale)"
            + " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE="update produit set "
            + "libelle = ?, marque = ?, prixUnitaire = ?, qteStock = ?, type = ?, dateLimiteConso = ?, poids = ?, parfum = ?,"
            + "temperatureConservation = ?, couleur = ?, typeMine = ?, typeCartePostale = ? "
            + "where refProd = ?";
    private static final String SQL_DELETE="delete from produit where refProd = ?";
    private static final String SQL_SELECT_ALL="select * from produit";
    private static final String SQL_SELECT_BY_ID="select * from produit where refProd = ?";

    // ----- INSERT PRODUIT -----
    @Override
    public void insert(Produit data) throws DALException {
        PreparedStatement pstmt = null;
        Connection cnx = JdbcTools.getConnection();
        try {
            pstmt = cnx.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, data.getLibelle());
            pstmt.setString(2, data.getMarque());
            pstmt.setFloat(3, data.getPrixUnitaire());
            pstmt.setLong(4, data.getQteStock());

            // pstmt.setString(5, "Stylo");
            // pstmt.setDate(6, Date.valueOf(((ProduitPerissable) data).getDateLimiteConso())); // Date
            // pstmt.setFloat(7, ((Pain) data).getPoids());
            // pstmt.setString(8, ((Glace) data).getParfun());
            // pstmt.setInt(9, ((Glace) data).getTemperatureConservation());
            // pstmt.setString(10, ((Stylo) data).getCouleur());
            // pstmt.setString(11, ((Stylo) data).getTypeMine());
            // pstmt.setString(12, ((CartePostale) data).getType().toString());

            if (data instanceof Stylo) {
                pstmt.setString(5, "Stylo");
                pstmt.setNull(6, Types.NULL); // Date
                pstmt.setNull(7, Types.NULL);
                pstmt.setNull(8, Types.NULL);
                pstmt.setNull(9, Types.NULL);
                pstmt.setString(10, ((Stylo) data).getCouleur());
                pstmt.setString(11, ((Stylo) data).getTypeMine());
                pstmt.setNull(12, Types.NULL);
            }
            if (data instanceof Pain) {
                pstmt.setString(5, "Pain");
                pstmt.setDate(6, Date.valueOf(((ProduitPerissable) data).getDateLimiteConso())); // Date
                pstmt.setFloat(7, ((Pain) data).getPoids());
                pstmt.setNull(8, Types.NULL);
                pstmt.setNull(9, Types.NULL);
                pstmt.setNull(10, Types.NULL);
                pstmt.setNull(11, Types.NULL);
                pstmt.setNull(12, Types.NULL);
            }
            if (data instanceof Glace) {
                pstmt.setString(5, "Glace");
                pstmt.setDate(6, Date.valueOf(((ProduitPerissable) data).getDateLimiteConso())); // Date
                pstmt.setNull(7, Types.NULL);
                pstmt.setString(8, ((Glace) data).getParfun());
                pstmt.setInt(9, ((Glace) data).getTemperatureConservation());
                pstmt.setNull(10, Types.NULL);
                pstmt.setNull(11, Types.NULL);
                pstmt.setNull(12, Types.NULL);
            }
            if (data instanceof CartePostale) {
                pstmt.setString(5, "CartePostale");
                pstmt.setNull(6, Types.NULL); // Date
                pstmt.setNull(7, Types.NULL);
                pstmt.setNull(8, Types.NULL);
                pstmt.setNull(9, Types.NULL);
                pstmt.setNull(10, Types.NULL);
                pstmt.setNull(11, Types.NULL);
                pstmt.setString(12, ((CartePostale) data).getType().toString());
            }
            int nbRow = pstmt.executeUpdate();
            if (nbRow == 1) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    data.setRefProd(rs.getLong(1));
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

    // ----- DELETE PRODUIT -----
    @Override
    public void delete(Produit data) throws DALException {
        PreparedStatement pstmt = null;
        long id = data.getRefProd();
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

    // ----- UPDATE -----
    @Override
    public void update(Produit data) throws DALException {
        PreparedStatement pstmt = null;
        long id = data.getRefProd();
        Connection cnx = JdbcTools.getConnection();
        try {
            pstmt = cnx.prepareStatement(SQL_UPDATE);
            pstmt.setString(1, data.getLibelle());
            pstmt.setString(2, data.getMarque());
            pstmt.setFloat(3, data.getPrixUnitaire());
            pstmt.setLong(4, data.getQteStock());

            if (data instanceof Stylo) {
                pstmt.setString(5, "Stylo");
                pstmt.setNull(6, Types.NULL); // Date
                pstmt.setNull(7, Types.NULL);
                pstmt.setNull(8, Types.NULL);
                pstmt.setNull(9, Types.NULL);
                pstmt.setString(10, ((Stylo) data).getCouleur());
                pstmt.setString(11, ((Stylo) data).getTypeMine());
                pstmt.setNull(12, Types.NULL);
            }
            if (data instanceof Pain) {
                pstmt.setString(5, "Pain");
                pstmt.setDate(6, Date.valueOf(((ProduitPerissable) data).getDateLimiteConso())); // Date
                pstmt.setFloat(7, ((Pain) data).getPoids());
                pstmt.setNull(8, Types.NULL);
                pstmt.setNull(9, Types.NULL);
                pstmt.setNull(10, Types.NULL);
                pstmt.setNull(11, Types.NULL);
                pstmt.setNull(12, Types.NULL);
            }
            if (data instanceof Glace) {
                pstmt.setString(5, "Glace");
                pstmt.setDate(6, Date.valueOf(((ProduitPerissable) data).getDateLimiteConso())); // Date
                pstmt.setNull(7, Types.NULL);
                pstmt.setString(8, ((Glace) data).getParfun());
                pstmt.setInt(9, ((Glace) data).getTemperatureConservation());
                pstmt.setNull(10, Types.NULL);
                pstmt.setNull(11, Types.NULL);
                pstmt.setNull(12, Types.NULL);
            }
            if (data instanceof CartePostale) {
                pstmt.setString(5, "CartePostale");
                pstmt.setNull(6, Types.NULL); // Date
                pstmt.setNull(7, Types.NULL);
                pstmt.setNull(8, Types.NULL);
                pstmt.setNull(9, Types.NULL);
                pstmt.setNull(10, Types.NULL);
                pstmt.setNull(11, Types.NULL);
                pstmt.setString(12, ((CartePostale) data).getType().toString());
            }
            pstmt.setLong(13, id);
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

    // ----- SELECT BY ID -----
    @Override
    public Produit selectById(long id) throws DALException {
        PreparedStatement pstmt = null;
        ResultSet rs = null ;
        Produit produit = null;
        Connection cnx = JdbcTools.getConnection();
        try {
            pstmt = cnx.prepareStatement(SQL_SELECT_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                // Si le produit selectionné est un stylo
                if (rs.getString(6).equals("Stylo")) {
                    produit = new Stylo(
                            rs.getLong(1),      // id
                            rs.getString(2),    // libelle
                            rs.getString(3),    // marque
                            rs.getLong(5),      // qteStock
                            rs.getFloat(4),     // prixUnitaire
                            rs.getString(11),   // couleur
                            rs.getString(12)    // typeMine
                    );
                }
                // Si le produit selectionné est un pain
                if (rs.getString(6).equals("Pain")) {
                    produit = new Pain(
                            rs.getLong(1),      // id
                            rs.getString(3),    // marque
                            rs.getString(2),    // libelle
                            rs.getLong(5),      // qteStock
                            rs.getFloat(4),     // prixUnitaire
                            rs.getFloat(8)      // poids
                    );
                }
                // Si le produit selectionné est une glace
                if (rs.getString(6).equals("Glace")) {
                    produit = new Glace(
                            rs.getLong(1),      // id
                            rs.getDate(7).toLocalDate(), // dateLimitConso
                            rs.getString(3),    // marque
                            rs.getString(2),    // libelle
                            rs.getLong(5),      // qteStock
                            rs.getFloat(4),     // prixUnitaire
                            rs.getString(9),    // parfum
                            rs.getInt(10)       // temperatureConservation
                    );
                }
                // Si le produit selectionné est un CartePostale
                if (rs.getString(6).equals("CartePostale")) {
                    AuteurJBDCImpl auteurJBDC = new AuteurJBDCImpl();
                    List<Auteur> listAuteur = new ArrayList<>();

                    listAuteur.add(auteurJBDC.selectById(rs.getLong(1)));

                    produit = new CartePostale(
                            rs.getLong(1),      // id
                            rs.getString(3),    // marque
                            rs.getString(2),    // libelle
                            rs.getLong(5),      // qteStock
                            rs.getFloat(4),     // prixUnitaire
                            listAuteur,
                            TypeCartePostale.valueOf(rs.getString(13))
                    );
                }
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
        return produit;
    }

    // ----- SELECT ALL -----
    @Override
    public List<Produit> selectAll() throws DALException {
        Statement stmt = null;
        ResultSet rs = null ;
        List<Produit> lesProduits = new ArrayList<>();
        Produit produit = null;
        Connection cnx = JdbcTools.getConnection();
        try {
            stmt = cnx.createStatement();
            rs = stmt.executeQuery(SQL_SELECT_ALL);
            while (rs.next()) {
                if (rs.getString(6).equals("Stylo")) {
                    produit = new Stylo(
                            rs.getLong(1),      // id
                            rs.getString(2),    // libelle
                            rs.getString(3),    // marque
                            rs.getLong(5),      // qteStock
                            rs.getFloat(4),     // prixUnitaire
                            rs.getString(11),   // couleur
                            rs.getString(12)    // typeMine
                    );
                }
                // Si le produit selectionné est un pain
                if (rs.getString(6).equals("Pain")) {
                    produit = new Pain(
                            rs.getLong(1),      // id
                            rs.getString(3),    // marque
                            rs.getString(2),    // libelle
                            rs.getLong(5),      // qteStock
                            rs.getFloat(4),     // prixUnitaire
                            rs.getFloat(8)      // poids
                    );
                }
                // Si le produit selectionné est une glace
                if (rs.getString(6).equals("Glace")) {
                    produit = new Glace(
                            rs.getLong(1),      // id
                            rs.getDate(7).toLocalDate(), // dateLimitConso
                            rs.getString(3),    // marque
                            rs.getString(2),    // libelle
                            rs.getLong(5),      // qteStock
                            rs.getFloat(4),     // prixUnitaire
                            rs.getString(9),    // parfum
                            rs.getInt(10)       // temperatureConservation
                    );
                }
                // Si le produit selectionné est un CartePostale
                if (rs.getString(6).equals("CartePostale")) {
                    AuteurJBDCImpl auteurJBDC = new AuteurJBDCImpl();
                    List<Auteur> listAuteur = new ArrayList<>();

                    listAuteur.add(auteurJBDC.selectById(rs.getLong(1)));

                    produit = new CartePostale(
                            rs.getLong(1),      // id
                            rs.getString(3),    // marque
                            rs.getString(2),    // libelle
                            rs.getLong(5),      // qteStock
                            rs.getFloat(4),     // prixUnitaire
                            listAuteur,
                            TypeCartePostale.valueOf(rs.getString(13))
                    );
                }
                lesProduits.add(produit);
            }
        } catch (SQLException e) {
            throw new DALException("erreur du select all" + e);
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
                throw new DALException("erreur du select all au niveau du close" + e);
            }
        }
        return lesProduits;
    }
}
