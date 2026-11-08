package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VerificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        TextView tvNom = findViewById(R.id.tv_nom_mr);
        TextView tvAge = findViewById(R.id.tv_age_mr);
        TextView tvDestination = findViewById(R.id.tv_destination_mr);
        TextView tvMotif = findViewById(R.id.tv_motif_mr);
        Button btnApprouver = findViewById(R.id.btn_approuver_mr);
        Button btnRefuser = findViewById(R.id.btn_refuser_mr);

        Intent recu = getIntent();
        String nom = recu.getStringExtra(Cles.nomVoyageur());
        int naissance = recu.getIntExtra(Cles.anneeNaissance(), 0);
        int destination = recu.getIntExtra(Cles.anneeDestination(), 0);
        String motif = recu.getStringExtra(Cles.motif());

        int ageTemporel = destination - naissance;

        tvNom.setText("Nom : " + nom);
        tvAge.setText("Âge temporel : " + ageTemporel + " ans");
        tvDestination.setText("Destination : " + destination);
        tvMotif.setText("Motif : " + motif);

        if ("Modification du passé".equals(motif) && estPremier(ageTemporel)) {
            btnApprouver.setEnabled(false);
        }

        btnApprouver.setOnClickListener(v -> envoyerVerdict(true, nom));

        btnRefuser.setOnClickListener(v -> envoyerVerdict(false, nom));
    }

    private void envoyerVerdict(boolean approuve, String nom) {
        Intent intent = new Intent(VerificationActivity.this, ResultActivity.class);
        intent.putExtra(Cles.verdict(), approuve);
        intent.putExtra(Cles.nomVoyageur(), nom);
        startActivity(intent);
    }

    private boolean estPremier(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}