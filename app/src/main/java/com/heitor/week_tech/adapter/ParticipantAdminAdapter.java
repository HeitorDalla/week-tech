package com.heitor.week_tech.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.heitor.week_tech.data.model.Participante;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter para listagem administrativa de participantes.
 */
public class ParticipantAdminAdapter extends RecyclerView.Adapter<ParticipantAdminAdapter.ViewHolder> {

    private final List<Participante> items = new ArrayList<>();

    public void setItems(List<Participante> newItems) {
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
        Participante participante = items.get(position);
        String nomeLinha = String.format("%s (RA: %s)",
                valueOrDash(participante.getNome()),
                valueOrDash(participante.getRa()));
        String detalheLinha = String.format("Curso: %s | Serie: %s | Coffee: %s",
                valueOrDash(participante.getCurso()),
                valueOrDash(participante.getSerie()),
                participante.isQuerCoffeeBreak() ? "Sim" : "Nao");

        holder.title.setText(nomeLinha);
        holder.subtitle.setText(detalheLinha);
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
