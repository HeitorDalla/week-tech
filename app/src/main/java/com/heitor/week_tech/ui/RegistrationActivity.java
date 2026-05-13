package com.heitor.week_tech.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;
import com.heitor.week_tech.R;
import com.heitor.week_tech.data.database.AppDatabase;
import com.heitor.week_tech.data.model.Participante;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Activity para o cadastro de participantes (RF02).
 * Atende aos requisitos RF08 (Coffee Break).
 */
public class RegistrationActivity extends AppCompatActivity {

    private static final String TAG = "RegistrationActivity";
    private TextInputEditText etName, etEmail, etCpf, etPhone, etRa, etCourse, etYear;
    private SwitchMaterial swCoffeeBreak;
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
        etRa = findViewById(R.id.etRa);
        etCourse = findViewById(R.id.etCourse);
        etYear = findViewById(R.id.etYear);
        swCoffeeBreak = findViewById(R.id.swCoffeeBreak);

        Button btnConfirm = findViewById(R.id.btnSubmitRegistration);
        btnConfirm.setOnClickListener(v -> saveParticipant());
    }

    private void saveParticipant() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String cpf = etCpf.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String ra = etRa.getText().toString().trim();
        String course = etCourse.getText().toString().trim();
        String year = etYear.getText().toString().trim();
        boolean coffeeBreak = swCoffeeBreak.isChecked();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(cpf) || 
            TextUtils.isEmpty(phone) || TextUtils.isEmpty(ra)) {
            Toast.makeText(this, "Por favor, preencha os campos obrigatórios (Nome, Email, CPF, Telefone e RA)", Toast.LENGTH_SHORT).show();
            return;
        }

        Participante participant = new Participante(name, email, cpf, phone);
        participant.setRa(ra);
        participant.setCurso(course);
        participant.setSerie(year);
        participant.setQuerCoffeeBreak(coffeeBreak);

        executorService.execute(() -> {
            try {
                AppDatabase.getInstance(this).participanteDao().insert(participant);
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