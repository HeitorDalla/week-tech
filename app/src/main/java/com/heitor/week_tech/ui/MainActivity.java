package com.heitor.week_tech.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.heitor.week_tech.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvHashtag = findViewById(R.id.tvHashtag);
        tvHashtag.setText(Html.fromHtml("#WEEK <font color='#00B4D8'>TECH</font>", Html.FROM_HTML_MODE_LEGACY));

        TextView tvMainTitle = findViewById(R.id.tvMainTitle);
        tvMainTitle.setText(Html.fromHtml("WEEK<br/>TECH<font color='#00B4D8'>.</font>", Html.FROM_HTML_MODE_LEGACY));

        setupBottomNavigation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Garante que o item "Início" esteja selecionado ao voltar para esta tela
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        bottomNav.setSelectedItemId(R.id.nav_home);
    }

    private void setupBottomNavigation() {
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        bottomNav.setSelectedItemId(R.id.nav_home);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_presence) {
                startActivity(new Intent(this, PresenceActivity.class));
                return true;
            } else if (id == R.id.nav_registration) {
                startActivity(new Intent(this, RegistrationActivity.class));
                return true;
            } else if (id == R.id.nav_contact) {
                startActivity(new Intent(this, ContactActivity.class));
                return true;
            }
            return id == R.id.nav_home;
        });
    }
}