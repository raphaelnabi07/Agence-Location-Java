package test;

import vehicules.*;

public class TestVehicules {

    public static void main(String[] args) {

        Voiture voiture = new Voiture(
                "AA-111-BB",
                "voiture1",
                "modele1",
                45.0,
                5,
                "Essence",
                false
        );

        Moto moto = new Moto(
                "BB-222-CC",
                "moto1",
                "model1",
                30.0,
                689
        );

        System.out.println(voiture);
        System.out.println(moto);

        System.out.println("Permis A2/A requis : ");
    }
}
