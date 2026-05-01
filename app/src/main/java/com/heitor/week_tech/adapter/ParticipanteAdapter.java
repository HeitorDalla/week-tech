package com.heitor.week_tech.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.heitor.week_tech.R;
import com.google.android.material.chip.Chip;

import java.util.List;

public class ParticipanteAdapter extends RecyclerView.Adapter<ParticipanteAdapter.ParticipanteViewHolder> {

    private final List<Participante> participantes;

    public ParticipanteAdapter(List<Participante> participantes) {
        this.participantes = participantes;
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
        holder.chipCoffee.setVisibility(participante.isCoffeeBreak() ? View.VISIBLE : View.GONE);
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