package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etNom = findViewById(R.id.et_nom_mr);
        EditText etNaissance = findViewById(R.id.et_naissance_mr);
        EditText etDestination = findViewById(R.id.et_destination_mr);
        RadioGroup rgMotif = findViewById(R.id.rg_motif_mr);
        Button btnInspecter = findViewById(R.id.btn_inspecter_mr);

        btnInspecter.setOnClickListener(v -> {
            String nom = etNom.getText().toString().trim();
            String naissance = etNaissance.getText().toString().trim();
            String destination = etDestination.getText().toString().trim();

            if (nom.isEmpty() || naissance.isEmpty() || destination.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            String motif;
            int choisi = rgMotif.getCheckedRadioButtonId();
            if (choisi == R.id.rb_modification_mr) {
                motif = "Modification du passé";
            } else if (choisi == R.id.rb_fuite_mr) {
                motif = "Fuite";
            } else {
                motif = "Tourisme";
            }

            Intent intent = new Intent(MainActivity.this, VerificationActivity.class);
            intent.putExtra(Cles.nomVoyageur(), nom);
            intent.putExtra(Cles.anneeNaissance(), Integer.parseInt(naissance));
            intent.putExtra(Cles.anneeDestination(), Integer.parseInt(destination));
            intent.putExtra(Cles.motif(), motif);
            startActivity(intent);
        });
    }
}