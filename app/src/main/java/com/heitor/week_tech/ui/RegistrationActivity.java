package com.heitor.week_tech.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.switchmaterial.SwitchMaterial;
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
    private TextInputEditText etName, etEmail, etCpf, etPhone, etCourse, etPeriod;
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
        etCourse = findViewById(R.id.etCourse);
        etPeriod = findViewById(R.id.etPeriod);
        swCoffeeBreak = findViewById(R.id.swCoffeeBreak);

        Button btnConfirm = findViewById(R.id.btnSubmitRegistration);
        setupBottomNavigation();

        // Lógica de confirmação de inscrição
        btnConfirm.setOnClickListener(v -> saveParticipant());
    }

    @Override
    protected void onResume() {
        super.onResume();
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        if (bottomNav != null) {
            bottomNav.setSelectedItemId(R.id.nav_registration);
        }
    }

    private void setupBottomNavigation() {
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        bottomNav.setSelectedItemId(R.id.nav_registration);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
                return true;
            } else if (id == R.id.nav_presence) {
                startActivity(new Intent(this, EventScheduleActivity.class));
                finish();
                return true;
            } else if (id == R.id.nav_contact) {
                startActivity(new Intent(this, ContactActivity.class));
                finish();
                return true;
            } else if (id == R.id.nav_admin) {
                Intent intent = new Intent(this, LoginActivity.class);
                intent.putExtra(LoginActivity.EXTRA_REDIRECT_TO_ADMIN, true);
                startActivity(intent);
                finish();
                return true;
            }
            return true;
        });
    }

    private void saveParticipant() {
        String name = getText(etName);
        String email = getText(etEmail);
        String cpf = getText(etCpf);
        String phone = getText(etPhone);
        String course = getText(etCourse);
        String period = getText(etPeriod);
        boolean coffeeBreak = swCoffeeBreak.isChecked();

        Log.d(TAG, "Tentativa de salvar participante: " + name);

        if (TextUtils.isEmpty(name)
                || TextUtils.isEmpty(email)
                || TextUtils.isEmpty(cpf)
                || TextUtils.isEmpty(phone)
                || TextUtils.isEmpty(course)
                || TextUtils.isEmpty(period)) {
            Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Participant participant = new Participant(name, email, cpf, phone, course, period, coffeeBreak);

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

    private String getText(TextInputEditText editText) {
        return editText.getText() != null ? editText.getText().toString().trim() : "";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}