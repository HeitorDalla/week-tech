package com.heitor.week_tech.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.heitor.week_tech.R;
import com.heitor.week_tech.data.local.database.AppDatabase;
import com.heitor.week_tech.data.local.entity.Project;
import com.heitor.week_tech.ui.adapter.ProjectAdapter;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Tela de programacao do evento.
 */
public class EventScheduleActivity extends AppCompatActivity {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private ProjectAdapter scheduleAdapter;
    private TextView tvScheduleEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventschedule);

        MaterialToolbar toolbar = findViewById(R.id.toolbarEventSchedule);
        toolbar.setNavigationOnClickListener(v -> finish());

        findViewById(R.id.verPalestrantes)
                .setOnClickListener(v -> startActivity(new Intent(this, SpeakersActivity.class)));

        setupScheduleList();
        setupBottomNavigation();
        loadScheduleFromDatabase();
    }

    @Override
    protected void onResume() {
        super.onResume();
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        if (bottomNav != null) {
            bottomNav.setSelectedItemId(R.id.nav_presence);
        }
        loadScheduleFromDatabase();
    }

    private void setupScheduleList() {
        RecyclerView rvEventSchedule = findViewById(R.id.rvEventSchedule);
        rvEventSchedule.setLayoutManager(new LinearLayoutManager(this));
        scheduleAdapter = new ProjectAdapter();
        rvEventSchedule.setAdapter(scheduleAdapter);
        tvScheduleEmpty = findViewById(R.id.tvScheduleEmpty);
    }

    private void loadScheduleFromDatabase() {
        executor.execute(() -> {
            List<Project> projects = AppDatabase.getInstance(getApplicationContext())
                    .projectDao()
                    .getAllProjects();

            runOnUiThread(() -> {
                scheduleAdapter.setProjects(projects);
                boolean isEmpty = projects == null || projects.isEmpty();
                tvScheduleEmpty.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
            });
        });
    }

    private void setupBottomNavigation() {
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        bottomNav.setSelectedItemId(R.id.nav_presence);

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.shutdown();
    }
}
