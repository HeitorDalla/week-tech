package com.heitor.week_tech.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.appbar.MaterialToolbar;
import com.heitor.week_tech.R;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        MaterialToolbar toolbar = findViewById(R.id.toolbarContact);
        // Fix para a seta de voltar: encerra a activity atual e volta para a anterior
        toolbar.setNavigationOnClickListener(this::closeScreen);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        bottomNav.setSelectedItemId(R.id.nav_contact);
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
            } else if (id == R.id.nav_admin) {
                Intent intent = new Intent(this, LoginActivity.class);
                intent.putExtra(LoginActivity.EXTRA_REDIRECT_TO_ADMIN, true);
                startActivity(intent);
                finish();
                return true;
            }
            return true;
        });

        findViewById(R.id.btnFaq).setOnClickListener(v -> {
            startActivity(new Intent(this, FaqActivity.class));
        });
    }

    private void closeScreen(android.view.View view) {
        finish();
    }
}