package com.heitor.week_tech.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.MaterialToolbar;
import com.heitor.week_tech.R;
import com.heitor.week_tech.adapter.PalestraAdapter;
import com.heitor.week_tech.data.database.AppDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ScheduleActivity extends AppCompatActivity {

    private PalestraAdapter adapter;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        MaterialToolbar toolbar = findViewById(R.id.toolbarSchedule);
        toolbar.setNavigationOnClickListener(v -> finish());

        RecyclerView rvSchedule = findViewById(R.id.rvSchedule);
        rvSchedule.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PalestraAdapter();
        rvSchedule.setAdapter(adapter);

        loadSchedule();
    }

    private void loadSchedule() {
        executorService.execute(() -> {
            var palestras = AppDatabase.getInstance(this).palestraDao().getAll();
            runOnUiThread(() -> adapter.setItems(palestras));
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}