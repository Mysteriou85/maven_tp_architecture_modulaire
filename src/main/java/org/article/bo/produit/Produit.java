package org.article.bo.produit;

public class Produit {
    long refProd;
    String libelle;
    String marque;
    float prixUnitaire;
    long qteStock;

    // Constructor
    public Produit() {

    }

    public Produit(long refProd, String marque, String libelle, long qteStock, float prixUnitaire) {
        this.setRefProd(refProd);
        this.setLibelle(libelle);
        this.setMarque(marque);
        this.setPrixUnitaire(prixUnitaire);
        this.setQteStock(qteStock);
    }

    public Produit(String marque, String libelle, long qteStock, float prixUnitaire) {
        this(0, marque, libelle, qteStock, prixUnitaire);
    }

    // Getter
    public long getRefProd() {
        return refProd;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getMarque() {
        return marque;
    }

    public float getPrixUnitaire() {
        return prixUnitaire;
    }

    public long getQteStock() {
        return qteStock;
    }

    // Setter
    public void setRefProd(long refProd) {
        this.refProd = refProd;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setPrixUnitaire(float prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public void setQteStock(long qteStock) {
        this.qteStock = qteStock;
    }

    // Methode
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName()).append(" [");
        sb.append("libelle=").append(libelle);
        if (refProd != 0) {
            sb.append(", refProd=").append(refProd);
        }
        sb.append(", marque=").append(marque);
        sb.append(", prixUnitaire=").append(String.format("%.2f", prixUnitaire)).append(" euro").append((prixUnitaire > 1) ? "s" : "");
        sb.append(", qteStock=").append(qteStock);
        return sb.toString();
    }
}
