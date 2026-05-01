package com.heitor.week_tech.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.heitor.week_tech.R;
import com.heitor.week_tech.adapter.ParticipanteAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity para o Painel Administrativo (RF07).
 * Exibe estatísticas de inscrições e Coffee Break (RF08).
 */
public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        setupStats();
        setupListaParticipantes();

        Button btnExitAdmin = findViewById(R.id.btnExitAdmin);
        btnExitAdmin.setOnClickListener(v -> finish());
    }

    private void setupStats() {
        TextView tvTotal = findViewById(R.id.tvTotalInscriptions);
        TextView tvCoffee = findViewById(R.id.tvTotalCoffee);

        // Dados estáticos para demonstração (RF08)
        tvTotal.setText("Total de Alunos: 3");
        tvCoffee.setText("Confirmados no Coffee Break: 2");
    }

    private void setupListaParticipantes() {
        RecyclerView rv = findViewById(R.id.rvParticipantes);
        rv.setLayoutManager(new LinearLayoutManager(this));

        List<Participante> list = new ArrayList<>();
        list.add(new Participante("Heitor Souza", "123456", true));
        list.add(new Participante("Maria Silva", "654321", true));
        list.add(new Participante("João Pereira", "112233", false));

        rv.setAdapter(new ParticipanteAdapter(list));
    }
}