package org.article.bll;

import org.article.bo.produit.Produit;
import org.article.dal.DALException;
import org.article.dal.DAO;
import org.article.dal.DAOFactory;

import java.util.List;

public class ProduitManager {
    private static volatile ProduitManager instance = null;
    private static DAO<Produit> impl;

    private ProduitManager() {
        impl = DAOFactory.getProduitDAO();
    }

    public final static ProduitManager getInstance() {
        if(ProduitManager.instance == null) {
            synchronized (ProduitManager.class) {
                if (ProduitManager.instance == null) {
                    ProduitManager.instance = new ProduitManager();
                }
            }
        }
        return  ProduitManager.instance;
    }

    public List<Produit> getLesProduits() throws BLLException {
        List<Produit> lesProduits = null;
        try {
            lesProduits = impl.selectAll();
        } catch (DALException e) {
            throw new BLLException("Erreur lors de la récupération des produits", e.getCause());
        }
        return lesProduits;
    }

    public void ajouteProduit(Produit produit) throws BLLException {
        if(produit.getRefProd()!=0) {
            throw new BLLException("Produit déjà existant");
        }
        valider(produit);
        try {
            impl.insert(produit);
        } catch (DALException e) {
            throw new BLLException("Erreur lors de l'ajout du produit "+ produit, e.getCause());
        }
    }

    public void supprimerProduit(Produit produit) throws BLLException {
        try {
            valider(produit);
            impl.delete(produit);
        } catch (DALException e) {
            throw new BLLException("Erreur lors de la suppression du produit "+ produit, e.getCause());
        }
    }

    public void modifierProduit(Produit produit) throws BLLException
    {
        valider(produit);
        try {
            impl.update(produit);
        } catch (DALException e) {
            throw new BLLException("Erreur lors de la modification du produit "+ produit, e.getCause());
        }
    }

    private void valider(Produit produit) throws BLLException
    {
        boolean valide=true;
        StringBuilder sb= new StringBuilder();
        if (produit==null)
        {
            throw new BLLException("Produit ne peut pas être null");
        }
        if(!valide) {
            throw new BLLException(sb.toString());
        }
    }

}
