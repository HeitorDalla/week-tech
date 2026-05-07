package com.heitor.week_tech.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.heitor.week_tech.R;
import com.heitor.week_tech.data.local.database.AppDatabase;
import com.heitor.week_tech.ui.adapter.ParticipantAdapter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Activity para o Painel Administrativo (RF07).
 * Exibe estatísticas de inscrições e Coffee Break (RF08).
 */
public class AdminActivity extends AppCompatActivity {

    private static final String TAG = "AdminActivity";
    private RecyclerView rvParticipants;
    private ParticipantAdapter adapter;
    private TextView tvTotalInscriptions;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        tvTotalInscriptions = findViewById(R.id.tvTotalInscriptions);
        rvParticipants = findViewById(R.id.rvParticipants);
        rvParticipants.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ParticipantAdapter();
        rvParticipants.setAdapter(adapter);

        Button btnExitAdmin = findViewById(R.id.btnExitAdmin);

        // Retorna para a tela de Login
        btnExitAdmin.setOnClickListener(v -> {
            finish();
        });

        loadParticipants();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadParticipants();
    }

    private void loadParticipants() {
        Log.d(TAG, "Carregando participantes do banco de dados...");
        executorService.execute(() -> {
            try {
                var participants = AppDatabase.getInstance(this).participantDao().getAllParticipants();
                Log.d(TAG, "Participantes carregados: " + participants.size());
                runOnUiThread(() -> {
                    adapter.setParticipants(participants);
                    tvTotalInscriptions.setText(getString(R.string.total_participants, participants.size()));
                });
            } catch (Exception e) {
                Log.e(TAG, "Erro ao carregar participantes", e);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}