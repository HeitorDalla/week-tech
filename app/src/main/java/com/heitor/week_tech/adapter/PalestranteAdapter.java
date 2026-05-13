package com.heitor.week_tech.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.heitor.week_tech.R;
import com.heitor.week_tech.data.model.Palestrante;
import java.util.ArrayList;
import java.util.List;

public class PalestranteAdapter extends RecyclerView.Adapter<PalestranteAdapter.PalestranteViewHolder> {

    private final List<Palestrante> palestrantes = new ArrayList<>();

    public void setItems(List<Palestrante> newItems) {
        palestrantes.clear();
        if (newItems != null) {
            palestrantes.addAll(newItems);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PalestranteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_palestrante, parent, false);
        return new PalestranteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PalestranteViewHolder holder, int position) {
        Palestrante palestrante = palestrantes.get(position);
        holder.tvNome.setText(palestrante.getNome());
        holder.tvTema.setText(palestrante.getTemaPalestra());
    }

    @Override
    public int getItemCount() {
        return palestrantes.size();
    }

    public static class PalestranteViewHolder extends RecyclerView.ViewHolder {
        TextView tvNome, tvTema;
        View viewAvatar;

        public PalestranteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNome = itemView.findViewById(R.id.tvNomePalestrante);
            tvTema = itemView.findViewById(R.id.tvTemaPalestra);
            viewAvatar = itemView.findViewById(R.id.viewAvatar);
        }
    }
}