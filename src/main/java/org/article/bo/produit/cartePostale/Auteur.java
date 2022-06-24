package org.article.bo.produit.cartePostale;

public class Auteur {

    private long id;
    private String prenom;
    private String nom;

    // Constructor
    public Auteur() {
    }

    public Auteur(String prenom, String nom) {
        this.setPrenom(prenom);
        this.setNom(nom);
    }

    public Auteur(long id, String prenom, String nom) {
        this(prenom, nom);
        this.setId(id);
    }

    // Getter
    public long getId() { return id;}
    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    // Setter
    public void setId(long id) { this.id = id; }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // Methode
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("Auteur [");
        sb.append("prenom=").append(prenom);
        sb.append(", nom=").append(nom);
        sb.append(']');
        return sb.toString();
    }

}
