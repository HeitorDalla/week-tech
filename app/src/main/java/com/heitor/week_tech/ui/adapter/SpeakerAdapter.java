package com.heitor.week_tech.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.heitor.week_tech.R;
import com.heitor.week_tech.data.local.entity.Speaker;
import java.util.ArrayList;
import java.util.List;

public class SpeakerAdapter extends RecyclerView.Adapter<SpeakerAdapter.ViewHolder> {
    private final List<Speaker> speakers = new ArrayList<>();

    public void setSpeakers(List<Speaker> speakers) {
        int oldSize = this.speakers.size();
        this.speakers.clear();
        if (speakers != null) this.speakers.addAll(speakers);
        if (oldSize > 0) notifyItemRangeRemoved(0, oldSize);
        if (!this.speakers.isEmpty()) notifyItemRangeInserted(0, this.speakers.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_speaker, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Speaker s = speakers.get(position);
        holder.tvName.setText(s.getName());
        holder.tvCompany.setText(s.getCompany());
        // topic is optional; show when available
        if (s.getTopic() != null && !s.getTopic().trim().isEmpty()) {
            holder.tvTopic.setText(s.getTopic());
            holder.tvTopic.setVisibility(android.view.View.VISIBLE);
        } else {
            holder.tvTopic.setVisibility(android.view.View.GONE);
        }
        holder.tvBio.setText(s.getBio());
    }

    @Override
    public int getItemCount() { return speakers.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvCompany, tvBio, tvTopic;
        public ViewHolder(View v) {
            super(v);
            tvName = v.findViewById(R.id.tvSpeakerName);
            tvCompany = v.findViewById(R.id.tvSpeakerCompany);
            tvTopic = v.findViewById(R.id.tvSpeakerTopic);
            tvBio = v.findViewById(R.id.tvSpeakerBio);
        }
    }
}

