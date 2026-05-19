package com.heitor.week_tech.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.heitor.week_tech.R;

/**
 * Tela inicial do aplicativo.
 * Mostra a apresentação do evento, botões de acesso rápido, mapa e navegação inferior.
 */
public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Texto destacado do topo da tela.
        TextView tvHashtag = findViewById(R.id.tvHashtag);
        tvHashtag.setText(Html.fromHtml("#WEEK <font color='#00B4D8'>TECH</font>", Html.FROM_HTML_MODE_LEGACY));

        // Título principal do evento, montado com quebra de linha e destaque de cor.
        TextView tvMainTitle = findViewById(R.id.tvMainTitle);
        tvMainTitle.setText(Html.fromHtml("WEEK<br/>TECH<font color='#00B4D8'>.</font>", Html.FROM_HTML_MODE_LEGACY));

        // Botão para abrir a programação.
        Button btnProgramacao = findViewById(R.id.btnProgramacao);
        btnProgramacao.setOnClickListener(v -> startActivity(new Intent(this, EventScheduleActivity.class)));

        // Botão para abrir a confirmação de presença.
        MaterialButton btnCheckPresence = findViewById(R.id.btnCheckPresence);
        btnCheckPresence.setOnClickListener(v -> startActivity(new Intent(this, PresenceActivity.class)));

        // Configura a navegação inferior do app.
        setupBottomNavigation();

        // Inicializa o mapa apenas se o serviço estiver disponível no aparelho.
        initializeMapIfAvailable();
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

        // Cada item da barra inferior abre uma tela do aplicativo.
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_presence) {
                startActivity(new Intent(this, EventScheduleActivity.class));
                return true;
            } else if (id == R.id.nav_registration) {
                startActivity(new Intent(this, RegistrationActivity.class));
                return true;
            } else if (id == R.id.nav_contact) {
                startActivity(new Intent(this, ContactActivity.class));
                return true;
            } else if (id == R.id.nav_admin) {
                Intent intent = new Intent(this, LoginActivity.class);
                intent.putExtra(LoginActivity.EXTRA_REDIRECT_TO_ADMIN, true);
                startActivity(intent);
                return true;
            }
            return id == R.id.nav_home;
        });
    }

    private void initializeMapIfAvailable() {
        // Verifica se o Google Play Services está disponível antes de criar o mapa.
        int status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        if (status != ConnectionResult.SUCCESS) {
            return;
        }

        if (getSupportFragmentManager().findFragmentByTag("map_fragment") == null) {
            try {
                SupportMapFragment mapFragment = SupportMapFragment.newInstance();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.map_container, mapFragment, "map_fragment")
                        .commitNowAllowingStateLoss();
                mapFragment.getMapAsync(this);
            } catch (RuntimeException ignored) {
                // Keep the home screen usable even if Maps cannot be initialized.
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Coordenadas do local do evento.
        LatLng eventLocation = new LatLng(-23.303736829061563, -51.141027544219284);
        mMap.addMarker(new com.google.android.gms.maps.model.MarkerOptions().position(eventLocation).title("Unicesumar"));
        mMap.moveCamera(com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom(eventLocation, 15));
    }
}