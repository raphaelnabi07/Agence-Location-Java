package test;

import clients.*;

public class TestClients {

    public static void main(String[] args) {

        Particulier p =
                new Particulier(
                 "exemple particulier infos"
                );

        Entreprise e =
                new Entreprise(
                    "exemple entreprise infos"
                );

        System.out.println(p);
        System.out.println(e);

        System.out.println(
                "Remise particulier : "
                        + p.calculerRemise()
        );

        System.out.println(
                "Remise entreprise : "
                        + e.calculerRemise()
        );
    }
}