package com.heitor.week_tech.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.heitor.week_tech.R;
import com.heitor.week_tech.data.database.AppDatabase;
import com.heitor.week_tech.data.model.Presenca;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Activity para Confirmação de Presença (RF06).
 */
public class PresenceActivity extends AppCompatActivity {

    private static final String TAG = "PresenceActivity";
    private TextInputEditText etPresenceCode;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presence);

        MaterialToolbar toolbar = findViewById(R.id.toolbarPresence);
        toolbar.setNavigationOnClickListener(v -> finish());

        etPresenceCode = findViewById(R.id.etPresenceCode);
        Button btnConfirmPresence = findViewById(R.id.btnConfirmPresence);

        btnConfirmPresence.setOnClickListener(v -> {
            String code = etPresenceCode.getText().toString().trim();
            if (code.isEmpty()) {
                Toast.makeText(this, "Por favor, insira o código da palestra", Toast.LENGTH_SHORT).show();
            } else {
                savePresence(code);
            }
        });
    }

    private void savePresence(String code) {
        // RF06: Lógica para registrar a presença
        // Como o código manual é um mock, vamos simular que o código representa o ID da palestra
        executorService.execute(() -> {
            try {
                // Mock: assumindo que o participante logado tem ID 1 (em um app real teríamos o ID do usuário logado)
                // E que o código inserido é o ID da palestra.
                long palestraId;
                try {
                    palestraId = Long.parseLong(code);
                } catch (NumberFormatException e) {
                    palestraId = 1L; // Fallback para exemplo
                }

                Presenca presenca = new Presenca();
                presenca.setParticipanteId(1L); // Mock
                presenca.setPalestraId(palestraId);
                presenca.setConfirmada(true);

                AppDatabase.getInstance(this).presencaDao().insert(presenca);

                runOnUiThread(() -> {
                    Toast.makeText(this, "Presença confirmada! Obrigado por participar.", Toast.LENGTH_LONG).show();
                    finish();
                });
            } catch (Exception e) {
                Log.e(TAG, "Erro ao registrar presença", e);
                runOnUiThread(() -> Toast.makeText(this, "Erro ao registrar presença.", Toast.LENGTH_SHORT).show());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}