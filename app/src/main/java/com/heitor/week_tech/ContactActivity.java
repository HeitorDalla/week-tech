package com.heitor.week_tech;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity para Localização (RF04) e Contato/FAQ (RF05).
 */
public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Button btnOpenMaps = findViewById(R.id.btnOpenMaps);

        // RF04: Redireciona para o Google Maps com o endereço da universidade
        btnOpenMaps.setOnClickListener(v -> {
            String address = "Av. Guedner, 1610 - Maringá, PR";
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(address));
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        });
    }
}