package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// الشاشة رقم 1: استمارة تسجيل المسافر
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ربط الشاشة بملف التصميم activity_main.xml
        setContentView(R.layout.activity_main);

        // ربط عناصر التصميم بالكود عن طريق الـ IDs
        EditText etNom = findViewById(R.id.et_nom_mr);
        EditText etNaissance = findViewById(R.id.et_naissance_mr);
        EditText etDestination = findViewById(R.id.et_destination_mr);
        RadioGroup rgMotif = findViewById(R.id.rg_motif_mr);
        Button btnInspecter = findViewById(R.id.btn_inspecter_mr);

        // ما يحدث عند الضغط على زر "Inspecter le profil"
        btnInspecter.setOnClickListener(v -> {

            // قراءة النصوص المكتوبة وحذف الفراغات من الطرفين
            String nom = etNom.getText().toString().trim();
            String naissance = etNaissance.getText().toString().trim();
            String destination = etDestination.getText().toString().trim();

            // التحقق من أن كل الحقول مملوءة، وإلا نعرض رسالة ونتوقف
            if (nom.isEmpty() || naissance.isEmpty() || destination.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            // معرفة زر الراديو المختار لتحديد سبب السفر
            String motif;
            int choisi = rgMotif.getCheckedRadioButtonId();
            if (choisi == R.id.rb_modification_mr) {
                motif = "Modification du passé";
            } else if (choisi == R.id.rb_fuite_mr) {
                motif = "Fuite";
            } else {
                motif = "Tourisme";
            }

            // إنشاء Intent صريح لفتح الشاشة رقم 2 مباشرة بالاسم
            Intent intent = new Intent(MainActivity.this, VerificationActivity.class);

            // إرسال البيانات بمفاتيح مبنية من الصنف Cles ولا نكتب المفاتيح يدوياً
            intent.putExtra(Cles.nomVoyageur(), nom);
            intent.putExtra(Cles.anneeNaissance(), Integer.parseInt(naissance));
            intent.putExtra(Cles.anneeDestination(), Integer.parseInt(destination));
            intent.putExtra(Cles.motif(), motif);

            // تشغيل الشاشة رقم 2
            startActivity(intent);
        });
    }
}