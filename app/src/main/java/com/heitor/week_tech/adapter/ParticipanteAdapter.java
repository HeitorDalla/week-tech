package com.heitor.week_tech.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.heitor.week_tech.R;
import com.google.android.material.chip.Chip;

import com.heitor.week_tech.data.model.Participante;

import java.util.ArrayList;
import java.util.List;

public class ParticipanteAdapter extends RecyclerView.Adapter<ParticipanteAdapter.ParticipanteViewHolder> {

    private List<Participante> participantes = new ArrayList<>();

    public ParticipanteAdapter() {
    }

    public void setParticipants(List<Participante> participantes) {
        this.participantes = participantes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ParticipanteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_participante, parent, false);
        return new ParticipanteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParticipanteViewHolder holder, int position) {
        Participante participante = participantes.get(position);
        holder.tvNome.setText(participante.getNome());
        holder.tvRA.setText("RA: " + participante.getRa());
        holder.chipCoffee.setVisibility(participante.isQuerCoffeeBreak() ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return participantes != null ? participantes.size() : 0;
    }

    static class ParticipanteViewHolder extends RecyclerView.ViewHolder {
        TextView tvNome, tvRA;
        Chip chipCoffee;

        public ParticipanteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNome = itemView.findViewById(R.id.tvNomeParticipante);
            tvRA = itemView.findViewById(R.id.tvRAParticipante);
            chipCoffee = itemView.findViewById(R.id.chipCoffee);
        }
    }
}