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
import com.heitor.week_tech.ui.adapter.ParticipantAdapter;
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
    private ParticipantAdapter participantAdapter;
    private ProjectAdapter projectAdapter;
    private SpeakerAdapter speakerAdapter;
    private TextView tvTotalInscriptions;
    private TextView tvTotalCoffee;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private static final int TAB_PARTICIPANTS = 0;
    private static final int TAB_PROJECTS = 1;
    private static final int TAB_SPEAKERS = 2;
    private int currentTab = TAB_PARTICIPANTS;

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
        participantAdapter = new ParticipantAdapter();
        projectAdapter = new ProjectAdapter();
        speakerAdapter = new SpeakerAdapter();
        rvParticipants.setAdapter(participantAdapter);

        Button btnExitAdmin = findViewById(R.id.btnExitAdmin);
        BottomNavigationView adminBottomNavigation = findViewById(R.id.adminBottomNavigation);
        adminBottomNavigation.setSelectedItemId(R.id.nav_admin_participants);
        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
        adminBottomNavigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_admin_participants) {
                currentTab = TAB_PARTICIPANTS;
                loadParticipants();
                return true;
            } else if (id == R.id.nav_admin_projects) {
                // Abre a tela de cadastro de projetos diretamente
                startActivity(new android.content.Intent(this, ProjectRegistrationActivity.class));
                return false;
            } else if (id == R.id.nav_admin_speakers) {
                // Abre a tela de cadastro de palestrantes diretamente
                startActivity(new android.content.Intent(this, SpeakerRegistrationActivity.class));
                return false;
            }
            currentTab = TAB_PARTICIPANTS;
            loadParticipants();
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
        loadParticipants();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Mantem dashboard e lista de participantes sempre atualizados ao voltar dos cadastros
        loadDashboardStats();
        loadParticipants();
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

    private void loadParticipants() {
        Log.d(TAG, "Carregando lista de participantes...");
        executorService.execute(() -> {
            try {
                var participants = AppDatabase.getInstance(this).participantDao().getAllParticipants();
                Log.d(TAG, "Participantes carregados para listagem: " + participants.size());
                runOnUiThread(() -> {
                    TextView tvListTitle = findViewById(R.id.tvListTitle);
                    tvListTitle.setText(getString(R.string.admin_list));
                    RecyclerView rv = findViewById(R.id.rvParticipants);
                    rv.setAdapter(participantAdapter);
                    participantAdapter.setParticipants(participants);
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