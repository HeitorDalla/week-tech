package com.heitor.week_tech;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity para o cadastro de participantes (RF02).
 * Atende aos requisitos RF08 (Coffee Break) e RF09 (Cadastro de Projetos).
 */
public class RegistrationActivity extends AppCompatActivity {

    private CheckBox cbPresentProject;
    private LinearLayout layoutProjectFields;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        cbPresentProject = findViewById(R.id.cbPresentProject);
        layoutProjectFields = findViewById(R.id.layoutProjectFields);
        Button btnConfirm = findViewById(R.id.btnConfirmRegistration);

        // RF09: Exibe ou oculta campos de projeto dinamicamente
        cbPresentProject.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                layoutProjectFields.setVisibility(View.VISIBLE);
            } else {
                layoutProjectFields.setVisibility(View.GONE);
            }
        });

        // Lógica de confirmação de inscrição
        btnConfirm.setOnClickListener(v -> {
            // No futuro, os dados serão salvos para o relatório administrativo (RF07)
            Toast.makeText(this, "Inscrição confirmada com sucesso!", Toast.LENGTH_LONG).show();
            finish();
        });
    }
}