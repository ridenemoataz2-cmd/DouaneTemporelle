package com.example.douanetemporelle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        View racine = findViewById(R.id.root_result_mr);
        TextView tvMessage = findViewById(R.id.tv_message_mr);
        Button btnNouveau = findViewById(R.id.btn_nouveau_mr);

        Intent recu = getIntent();
        boolean approuve = recu.getBooleanExtra(Cles.verdict(), false);
        String nom = recu.getStringExtra(Cles.nomVoyageur());

        if (approuve) {
            racine.setBackgroundColor(Color.parseColor("#2E7D32"));
            tvMessage.setText("Bon voyage " + nom + " !");
        } else {
            racine.setBackgroundColor(Color.parseColor("#C62828"));
            tvMessage.setText(nom + " est en état d'arrestation temporelle");
        }

        btnNouveau.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}