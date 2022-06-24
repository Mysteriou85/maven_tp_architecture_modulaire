package org.article.dal.jdbc.allJdbc.testjdbc;

import eu.unareil.bo.produit.Produit;
import eu.unareil.bo.produit.Stylo;
import eu.unareil.dal.DALException;
import eu.unareil.dal.jdbc.allJdbc.ProduitJDBCImpl;

import java.util.List;
import java.util.Scanner;

public class TestProduitJDBCImpl {
    public static void main(String[] args) {

        ProduitJDBCImpl produitJDBC = new ProduitJDBCImpl();
        Scanner scanner = new Scanner(System.in);

        String libelle = "Stylo rouge";
        String marque = "BicRouge";
        float prixUnitaire = 5.2f;
        long qteStock = 100;

        String couleur = "Rouge";
        String typeMine = "Fin";

        Produit newStylo = new Stylo(libelle, marque, qteStock, prixUnitaire, couleur, typeMine);

//        try {
//            produitJDBC.insert(newStylo);
//        } catch (DALException e) {
//            throw new RuntimeException(e);
//        }

//        try {
//            System.out.println(produitJDBC.selectById(13));
//        } catch (DALException e) {
//            throw new RuntimeException(e);
//        }

//        try {
//            produitJDBC.delete(produitJDBC.selectById(13));
//        } catch (DALException e) {
//            throw new RuntimeException(e);
//        }

//        try {
//            Stylo updateProduit = (Stylo) produitJDBC.selectById(12);
//            updateProduit.setCouleur("Rouge");
//            updateProduit.setTypeMine("Mine");
//            // updateProduit.setMultipleTruc(CHANGEMENT A EFFECTUE)
//            produitJDBC.update(updateProduit);
//
//        } catch (DALException e) {
//            throw new RuntimeException(e);
//        }

        try {
            List<Produit> maListeProduit = produitJDBC.selectAll();
            for (Produit produit : maListeProduit) {
                System.out.println(produit);
            }
        } catch (DALException e) {
            throw new RuntimeException(e);
        }
    }
}
