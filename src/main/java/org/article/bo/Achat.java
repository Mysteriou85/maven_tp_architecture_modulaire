package org.article.bo;

import eu.unareil.bo.produit.Produit;

import java.util.ArrayList;
import java.util.List;

public class Achat {
    double montant;
    List<Ligne> lignes = new ArrayList<>();

    // Constructor
    public Achat(Ligne ligne) {
        lignes.add(ligne);
    }

    // Getter
    public double getMontant() {
        return montant;
    }

    public Ligne getLigne(int index) {
        return lignes.get(index);
    }

    public List<Ligne> getLignes() {
        return lignes;
    }

    // Setter
    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setLignes(List<Ligne> lignes) {
        this.lignes = lignes;
    }

    // Methode
    public double calculMontant() {
        montant = 0;
        for (Ligne ligne : lignes) {
            montant += ligne.getQuantite() * ligne.getProduit().getPrixUnitaire();
        }
        return montant;
    }

    public void supprimeLigne(int index) {
        lignes.remove(index);
    }

    public void ajouteLigne(Produit produit, int qte) {
        lignes.add(new Ligne(produit, qte));
    }

    public void modifieLigne(int index, int nouvelleQte) {
        if (lignes.contains(lignes.get(index))) {
            lignes.get(index).setQuantite(nouvelleQte);
        }
    }

    // Methode
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("Achat : ").append("\n");
        for (Ligne ligne : lignes) {
            sb.append("\n");
            sb.append("ligne ").append((lignes.indexOf(ligne)) + 1).append(" : ");
            sb.append(ligne.toString());
        }
        sb.append("\n");
        sb.append("Total de l'achat : ").append(String.format("%.2f", calculMontant())).append(" euro").append((montant > 1) ? "s" : "");
        return sb.toString();
    }

}
