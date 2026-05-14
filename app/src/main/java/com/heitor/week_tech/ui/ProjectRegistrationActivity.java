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
import com.heitor.week_tech.data.local.entity.Project;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Activity simples para cadastrar projetos (formulário básico, validação simples).
 */
public class ProjectRegistrationActivity extends AppCompatActivity {

    private EditText etTitle;
    private EditText etDescription;
    private EditText etLocal;
    private EditText etHorario;
    private EditText etAuthor;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_registration);

        MaterialToolbar toolbar = findViewById(R.id.toolbarProject);
        toolbar.setNavigationOnClickListener(v -> finish());

        etTitle = findViewById(R.id.etProjectTitle);
        etDescription = findViewById(R.id.etProjectDescription);
        etLocal = findViewById(R.id.etProjectLocal);
        etHorario = findViewById(R.id.etProjectHorario);
        etAuthor = findViewById(R.id.etProjectAuthor);
        Button btnSubmit = findViewById(R.id.btnSubmitProject);

        btnSubmit.setOnClickListener(v -> {
            boolean valid = true;
            valid &= validateRequired(etTitle);
            valid &= validateRequired(etDescription);
            valid &= validateRequired(etLocal);
            valid &= validateRequired(etHorario);
            valid &= validateRequired(etAuthor);

            if (!valid) {
                Toast.makeText(this, R.string.form_invalid, Toast.LENGTH_SHORT).show();
                return;
            }

            String title = etTitle.getText().toString().trim();
            String description = etDescription.getText().toString().trim();
            String local = etLocal.getText().toString().trim();
            String horario = etHorario.getText().toString().trim();
            String author = etAuthor.getText().toString().trim();
            Project project = new Project(title, description, local, horario, author);
            executor.execute(() -> {
                try {
                    AppDatabase.getInstance(getApplicationContext()).projectDao().insert(project);
                    runOnUiThread(() -> {
                        Toast.makeText(this, R.string.project_registered_success, Toast.LENGTH_SHORT).show();
                        clearForm();
                    });
                } catch (Exception e) {
                    runOnUiThread(() -> Toast.makeText(this, "Erro ao salvar projeto", Toast.LENGTH_SHORT).show());
                }
            });
        });
    }

    private void clearForm() {
        etTitle.setText("");
        etDescription.setText("");
        etLocal.setText("");
        etHorario.setText("");
        etAuthor.setText("");
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
