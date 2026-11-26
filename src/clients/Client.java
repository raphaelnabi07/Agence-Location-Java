
package clients;

import java.util.Objects;

public class Client {

    private String nom;
    private String prenom;
    private String numeroPermis;
    private String telephone;
    private int anneeNaissance;
    private static int compteurClients;


    public Client() {

        nom = "N/A";
        prenom = "N/A";
        numeroPermis = "N/A";
        telephone = "N/A";
        anneeNaissance = 2000;
        System.out.println("Le constructeur par défaaut de Client est appelé");
        compteurClients = +compteurClients + 1;

    }

    public Client(String nom, String prenom, String numeroPermis, String telephone, int anneeNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.numeroPermis = numeroPermis;
        this.telephone = telephone;
        this.anneeNaissance = anneeNaissance;
        System.out.println("Le constructeur d'inititialisation de Client est appelé");
        compteurClients = +compteurClients + 1;
    }

    public Client(Client o) {
        this.nom = o.nom;
        this.prenom = o.prenom;
        this.numeroPermis = o.numeroPermis;
        this.telephone = o.telephone;
        this.anneeNaissance = o.anneeNaissance;
        System.out.println("Le constructeur de copie de Client est appelé");
        compteurClients = +compteurClients + 1;
    }

    //Méthodes

    public int calculerAge(int anneeCourante) {

        int operation = anneeCourante - anneeNaissance;
        return operation;

    }

    public void afficher_infos() {
        System.out.println("----- Informations du client -----");
        System.out.println("Nom              : " + nom);
        System.out.println("Prénom           : " + prenom);
        System.out.println("Numéro de permis : " + numeroPermis);
        System.out.println("Téléphone        : " + telephone);
        System.out.println("Année naissance  : " + anneeNaissance);
    }

    //Methode static

    public static int getCompteurClients() {
        return compteurClients;
    }


    //GETTERS


    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNumeroPermis() {
        return numeroPermis;
    }

    public String getTelephone() {
        return telephone;
    }

    public int getAnneeNaissance() {
        return anneeNaissance;
    }


    //SETTERS


    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setAnneeNaissance(int anneeNaissance) {
        this.anneeNaissance = anneeNaissance;
    }

    //Methodes redéfinie

    @Override
    public String toString() {
        return String.format("[%s] %s %s - Tel : %s - Ne(e) en %d",
                numeroPermis, prenom, nom, telephone, anneeNaissance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client autre = (Client) o;
        return this.numeroPermis.equals(autre.numeroPermis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroPermis);
    }

}



