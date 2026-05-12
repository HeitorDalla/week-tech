package com.heitor.week_tech.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.heitor.week_tech.data.model.Palestra;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter para listar a programacao de palestras (RF01).
 */
public class EventScheduleAdapter extends RecyclerView.Adapter<EventScheduleAdapter.ViewHolder> {

    private final List<Palestra> items = new ArrayList<>();

    public void setItems(List<Palestra> newItems) {
        items.clear();
        if (newItems != null) {
            items.addAll(newItems);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Palestra palestra = items.get(position);
        String tituloLinha = String.format("%s - %s", valueOrDash(palestra.getHorario()), valueOrDash(palestra.getTitulo()));
        String subtituloLinha = String.format("Local: %s", valueOrDash(palestra.getLocal()));

        holder.title.setText(tituloLinha);
        holder.subtitle.setText(subtituloLinha);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private String valueOrDash(String value) {
        return (value == null || value.trim().isEmpty()) ? "-" : value;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView title;
        final TextView subtitle;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(android.R.id.text1);
            subtitle = itemView.findViewById(android.R.id.text2);
        }
    }
}
