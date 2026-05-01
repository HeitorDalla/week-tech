package com.heitor.week_tech.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.heitor.week_tech.R;
import com.heitor.week_tech.adapter.FAQAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity para Localização (RF04) e Contato/FAQ (RF05).
 */
public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        setupMaps();
        setupFAQ();
    }

    private void setupMaps() {
        Button btnOpenMaps = findViewById(R.id.btnOpenMaps);

        btnOpenMaps.setOnClickListener(v -> {
            String address = "Av. Guedner, 1610 - Maringá, PR";
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(address));
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            
            try {
                startActivity(mapIntent);
            } catch (Exception e) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW, 
                    Uri.parse("https://www.google.com/maps/search/?api=1&query=" + Uri.encode(address)));
                startActivity(webIntent);
            }
        });
    }

    private void setupFAQ() {
        RecyclerView rvFaq = findViewById(R.id.rvFaq);
        rvFaq.setLayoutManager(new LinearLayoutManager(this));

        // Sendo explícito no uso do FAQ da pasta ui para evitar conflitos de tipos
        List<com.heitor.week_tech.ui.FAQ> faqList = new ArrayList<>();
        faqList.add(new com.heitor.week_tech.ui.FAQ("Como confirmo minha presença?", "Ao final de cada palestra, um QR Code será disponibilizado na tela."));
        faqList.add(new com.heitor.week_tech.ui.FAQ("Haverá certificado?", "Sim, para todos os participantes com presença confirmada."));
        faqList.add(new com.heitor.week_tech.ui.FAQ("O evento é gratuito?", "Sim, totalmente gratuito para os alunos da instituição."));
        faqList.add(new com.heitor.week_tech.ui.FAQ("Onde vejo os projetos?", "Na tela principal, logo abaixo da lista de palestrantes."));

        // O FAQAdapter agora receberá a lista correta
        FAQAdapter adapter = new FAQAdapter(faqList);
        rvFaq.setAdapter(adapter);
    }
}