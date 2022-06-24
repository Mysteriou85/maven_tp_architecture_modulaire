package org.article.bo.produit.produitPerissable;

import java.time.LocalDate;

public class Pain extends ProduitPerissable{

    float poids;

    // Constructor
    public Pain() {
        super();
    }

    public Pain(long refProd, String marque, String libelle, long qteStock, float prixUnitaire, float poids) {
        super(refProd, marque, libelle, qteStock, prixUnitaire, LocalDate.now().plusDays(2));
        this.setPoids(poids);
    }

    public Pain(String marque, String libelle, float poids, long qteStock, float prixUnitaire) {
        this(0, marque, libelle, qteStock, prixUnitaire, poids);
    }

    // Getter
    public float getPoids() {
        return poids;
    }

    // Setter
    public void setPoids(float poids) {
        this.poids = poids;
    }

    // Methode
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(super.toString()).append(", ");
        sb.append("poids=").append(poids).append("g");
        sb.append(']');
        return sb.toString();
    }
}
