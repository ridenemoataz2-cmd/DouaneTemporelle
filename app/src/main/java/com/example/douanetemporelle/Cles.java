package com.example.douanetemporelle;

public class Cles {

    public static final String MATRICULE = "3641";

    public static String cle(String matricule, String nom) {
        return matricule + "_" + nom;
    }

    public static String nomVoyageur() {
        return cle(MATRICULE, "NOM_VOYAGEUR");
    }

    public static String anneeNaissance() {
        return cle(MATRICULE, "ANNEE_NAISSANCE");
    }

    public static String anneeDestination() {
        return cle(MATRICULE, "ANNEE_DESTINATION");
    }

    public static String motif() {
        return cle(MATRICULE, "MOTIF");
    }

    public static String verdict() {
        return cle(MATRICULE, "VERDICT");
    }
}