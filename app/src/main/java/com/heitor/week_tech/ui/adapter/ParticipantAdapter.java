package com.heitor.week_tech.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.heitor.week_tech.R;
import com.heitor.week_tech.data.local.entity.Participant;
import java.util.ArrayList;
import java.util.List;

public class ParticipantAdapter extends RecyclerView.Adapter<ParticipantAdapter.ViewHolder> {
    private List<Participant> participants = new ArrayList<>();

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_participant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Participant participant = participants.get(position);
        holder.tvName.setText(participant.getName());
        holder.tvEmail.setText(participant.getEmail());
        holder.tvCpf.setText(holder.itemView.getContext().getString(R.string.label_cpf, participant.getCpf()));
    }

    @Override
    public int getItemCount() {
        return participants.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvEmail, tvCpf;

        ViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tvItemName);
            tvEmail = view.findViewById(R.id.tvItemEmail);
            tvCpf = view.findViewById(R.id.tvItemCpf);
        }
    }
}