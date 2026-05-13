package com.heitor.week_tech.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.heitor.week_tech.R;
import com.heitor.week_tech.data.model.Projeto;

import java.util.ArrayList;
import java.util.List;

public class ProjetoAdapter extends RecyclerView.Adapter<ProjetoAdapter.ProjetoViewHolder> {

    private final List<Projeto> projetos = new ArrayList<>();

    public void setItems(List<Projeto> newItems) {
        projetos.clear();
        if (newItems != null) {
            projetos.addAll(newItems);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProjetoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_projeto, parent, false);
        return new ProjetoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjetoViewHolder holder, int position) {
        Projeto projeto = projetos.get(position);
        holder.tvNome.setText(projeto.getNomeProjeto());
        holder.tvAutor.setText(projeto.getNomeAutor());
    }

    @Override
    public int getItemCount() {
        return projetos.size();
    }

    static class ProjetoViewHolder extends RecyclerView.ViewHolder {
        TextView tvNome, tvAutor;

        public ProjetoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNome = itemView.findViewById(R.id.tvNomeProjeto);
            tvAutor = itemView.findViewById(R.id.tvAutorProjeto);
        }
    }
}