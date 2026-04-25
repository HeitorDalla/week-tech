package com.heitor.week_tech.ui;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.heitor.week_tech.R;

/**
 * Activity para o Painel Administrativo (RF07).
 * Exibe estatísticas de inscrições e Coffee Break (RF08).
 */
public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button btnExitAdmin = findViewById(R.id.btnExitAdmin);

        // Retorna para a tela de Login
        btnExitAdmin.setOnClickListener(v -> {
            finish();
        });
    }
}