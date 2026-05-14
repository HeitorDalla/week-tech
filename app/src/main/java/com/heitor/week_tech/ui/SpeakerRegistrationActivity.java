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
    private EditText etEmail;
    private EditText etPhone;
    private EditText etLink;
    private EditText etTopic;
    private EditText etDuration;
    private EditText etPhoto;
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
        etTopic = findViewById(R.id.etSpeakerTopic);
        etEmail = findViewById(R.id.etSpeakerEmail);
        etPhone = findViewById(R.id.etSpeakerPhone);
        etLink = findViewById(R.id.etSpeakerLink);
        etDuration = findViewById(R.id.etSpeakerDuration);
        etPhoto = findViewById(R.id.etSpeakerPhoto);
        Button btnSubmit = findViewById(R.id.btnSubmitSpeaker);

        btnSubmit.setOnClickListener(v -> {
            boolean valid = true;
            valid &= validateRequired(etName);
            valid &= validateRequired(etBio);
            valid &= validateRequired(etTopic);

            if (!valid) {
                Toast.makeText(this, R.string.form_invalid, Toast.LENGTH_SHORT).show();
                return;
            }

            String name = etName.getText().toString().trim();
            String bio = etBio.getText().toString().trim();
            String company = etCompany.getText().toString().trim();
            String topic = etTopic.getText() != null ? etTopic.getText().toString().trim() : "";
            String email = etEmail.getText() != null ? etEmail.getText().toString().trim() : "";
            String phone = etPhone.getText() != null ? etPhone.getText().toString().trim() : "";
            String link = etLink.getText() != null ? etLink.getText().toString().trim() : "";
            String duration = etDuration.getText() != null ? etDuration.getText().toString().trim() : "";
            String photo = etPhoto.getText() != null ? etPhoto.getText().toString().trim() : "";

            Speaker speaker = new Speaker(name, bio, company, email, phone, link, topic, duration, photo);
            executor.execute(() -> {
                try {
                    AppDatabase.getInstance(getApplicationContext()).speakerDao().insert(speaker);
                    runOnUiThread(() -> {
                        Toast.makeText(this, R.string.speaker_registered_success, Toast.LENGTH_SHORT).show();
                        clearForm();
                    });
                } catch (Exception e) {
                    runOnUiThread(() -> Toast.makeText(this, "Erro ao salvar palestrante", Toast.LENGTH_SHORT).show());
                }
            });
        });
    }

    private void clearForm() {
        etName.setText("");
        etBio.setText("");
        etCompany.setText("");
        etTopic.setText("");
        etEmail.setText("");
        etPhone.setText("");
        etLink.setText("");
        etDuration.setText("");
        etPhoto.setText("");
    }

    private boolean validateRequired(EditText field) {
        String value = field.getText() != null ? field.getText().toString().trim() : "";
        if (TextUtils.isEmpty(value)) {
            field.setError(getString(R.string.error_required));
            return false;
        }
        field.setError(null);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.shutdown();
    }
}


