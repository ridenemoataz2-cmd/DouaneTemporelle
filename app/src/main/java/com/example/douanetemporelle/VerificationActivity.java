package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// الشاشة رقم 2: ماسح المفارقة (جواز السفر الزمني)
public class VerificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ربط الشاشة بملف التصميم activity_verification.xml
        setContentView(R.layout.activity_verification);

        // ربط عناصر النصوص والأزرار بالكود
        TextView tvNom = findViewById(R.id.tv_nom_mr);
        TextView tvAge = findViewById(R.id.tv_age_mr);
        TextView tvDestination = findViewById(R.id.tv_destination_mr);
        TextView tvMotif = findViewById(R.id.tv_motif_mr);
        Button btnApprouver = findViewById(R.id.btn_approuver_mr);
        Button btnRefuser = findViewById(R.id.btn_refuser_mr);

        // استقبال الـ Intent الذي أرسلته الشاشة رقم 1
        Intent recu = getIntent();

        // قراءة البيانات بنفس المفاتيح المستعملة عند الإرسال
        String nom = recu.getStringExtra(Cles.nomVoyageur());
        int naissance = recu.getIntExtra(Cles.anneeNaissance(), 0);
        int destination = recu.getIntExtra(Cles.anneeDestination(), 0);
        String motif = recu.getStringExtra(Cles.motif());

        // حساب العمر الزمني وهو الفرق بين سنة الوجهة وسنة الميلاد
        int ageTemporel = destination - naissance;

        // عرض البيانات داخل الجواز
        tvNom.setText("Nom : " + nom);
        tvAge.setText("Âge temporel : " + ageTemporel + " ans");
        tvDestination.setText("Destination : " + destination);
        tvMotif.setText("Motif : " + motif);

        // إذا كان السبب "تعديل الماضي" والفرق عدداً أولياً نعطّل زر الموافقة من البداية
        if ("Modification du passé".equals(motif) && estPremier(ageTemporel)) {
            btnApprouver.setEnabled(false);
        }

        // زر الموافقة: نرسل القرار true
        btnApprouver.setOnClickListener(v -> envoyerVerdict(true, nom));

        // زر الرفض: نرسل القرار false
        btnRefuser.setOnClickListener(v -> envoyerVerdict(false, nom));
    }

    // دالة تفتح الشاشة رقم 3 وترسل القرار واسم المسافر
    private void envoyerVerdict(boolean approuve, String nom) {
        Intent intent = new Intent(VerificationActivity.this, ResultActivity.class);
        intent.putExtra(Cles.verdict(), approuve);
        intent.putExtra(Cles.nomVoyageur(), nom);
        startActivity(intent);
    }

    // دالة تتحقق هل العدد أولي: أكبر من 1 ولا يقبل القسمة إلا على 1 وعلى نفسه
    private boolean estPremier(int n) {
        // الأعداد الأصغر من 2 ليست أولية
        if (n < 2) {
            return false;
        }
        // يكفي الفحص حتى الجذر التربيعي للعدد
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}