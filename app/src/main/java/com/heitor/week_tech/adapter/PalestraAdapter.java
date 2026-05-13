package com.heitor.week_tech.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.heitor.week_tech.R;
import com.heitor.week_tech.data.model.Palestra;
import java.util.ArrayList;
import java.util.List;

public class PalestraAdapter extends RecyclerView.Adapter<PalestraAdapter.PalestraViewHolder> {

    private final List<Palestra> palestras = new ArrayList<>();

    public void setItems(List<Palestra> newItems) {
        palestras.clear();
        if (newItems != null) {
            palestras.addAll(newItems);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PalestraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_palestra, parent, false);
        return new PalestraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PalestraViewHolder holder, int position) {
        Palestra palestra = palestras.get(position);
        holder.tvHorario.setText(palestra.getHorario());
        holder.tvTitulo.setText(palestra.getTitulo());
        holder.tvLocal.setText(palestra.getLocal());
    }

    @Override
    public int getItemCount() {
        return palestras.size();
    }

    static class PalestraViewHolder extends RecyclerView.ViewHolder {
        TextView tvHorario, tvTitulo, tvLocal;

        public PalestraViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHorario = itemView.findViewById(R.id.tvHorarioPalestra);
            tvTitulo = itemView.findViewById(R.id.tvTituloPalestra);
            tvLocal = itemView.findViewById(R.id.tvLocalPalestra);
        }
    }
}