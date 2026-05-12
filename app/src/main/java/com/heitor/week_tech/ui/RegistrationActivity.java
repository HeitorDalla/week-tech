package com.heitor.week_tech.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.heitor.week_tech.R;
import com.heitor.week_tech.data.database.AppDatabase;
import com.heitor.week_tech.data.model.Participante;
import com.heitor.week_tech.data.model.Projeto;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Activity para o cadastro de participantes (RF02).
 * Atende aos requisitos RF08 (Coffee Break) e RF09 (Cadastro de Projetos).
 */
public class RegistrationActivity extends AppCompatActivity {

    private static final String TAG = "RegistrationActivity";
    private TextInputEditText etName, etRA, etCourse, etGrade, etProjectName, etProjectDesc;
    private CheckBox cbCoffeeBreak, cbPresentProject;
    private LinearLayout layoutProjectFields;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        MaterialToolbar toolbar = findViewById(R.id.toolbarRegistration);
        if (toolbar != null) {
            toolbar.setNavigationOnClickListener(v -> finish());
        }

        etName = findViewById(R.id.etName);
        etRA = findViewById(R.id.etRA);
        etCourse = findViewById(R.id.etCourse);
        etGrade = findViewById(R.id.etGrade);
        cbCoffeeBreak = findViewById(R.id.cbCoffeeBreak);
        cbPresentProject = findViewById(R.id.cbPresentProject);
        layoutProjectFields = findViewById(R.id.layoutProjectFields);
        etProjectName = findViewById(R.id.etProjectName);
        etProjectDesc = findViewById(R.id.etProjectDesc);

        if (cbPresentProject != null && layoutProjectFields != null) {
            cbPresentProject.setOnCheckedChangeListener((buttonView, isChecked) -> {
                layoutProjectFields.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            });
        }

        Button btnConfirm = findViewById(R.id.btnSubmitRegistration);
        if (btnConfirm != null) {
            btnConfirm.setOnClickListener(v -> saveParticipant());
        }
    }

    private void saveParticipant() {
        String name = etName != null ? etName.getText().toString().trim() : "";
        String ra = etRA != null ? etRA.getText().toString().trim() : "";
        String course = etCourse != null ? etCourse.getText().toString().trim() : "";
        String grade = etGrade != null ? etGrade.getText().toString().trim() : "";

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(ra)) {
            Toast.makeText(this, "Por favor, preencha o nome e o RA", Toast.LENGTH_SHORT).show();
            return;
        }

        Participante participante = new Participante();
        participante.setNome(name);
        participante.setRa(ra);
        participante.setCurso(course);
        participante.setSerie(grade);
        participante.setQuerCoffeeBreak(cbCoffeeBreak != null && cbCoffeeBreak.isChecked());

        executorService.execute(() -> {
            try {
                AppDatabase db = AppDatabase.getInstance(this);
                db.participanteDao().insert(participante);

                if (cbPresentProject != null && cbPresentProject.isChecked()) {
                    String projectName = etProjectName != null ? etProjectName.getText().toString().trim() : "";
                    String projectDesc = etProjectDesc != null ? etProjectDesc.getText().toString().trim() : "";
                    if (!TextUtils.isEmpty(projectName)) {
                        Projeto projeto = new Projeto();
                        projeto.setNomeProjeto(projectName);
                        projeto.setDescricaoProjeto(projectDesc);
                        projeto.setNomeAutor(name);
                        projeto.setRaAutor(ra);
                        db.projetoDao().insert(projeto);
                    }
                }

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