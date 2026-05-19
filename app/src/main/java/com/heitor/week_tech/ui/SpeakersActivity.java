package com.heitor.week_tech.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.heitor.week_tech.R;
import com.heitor.week_tech.data.local.database.AppDatabase;
import com.heitor.week_tech.ui.adapter.SpeakerAdapter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Tela que lista os palestrantes cadastrados no banco local.
 * Também mantém a navegação inferior igual às outras telas do app.
 */
public class SpeakersActivity extends AppCompatActivity {

    private SpeakerAdapter adapter;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speakers);

        // Configura a barra superior, a lista e a navegação inferior.
        setupToolbar();
        setupRecyclerView();
        setupBottomNavigation();
        loadSpeakers();
    }

    // Configura o título da tela e a ação de voltar.
    private void setupToolbar() {
        MaterialToolbar toolbar = findViewById(R.id.include_header);
        if (toolbar != null) {
            toolbar.setTitle(R.string.speakers_title);
            toolbar.setNavigationOnClickListener(v -> finish());
        }
    }

    // Prepara a RecyclerView que exibe os palestrantes.
    private void setupRecyclerView() {
        RecyclerView rvSpeakers = findViewById(R.id.rvSpeakers);
        rvSpeakers.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SpeakerAdapter();
        rvSpeakers.setAdapter(adapter);
    }

    // Busca os palestrantes no banco em uma thread de fundo.
    private void loadSpeakers() {
        executor.execute(() -> {
            var speakers = AppDatabase.getInstance(this).speakerDao().getAllSpeakers();
            runOnUiThread(() -> adapter.setSpeakers(speakers));
        });
    }

    // Navegação inferior usada para trocar de módulo sem recriar lógica de tela.
    private void setupBottomNavigation() {
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        if (bottomNav != null) {
            bottomNav.setOnItemSelectedListener(item -> {
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                    return true;
                } else if (id == R.id.nav_registration) {
                    startActivity(new Intent(this, RegistrationActivity.class));
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.shutdown();
    }
}
