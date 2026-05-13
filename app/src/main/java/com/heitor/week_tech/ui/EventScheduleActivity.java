package com.heitor.week_tech.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.heitor.week_tech.R;

/**
 * Tela de programação de palestras do evento.
 */
public class EventScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventschedule);

        MaterialToolbar toolbar = findViewById(R.id.toolbarEventSchedule);
        toolbar.setNavigationOnClickListener(v -> finish());

        findViewById(R.id.verPalestrantes).setOnClickListener(v -> startActivity(new Intent(this, SpeakersActivity.class)));

        setupBottomNavigation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        if (bottomNav != null) {
            bottomNav.setSelectedItemId(R.id.nav_presence);
        }
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
}

