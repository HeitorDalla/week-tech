package com.heitor.week_tech.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.MaterialToolbar;
import com.heitor.week_tech.R;
import com.heitor.week_tech.adapter.FAQAdapter;
import com.heitor.week_tech.data.database.AppDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FaqActivity extends AppCompatActivity {

    private FAQAdapter adapter;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        MaterialToolbar toolbar = findViewById(R.id.toolbarFaq);
        toolbar.setNavigationOnClickListener(v -> finish());

        RecyclerView rvFaq = findViewById(R.id.rvFaq);
        rvFaq.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FAQAdapter();
        rvFaq.setAdapter(adapter);

        loadFaqData();
    }

    private void loadFaqData() {
        executorService.execute(() -> {
            var faqs = AppDatabase.getInstance(this).faqDao().getAll();
            runOnUiThread(() -> adapter.setItems(faqs));
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}