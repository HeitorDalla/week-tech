package com.heitor.week_tech.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.heitor.week_tech.R;
import com.heitor.week_tech.data.local.database.AppDatabase;
import com.heitor.week_tech.data.local.entity.Participant;
import com.heitor.week_tech.ui.adapter.ProjectAdapter;
import com.heitor.week_tech.ui.adapter.SpeakerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Activity para o Painel Administrativo (RF07).
 * Exibe estatísticas de inscrições e Coffee Break (RF08).
 */
public class AdminActivity extends AppCompatActivity {

    private static final String TAG = "AdminActivity";
    private ProjectAdapter projectAdapter;
    private SpeakerAdapter speakerAdapter;
    private TextView tvTotalInscriptions;
    private TextView tvTotalCoffee;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private static final int TAB_PROJECTS = 0;
    private static final int TAB_SPEAKERS = 1;
    private int currentTab = TAB_PROJECTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        MaterialToolbar toolbar = findViewById(R.id.toolbarAdmin);
        toolbar.setNavigationOnClickListener(v -> finish());

        tvTotalInscriptions = findViewById(R.id.tvTotalInscriptions);
        tvTotalCoffee = findViewById(R.id.tvTotalCoffee);
        RecyclerView rvParticipants = findViewById(R.id.rvParticipants);
        rvParticipants.setLayoutManager(new LinearLayoutManager(this));
        projectAdapter = new ProjectAdapter();
        speakerAdapter = new SpeakerAdapter();
        rvParticipants.setAdapter(projectAdapter);

        Button btnExitAdmin = findViewById(R.id.btnExitAdmin);
        BottomNavigationView adminBottomNavigation = findViewById(R.id.adminBottomNavigation);
        adminBottomNavigation.setSelectedItemId(R.id.nav_admin_projects);
        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
        adminBottomNavigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_admin_projects) {
                currentTab = TAB_PROJECTS;
                loadProjects();
                return true;
            } else if (id == R.id.nav_admin_speakers) {
                currentTab = TAB_SPEAKERS;
                loadSpeakers();
                return true;
            }
            currentTab = TAB_PROJECTS;
            loadProjects();
            return true;
        });

        fabAdd.setOnClickListener(v -> {
            if (currentTab == TAB_PROJECTS) {
                startActivity(new android.content.Intent(this, ProjectRegistrationActivity.class));
            } else if (currentTab == TAB_SPEAKERS) {
                startActivity(new android.content.Intent(this, SpeakerRegistrationActivity.class));
            } else {
                startActivity(new android.content.Intent(this, ProjectRegistrationActivity.class));
            }
        });

        // Retorna para a tela de Login
        btnExitAdmin.setOnClickListener(v -> finish());

        loadDashboardStats();
        loadProjects();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Recarrega de acordo com a aba atual
        loadDashboardStats();
        if (currentTab == TAB_PROJECTS) loadProjects();
        else loadSpeakers();
    }

    private void loadDashboardStats() {
        Log.d(TAG, "Carregando participantes do banco de dados...");
        executorService.execute(() -> {
            try {
                var participants = AppDatabase.getInstance(this).participantDao().getAllParticipants();
                Log.d(TAG, "Participantes carregados: " + participants.size());
                runOnUiThread(() -> {
                    tvTotalInscriptions.setText(getString(R.string.total_participants, participants.size()));
                    long coffeeCount = participants.stream().filter(Participant::isCoffeeBreak).count();
                    tvTotalCoffee.setText(getString(R.string.admin_coffee_confirmed, coffeeCount));
                });
            } catch (Exception e) {
                Log.e(TAG, "Erro ao carregar participantes", e);
            }
        });
    }

    private void loadProjects() {
        Log.d(TAG, "Carregando projetos do banco de dados...");
        executorService.execute(() -> {
            try {
                var projects = AppDatabase.getInstance(this).projectDao().getAllProjects();
                Log.d(TAG, "Projetos carregados: " + projects.size());
                runOnUiThread(() -> {
                    TextView tvListTitle = findViewById(R.id.tvListTitle);
                    tvListTitle.setText(getString(R.string.nav_admin_projects));
                    RecyclerView rv = findViewById(R.id.rvParticipants);
                    rv.setAdapter(projectAdapter);
                    projectAdapter.setProjects(projects);
                });
            } catch (Exception e) {
                Log.e(TAG, "Erro ao carregar projetos", e);
            }
        });
    }

    private void loadSpeakers() {
        Log.d(TAG, "Carregando palestrantes do banco de dados...");
        executorService.execute(() -> {
            try {
                var speakers = AppDatabase.getInstance(this).speakerDao().getAllSpeakers();
                Log.d(TAG, "Palestrantes carregados: " + speakers.size());
                runOnUiThread(() -> {
                    TextView tvListTitle = findViewById(R.id.tvListTitle);
                    tvListTitle.setText(getString(R.string.nav_admin_speakers));
                    RecyclerView rv = findViewById(R.id.rvParticipants);
                    rv.setAdapter(speakerAdapter);
                    speakerAdapter.setSpeakers(speakers);
                });
            } catch (Exception e) {
                Log.e(TAG, "Erro ao carregar palestrantes", e);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}