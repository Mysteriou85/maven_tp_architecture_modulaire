package org.article.bo.produit.cartePostale;

import eu.unareil.bo.produit.Produit;

import java.util.ArrayList;
import java.util.List;

public class CartePostale extends Produit {
    TypeCartePostale type;
    List<Auteur> lesAuteurs = new ArrayList<>();

    // Constructor
    public CartePostale() {
        super();
    }
    public CartePostale(long refProd, String marque, String libelle, long qteStock, float prixUnitaire, List<Auteur> lesAuteurs, TypeCartePostale type) {
        super(refProd, marque, libelle, qteStock, prixUnitaire);
        this.setType(type);
        this.setLesAuteurs(lesAuteurs);
    }

    public CartePostale(String marque, String libelle, long qteStock, float prixUnitaire, List<Auteur> lesAuteurs, TypeCartePostale type) {
        super(marque, libelle, qteStock, prixUnitaire);
        this.type = type;
        this.lesAuteurs = lesAuteurs;
    }

    // Getter
    public TypeCartePostale getType() {
        return type;
    }

    public List<Auteur> getLesAuteurs() {
        return lesAuteurs;
    }

    // Setter
    public void setType(TypeCartePostale type) {
        this.type = type;
    }

    public void setLesAuteurs(List<Auteur> lesAuteurs) {
        this.lesAuteurs = lesAuteurs;
    }

    // Methode
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(super.toString()).append(", ");
        sb.append("auteurs=");
        for(Auteur auteur : lesAuteurs) {
            sb.append("auteur").append((lesAuteurs.indexOf(auteur))+1).append("=");
            sb.append(auteur.getNom()).append(" ").append(auteur.getPrenom()).append(", ");
        }
        sb.append("type=").append(type);
        sb.append(']');
        return sb.toString();
    }
}
