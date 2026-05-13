package com.heitor.week_tech.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.heitor.week_tech.R;
import com.heitor.week_tech.data.model.Patrocinador;

import java.util.ArrayList;
import java.util.List;

public class PatrocinadorAdapter extends RecyclerView.Adapter<PatrocinadorAdapter.PatrocinadorViewHolder> {

    private final List<Patrocinador> patrocinadores = new ArrayList<>();

    public void setItems(List<Patrocinador> newItems) {
        patrocinadores.clear();
        if (newItems != null) {
            patrocinadores.addAll(newItems);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PatrocinadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_patrocinador, parent, false);
        return new PatrocinadorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatrocinadorViewHolder holder, int position) {
        Patrocinador patrocinador = patrocinadores.get(position);
        // Placeholder implementation
        holder.ivLogo.setImageResource(android.R.drawable.ic_menu_gallery);
    }

    @Override
    public int getItemCount() {
        return patrocinadores.size();
    }

    static class PatrocinadorViewHolder extends RecyclerView.ViewHolder {
        ImageView ivLogo;

        public PatrocinadorViewHolder(@NonNull View itemView) {
            super(itemView);
            ivLogo = itemView.findViewById(R.id.ivLogoPatrocinador);
        }
    }
}