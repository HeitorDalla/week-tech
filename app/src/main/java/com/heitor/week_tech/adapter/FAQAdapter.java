package com.heitor.week_tech.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.heitor.week_tech.R;
import com.heitor.week_tech.data.model.FAQ;
import com.heitor.week_tech.ui.Palestra;

import java.util.List;

/**
 * Adapter que utiliza a entidade FAQ localizada no mesmo pacote (ui).
 */
public class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.FAQViewHolder> {

    // Certificando que estamos usando a classe FAQ da pasta ui
    private final List<FAQ> faqList;

    public FAQAdapter(List<FAQ> faqList) {
        this.faqList = faqList;
    }

    @NonNull
    @Override
    public FAQViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_faq, parent, false);
        return new FAQViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FAQViewHolder holder, int position) {
        FAQ faq = faqList.get(position);
        holder.tvPergunta.setText(faq.getPergunta());
        holder.tvResposta.setText(faq.getResposta());
    }

    @Override
    public int getItemCount() {
        return faqList != null ? faqList.size() : 0;
    }

    static class FAQViewHolder extends RecyclerView.ViewHolder {
        TextView tvPergunta, tvResposta;

        public FAQViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPergunta = itemView.findViewById(R.id.tvPergunta);
            tvResposta = itemView.findViewById(R.id.tvResposta);
        }
    }

    public static class PalestraAdapter extends RecyclerView.Adapter<PalestraAdapter.PalestraViewHolder> {

        private final List<Palestra> palestras;

        public PalestraAdapter(List<Palestra> palestras) {
            this.palestras = palestras;
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
            return palestras != null ? palestras.size() : 0;
        }

        public static class PalestraViewHolder extends RecyclerView.ViewHolder {
            TextView tvHorario, tvTitulo, tvLocal;

            public PalestraViewHolder(@NonNull View itemView) {
                super(itemView);
                tvHorario = itemView.findViewById(R.id.tvHorarioPalestra);
                tvTitulo = itemView.findViewById(R.id.tvTituloPalestra);
                tvLocal = itemView.findViewById(R.id.tvLocalPalestra);
            }
        }
    }
}