package com.heitor.week_tech.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.appbar.MaterialToolbar;
import com.heitor.week_tech.R;
import com.heitor.week_tech.data.database.AppDatabase;
import com.heitor.week_tech.data.model.InformacaoEvento;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContactActivity extends AppCompatActivity {

    private static final String TAG = "ContactActivity";
    private TextView tvSupportEmail, tvLocationAddress;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        MaterialToolbar toolbar = findViewById(R.id.toolbarContact);
        toolbar.setNavigationOnClickListener(v -> finish());

        tvSupportEmail = findViewById(R.id.tvSupportEmail);
        tvLocationAddress = findViewById(R.id.tvLocationAddress);

        findViewById(R.id.btnFaq).setOnClickListener(v -> {
            startActivity(new Intent(this, FaqActivity.class));
        });

        loadContactInfo();
    }

    private void loadContactInfo() {
        executorService.execute(() -> {
            try {
                InformacaoEvento info = AppDatabase.getInstance(this).informacaoEventoDao().getInfo();
                if (info != null) {
                    runOnUiThread(() -> {
                        tvSupportEmail.setText(info.getEmailSuporte());
                        tvLocationAddress.setText(info.getEnderecoUniversidade());
                    });
                }
            } catch (Exception e) {
                Log.e(TAG, "Erro ao carregar informações de contato", e);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}