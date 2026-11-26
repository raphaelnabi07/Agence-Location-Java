package test;

import clients.Client;

public class TestClients {

    public static void main(String[] args) {
        Client c1 = new Client();
        c1.afficher_infos();

        Client c2 = new Client("Dupont", "Claire", "PERMIS-1001", "0612345678", 1998);
        c2.afficher_infos();
        System.out.println("Âge de " + c2.getPrenom() + " en 2026 : " + c2.calculerAge(2026) + " ans");

        Client c3 = new Client(c2);
        System.out.println("Copie : " + c3);

        Client c4 = new Client("Martin", "Luc", "PERMIS-2002", "0698765432", 1985);
        c4.setTelephone("0700112233");
        System.out.println("Client 4 : " + c4);

        // Test d'égalité sur le numéro de permis
        System.out.println("c2 == c3 : " + (c2 == c3));
        System.out.println("c2.equals(c3) : " + c2.equals(c3));
        System.out.println("c2.equals(c4) : " + c2.equals(c4));
        System.out.println("Même hashCode (c2 / c3) : " + (c2.hashCode() == c3.hashCode()));
        System.out.println("Clients créés : " + Client.getCompteurClients());
    }
}