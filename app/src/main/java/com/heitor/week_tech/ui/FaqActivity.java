package com.heitor.week_tech.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.appbar.MaterialToolbar;
import com.heitor.week_tech.R;

/**
 * Tela de perguntas frequentes.
 * Serve para orientar o usuário com dúvidas comuns sobre o evento.
 */
public class FaqActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        // Toolbar com navegação de retorno.
        MaterialToolbar toolbar = findViewById(R.id.toolbarFaq);
        // Ajuste para voltar à última interação
        toolbar.setNavigationOnClickListener(v -> finish());
    }
}