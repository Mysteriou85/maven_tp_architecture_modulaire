package org.article.bll;

import org.article.bo.produit.Produit;

import java.util.List;

public class ProduitManager {
//    private static volatile ProduitManager instance = null;
//    private static DAO<Produit> impl;
//    private ProduitManager() {
//        impl = DAOFactory.getProduitDAO();
//    }
//
//    public final static ProduitManager getInstance() {
//        if(ProduitManager.instance==null) {
//            synchronized (ProduitManager.class) {
//                if(ProduitManager.instance == null) {
//                    ProduitManager.instance=new ProduitManager();
//                }
//            }
//        }
//        return ProduitManager.instance;
//    }
//
//    public List<Produit> getLesProduits() throws BLLException {
//        List<Produit> lesProduits = null;
//        try {
//            lesEle=impl.selectAll();
//        } catch (DALException e) {
//            // TODO Auto-generated catch block
//            throw new BLLException("Erreur lors de la récupération des éléments chimiques",e.getCause());
//        }
//
//        return lesEle;
//    }
}
