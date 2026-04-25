package com.heitor.week_tech;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity Principal que atende ao requisito RF01 (Informações do Evento).
 * Exibe a programação, palestrantes, projetos e patrocinadores.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRegister = findViewById(R.id.btnRegister);
        Button btnPresence = findViewById(R.id.btnPresence);
        Button btnContact = findViewById(R.id.btnContact);

        // RF02: Navegação para Inscrição
        btnRegister.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
        });

        // RF06: Navegação para Confirmação de Presença
        btnPresence.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, PresenceActivity.class));
        });

        // RF04 e RF05: Navegação para Contato e Localização
        btnContact.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ContactActivity.class));
        });
    }
}