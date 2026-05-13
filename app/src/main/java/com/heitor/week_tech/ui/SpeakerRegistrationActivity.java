package com.heitor.week_tech.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.heitor.week_tech.R;
import com.heitor.week_tech.data.local.database.AppDatabase;
import com.heitor.week_tech.data.local.entity.Speaker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Activity simples para cadastrar palestrantes (formulário básico, validação simples).
 */
public class SpeakerRegistrationActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etBio;
    private EditText etCompany;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker_registration);

        MaterialToolbar toolbar = findViewById(R.id.toolbarSpeaker);
        toolbar.setNavigationOnClickListener(v -> finish());

        etName = findViewById(R.id.etSpeakerName);
        etBio = findViewById(R.id.etSpeakerBio);
        etCompany = findViewById(R.id.etSpeakerCompany);
        Button btnSubmit = findViewById(R.id.btnSubmitSpeaker);

        btnSubmit.setOnClickListener(v -> {
            boolean valid = true;
            if (TextUtils.isEmpty(etName.getText())) {
                etName.setError(getString(R.string.error_required));
                valid = false;
            }

            if (!valid) {
                Toast.makeText(this, R.string.form_invalid, Toast.LENGTH_SHORT).show();
                return;
            }

            String name = etName.getText().toString().trim();
            String bio = etBio.getText() != null ? etBio.getText().toString().trim() : "";
            String company = etCompany.getText() != null ? etCompany.getText().toString().trim() : "";
            Speaker speaker = new Speaker(name, bio, company);
            executor.execute(() -> {
                try {
                    AppDatabase.getInstance(getApplicationContext()).speakerDao().insert(speaker);
                    runOnUiThread(() -> {
                        Toast.makeText(this, R.string.speaker_registered_success, Toast.LENGTH_SHORT).show();
                        etName.setText("");
                        etBio.setText("");
                        etCompany.setText("");
                    });
                } catch (Exception e) {
                    runOnUiThread(() -> Toast.makeText(this, "Erro ao salvar palestrante", Toast.LENGTH_SHORT).show());
                }
            });
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.shutdown();
    }
}


