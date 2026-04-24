package com.heitor.week_tech;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity para o cadastro de participantes (RF02).
 * Inclui campos obrigatórios e opção de Coffee Break (RF08).
 */
public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Define o layout de cadastro
        setContentView(R.layout.activity_registration);

        Button btnConfirm = findViewById(R.id.btnConfirmRegistration);

        // Lógica de confirmação de inscrição
        btnConfirm.setOnClickListener(v -> {
            // No futuro, aqui os dados serão salvos no banco de dados (RNF07)
            Toast.makeText(this, "Inscrição confirmada com sucesso!", Toast.LENGTH_LONG).show();
            // Fecha a tela e volta para a principal
            finish();
        });
    }
}