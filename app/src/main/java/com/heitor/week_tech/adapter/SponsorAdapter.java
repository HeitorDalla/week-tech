package com.heitor.week_tech.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.heitor.week_tech.data.model.Patrocinador;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter para listar patrocinadores do evento.
 */
public class SponsorAdapter extends RecyclerView.Adapter<SponsorAdapter.ViewHolder> {

    private final List<Patrocinador> items = new ArrayList<>();

    public void setItems(List<Patrocinador> newItems) {
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
        Patrocinador patrocinador = items.get(position);
        holder.title.setText(valueOrDash(patrocinador.getNome()));
        holder.subtitle.setText(String.format("Site: %s", valueOrDash(patrocinador.getLinkSite())));
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
