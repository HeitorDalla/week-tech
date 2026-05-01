package com.heitor.week_tech.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.heitor.week_tech.R;
import com.heitor.week_tech.adapter.FAQAdapter;
import com.heitor.week_tech.adapter.PalestranteAdapter;
import com.heitor.week_tech.adapter.PatrocinadorAdapter;
import com.heitor.week_tech.adapter.ProjetoAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupNavigation();
        setupProgramacao();
        setupPalestrantes();
        setupProjetos();
        setupPatrocinadores();
    }

    private void setupNavigation() {
        Button btnRegister = findViewById(R.id.btnRegister);
        Button btnPresence = findViewById(R.id.btnPresence);
        Button btnContact = findViewById(R.id.btnContact);

        btnRegister.setOnClickListener(v -> startActivity(new Intent(this, RegistrationActivity.class)));
        btnPresence.setOnClickListener(v -> startActivity(new Intent(this, PresenceActivity.class)));
        btnContact.setOnClickListener(v -> startActivity(new Intent(this, ContactActivity.class)));
    }

    private void setupProgramacao() {
        RecyclerView rv = findViewById(R.id.rvProgramacao);
        rv.setLayoutManager(new LinearLayoutManager(this));
        
        List<Palestra> list = new ArrayList<>();
        list.add(new Palestra("Abertura Oficial", "19:00", "Auditório Principal"));
        list.add(new Palestra("O Futuro da IA", "20:00", "Auditório Principal"));
        
        rv.setAdapter(new FAQAdapter.PalestraAdapter(list));
    }

    private void setupPalestrantes() {
        RecyclerView rv = findViewById(R.id.rvPalestrantes);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        
        List<Palestrante> list = new ArrayList<>();
        list.add(new Palestrante("Dr. Silva", "Inteligência Artificial"));
        list.add(new Palestrante("Eng. Souza", "IoT e Cidades Inteligentes"));
        
        rv.setAdapter(new PalestranteAdapter(list));
    }

    private void setupProjetos() {
        RecyclerView rv = findViewById(R.id.rvProjetos);
        rv.setLayoutManager(new LinearLayoutManager(this));
        
        List<Projeto> list = new ArrayList<>();
        list.add(new Projeto("Robô Seguidor", "Grupo de Robótica"));
        list.add(new Projeto("App de Saúde", "Heitor Souza"));

        rv.setAdapter(new ProjetoAdapter(list));
    }

    private void setupPatrocinadores() {
        RecyclerView rv = findViewById(R.id.rvPatrocinadores);
        if (rv != null) {
            rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            List<Patrocinador> list = new ArrayList<>();
            list.add(new Patrocinador("Empresa A", R.drawable.ic_launcher_foreground));
            list.add(new Patrocinador("Empresa B", R.drawable.ic_launcher_foreground));
            rv.setAdapter(new PatrocinadorAdapter(list));
        }
    }
}