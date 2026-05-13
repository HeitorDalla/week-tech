package com.heitor.week_tech.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.heitor.week_tech.R;
import com.heitor.week_tech.data.model.FAQ;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter para FAQ (pergunta e resposta).
 */
public class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.ViewHolder> {

    private final List<FAQ> items = new ArrayList<>();

    public void setItems(List<FAQ> newItems) {
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
                .inflate(R.layout.item_faq, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FAQ faq = items.get(position);
        holder.tvPergunta.setText(valueOrDash(faq.getPergunta()));
        holder.tvResposta.setText(valueOrDash(faq.getResposta()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private String valueOrDash(String value) {
        return (value == null || value.trim().isEmpty()) ? "-" : value;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvPergunta;
        final TextView tvResposta;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPergunta = itemView.findViewById(R.id.tvPergunta);
            tvResposta = itemView.findViewById(R.id.tvResposta);
        }
    }
}
