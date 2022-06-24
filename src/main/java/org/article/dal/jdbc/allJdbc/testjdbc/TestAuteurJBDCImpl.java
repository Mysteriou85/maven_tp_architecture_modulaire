package org.article.dal.jdbc.allJdbc.testjdbc;

import eu.unareil.bo.produit.Produit;
import eu.unareil.bo.produit.cartePostale.Auteur;
import eu.unareil.dal.DALException;
import eu.unareil.dal.jdbc.allJdbc.AuteurJBDCImpl;

import java.util.List;

public class TestAuteurJBDCImpl {
    public static void main(String[] args) {

        AuteurJBDCImpl auteurJBDC = new AuteurJBDCImpl();

        String nom = "Tussaiki";
        String prenom = "George";

        Auteur newAuteur = new Auteur(prenom, nom);

//        try {
//            auteurJBDC.insert(newAuteur);
//        } catch (DALException e) {
//            throw new RuntimeException(e);
//        }

        try {
            System.out.println(auteurJBDC.selectById(3));
        } catch (DALException e) {
            throw new RuntimeException(e);
        }

        try {
            List<Auteur> maListeAuteur = auteurJBDC.selectAll();
            for (Auteur auteur : maListeAuteur) {
                System.out.println(auteur);
            }
        } catch (DALException e) {
            throw new RuntimeException(e);
        }

//        try {
//            auteurJBDC.delete(newAuteur);
//        } catch (DALException e) {
//            throw new RuntimeException(e);
//        }




    }
}
