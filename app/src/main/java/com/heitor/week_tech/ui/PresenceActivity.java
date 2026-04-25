package com.heitor.week_tech.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.heitor.week_tech.R;

/**
 * Activity para Confirmação de Presença (RF06).
 */
public class PresenceActivity extends AppCompatActivity {

    private TextInputEditText etPresenceCode;
    private Button btnConfirmPresence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presence);

        etPresenceCode = findViewById(R.id.etPresenceCode);
        btnConfirmPresence = findViewById(R.id.btnConfirmPresence);

        btnConfirmPresence.setOnClickListener(v -> {
            String code = etPresenceCode.getText().toString();
            if (code.isEmpty()) {
                Toast.makeText(this, "Por favor, insira o código da palestra", Toast.LENGTH_SHORT).show();
            } else {
                // RF06: Lógica para registrar a presença
                Toast.makeText(this, "Presença confirmada! Obrigado por participar.", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}