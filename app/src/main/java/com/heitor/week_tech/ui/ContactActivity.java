package com.heitor.week_tech.ui;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.appbar.MaterialToolbar;
import com.heitor.week_tech.R;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        MaterialToolbar toolbar = findViewById(R.id.toolbarContact);
        // Fix para a seta de voltar: encerra a activity atual e volta para a anterior
        toolbar.setNavigationOnClickListener(v -> finish());

        // Botão para FAQ (estilizado conforme novo layout)
        findViewById(R.id.btnFaq).setOnClickListener(v -> {
            Toast.makeText(this, "Em breve: Perguntas Frequentes", Toast.LENGTH_SHORT).show();
        });
    }
}