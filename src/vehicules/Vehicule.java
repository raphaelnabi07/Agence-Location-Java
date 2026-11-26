package vehicules;

public class Vehicule {

    private String immatriculation, marque, modele;
    private int kilometrage;
    private double tarifJournalier;
    private boolean disponible;
    private static int compteurVehicules;
    public static final String DEVISE = "EUR";

    /// ///Constructeurs

    public Vehicule() {

        immatriculation = "N/A";
        marque = "N/A";
        modele = "N/A";
        disponible = true;
        kilometrage = 0;
        tarifJournalier = 0.0;
        System.out.println("Constructeur par défaut de véhicule appelé");
        compteurVehicules++;
    }

    public Vehicule(String immat, String marque, String modele, double tarif) {

        this.immatriculation = immat;
        this.marque = marque;
        this.modele = modele;
        this.tarifJournalier = tarif;
        System.out.println("Constructeur d'initialisation de véhicule appelé");
        compteurVehicules++;
    }

    public Vehicule(Vehicule v) {
        this.immatriculation = v.immatriculation;
        this.marque = v.marque;
        this.modele = v.modele;
        this.kilometrage = v.kilometrage;
        this.tarifJournalier = v.tarifJournalier;
        this.disponible = v.disponible;
        System.out.println("Constructeur de copie de véhicule appelé");
        compteurVehicules++;
    }

    /// ///Méthodes

    public void rouler(int km) {
        this.kilometrage += km;
    }

    public void afficherInfos() {
        System.out.println("Immatriculation : " + immatriculation
                + " | Marque : " + marque
                + " | Modèle : " + modele
                + " | Disponible : " + (disponible ? "Oui" : "Non")
                + " | Kilométrage : " + kilometrage + " km"
                + " | Tarif : " + tarifJournalier + " €/jour");
    }

    public static int getCompteurVehicules() {
        return compteurVehicules;
    }

    /// ///Getters et Setters

    public String getImmatriculation() {
        return immatriculation;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(int kilometrage) {
        if (kilometrage < 0) {
            System.out.println("Erreur : le kilométrage ne peut pas être négatif.");
            return;
        }
        this.kilometrage = kilometrage;
    }

    public double getTarif() {
        return tarifJournalier;
    }

    public void setTarif(double tarif) {
        if (tarif < 0) {
            System.out.println("Erreur : le tarif ne peut pas être négatif.");
            return;
        }
        this.tarifJournalier = tarif;
    }


    /// ///Redéfinitions

// Redéfinitions
    @Override
    public String toString() {
        return String.format("[%s] %s %s - %d km - %.2f %s/jour - %s",
                immatriculation, marque, modele, kilometrage, tarifJournalier, DEVISE,
                disponible ? "DISPONIBLE" : "INDISPONIBLE");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vehicule autre = (Vehicule) obj;
        return this.immatriculation.equals(autre.immatriculation);
    }

    @Override
    public int hashCode() {
        return immatriculation != null ? immatriculation.hashCode() : 0;
    }
}