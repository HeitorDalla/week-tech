package com.heitor.week_tech.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.heitor.week_tech.R;
import com.heitor.week_tech.data.local.database.AppDatabase;
import com.heitor.week_tech.data.local.entity.Participant;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Activity para o cadastro de participantes (RF02).
 * Atende aos requisitos RF08 (Coffee Break) e RF09 (Cadastro de Projetos).
 */
public class RegistrationActivity extends AppCompatActivity {

    private static final String TAG = "RegistrationActivity";
    private TextInputEditText etName, etEmail, etCpf, etPhone;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        MaterialToolbar toolbar = findViewById(R.id.toolbarRegistration);
        toolbar.setNavigationOnClickListener(v -> finish());

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etCpf = findViewById(R.id.etCpf);
        etPhone = findViewById(R.id.etPhone);

        Button btnConfirm = findViewById(R.id.btnSubmitRegistration);

        // Lógica de confirmação de inscrição
        btnConfirm.setOnClickListener(v -> {
            saveParticipant();
        });
    }

    private void saveParticipant() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String cpf = etCpf.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();

        Log.d(TAG, "Tentativa de salvar participante: " + name);

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(cpf) || TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Participant participant = new Participant(name, email, cpf, phone);

        executorService.execute(() -> {
            try {
                AppDatabase.getInstance(this).participantDao().insert(participant);
                Log.d(TAG, "Participante salvo com sucesso no Room");
                runOnUiThread(() -> {
                    Toast.makeText(this, "Inscrição confirmada com sucesso!", Toast.LENGTH_LONG).show();
                    finish();
                });
            } catch (Exception e) {
                Log.e(TAG, "Erro ao salvar participante", e);
                runOnUiThread(() -> Toast.makeText(this, "Erro ao salvar: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}