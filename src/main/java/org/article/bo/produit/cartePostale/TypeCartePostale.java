package org.article.bo.produit.cartePostale;

public enum TypeCartePostale {
    Paysage("Paysage"), Portrait("Portrait");

    private String libelle;

    TypeCartePostale(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
