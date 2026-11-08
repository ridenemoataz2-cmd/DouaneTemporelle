package com.example.douanetemporelle;

// صنف مساعد لبناء مفاتيح putExtra، وكل مفتاح يبدأ برقم 3641
public class Cles {

    // آخر 4 أرقام من رقم الترسيم، وهي مكتوبة في مكان واحد فقط
    public static final String MATRICULE = "3641";

    // دالة ثابتة تأخذ الرقم والاسم وتبني المفتاح بالشكل 3641_NOM
    public static String cle(String matricule, String nom) {
        return matricule + "_" + nom;
    }

    // مفتاح اسم المسافر
    public static String nomVoyageur() {
        return cle(MATRICULE, "NOM_VOYAGEUR");
    }

    // مفتاح سنة الميلاد
    public static String anneeNaissance() {
        return cle(MATRICULE, "ANNEE_NAISSANCE");
    }

    // مفتاح سنة الوجهة
    public static String anneeDestination() {
        return cle(MATRICULE, "ANNEE_DESTINATION");
    }

    // مفتاح سبب السفر
    public static String motif() {
        return cle(MATRICULE, "MOTIF");
    }

    // مفتاح القرار النهائي (true للموافقة و false للرفض)
    public static String verdict() {
        return cle(MATRICULE, "VERDICT");
    }
}