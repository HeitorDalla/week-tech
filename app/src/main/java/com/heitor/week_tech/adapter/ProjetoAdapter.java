package com.heitor.week_tech.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.heitor.week_tech.R;

import java.util.List;

public class ProjetoAdapter extends RecyclerView.Adapter<ProjetoAdapter.ProjetoViewHolder> {

    private final List<Projeto> projetos;

    public ProjetoAdapter(List<Projeto> projetos) {
        this.projetos = projetos;
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
        holder.tvNome.setText(projeto.getNome());
        holder.tvAutor.setText(projeto.getAutor());
    }

    @Override
    public int getItemCount() {
        return projetos != null ? projetos.size() : 0;
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