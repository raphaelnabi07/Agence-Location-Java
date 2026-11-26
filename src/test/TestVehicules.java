package test;

import vehicules.Vehicule;
import java.util.Scanner;

public class TestVehicules {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Vehicule v1 = new Vehicule();
        v1.afficherInfos();

        System.out.print("Marque du véhicule : ");
        String marque = sc.nextLine();
        System.out.print("Modèle du véhicule : ");
        String modele = sc.nextLine();

        Vehicule v2 = new Vehicule("AB-123-CD", marque, modele, 35.0);
        v2.setDisponible(true);
        v2.afficherInfos();

        v2.rouler(250);
        System.out.println("Après 250 km :");
        v2.afficherInfos();

        Vehicule v3 = new Vehicule(v2);
        v3.afficherInfos();

        // Vérification des contrôles dans les setters
        v2.setTarif(-20.0);
        v2.setKilometrage(-50);

        // Comparaison de références et d'immatriculations
        Vehicule v4 = new Vehicule("AB-123-CD", "Peugeot", "208", 40.0);
        System.out.println("v2 == v4 : " + (v2 == v4));
        System.out.println("v2.equals(v4) : " + v2.equals(v4));
        System.out.println("v2.equals(v1) : " + v2.equals(v1));
        System.out.println("Véhicules créés : " + Vehicule.getCompteurVehicules());

        Vehicule[] vehicules = {
                v2,
                new Vehicule("EF-456-GH", "Toyota", "Yaris", 38.0),
                new Vehicule("IJ-789-KL", "BMW", "Série 1", 75.0)
        };

        System.out.println("\nListe des véhicules :");
        for (Vehicule v : vehicules) {
            System.out.println(v);
        }

        sc.close();
    }
}