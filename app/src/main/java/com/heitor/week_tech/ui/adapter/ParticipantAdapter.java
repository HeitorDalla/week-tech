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
    private final List<Participant> participants = new ArrayList<>();

    public void setParticipants(List<Participant> participants) {
        int oldSize = this.participants.size();
        this.participants.clear();
        if (participants != null) {
            this.participants.addAll(participants);
        }
        if (oldSize > 0) {
            notifyItemRangeRemoved(0, oldSize);
        }
        if (!this.participants.isEmpty()) {
            notifyItemRangeInserted(0, this.participants.size());
        }
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
        holder.tvCourse.setText(holder.itemView.getContext().getString(R.string.label_course, participant.getCourse()));
        holder.tvPeriod.setText(holder.itemView.getContext().getString(R.string.label_period, participant.getPeriod()));
        holder.tvCoffee.setText(holder.itemView.getContext().getString(
                participant.isCoffeeBreak() ? R.string.label_coffee_yes : R.string.label_coffee_no));
    }

    @Override
    public int getItemCount() {
        return participants.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvEmail, tvCpf, tvCourse, tvPeriod, tvCoffee;

        ViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tvItemName);
            tvEmail = view.findViewById(R.id.tvItemEmail);
            tvCpf = view.findViewById(R.id.tvItemCpf);
            tvCourse = view.findViewById(R.id.tvItemCourse);
            tvPeriod = view.findViewById(R.id.tvItemPeriod);
            tvCoffee = view.findViewById(R.id.tvItemCoffee);
        }
    }
}