package com.example.douanetemporelle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// الشاشة رقم 3: الحكم النهائي
public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ربط الشاشة بملف التصميم activity_result.xml
        setContentView(R.layout.activity_result);

        // ربط العناصر بالكود: الخلفية والرسالة والزر
        View racine = findViewById(R.id.root_result_mr);
        TextView tvMessage = findViewById(R.id.tv_message_mr);
        Button btnNouveau = findViewById(R.id.btn_nouveau_mr);

        // استقبال القرار واسم المسافر من الشاشة رقم 2
        Intent recu = getIntent();
        boolean approuve = recu.getBooleanExtra(Cles.verdict(), false);
        String nom = recu.getStringExtra(Cles.nomVoyageur());

        if (approuve) {
            // حالة الموافقة: خلفية خضراء ورسالة ترحيب
            racine.setBackgroundColor(Color.parseColor("#2E7D32"));
            tvMessage.setText("Bon voyage " + nom + " !");
        } else {
            // حالة الرفض: خلفية حمراء ورسالة اعتقال
            racine.setBackgroundColor(Color.parseColor("#C62828"));
            tvMessage.setText(nom + " est en état d'arrestation temporelle");
        }

        // زر "Nouveau voyageur": نعود إلى الشاشة رقم 1 ونغلق كل الشاشات السابقة
        btnNouveau.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            // هذان الـ flags يمسحان كل الشاشات القديمة ويبدآن مهمة جديدة
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}