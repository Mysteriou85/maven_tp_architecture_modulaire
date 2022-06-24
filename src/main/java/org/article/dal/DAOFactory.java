package org.article.dal;

import org.article.bo.produit.Produit;
import org.article.dal.jdbc.ProduitJdbcImpl;

public class DAOFactory {
    public static DAO<Produit> getProduitDAO() {
        DAO<Produit> maDAO = new ProduitJdbcImpl();
        return maDAO;
    }
}
