package com.heitor.week_tech.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.heitor.week_tech.R;
import com.heitor.week_tech.data.local.entity.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {
    private final List<Project> projects = new ArrayList<>();

    public void setProjects(List<Project> projects) {
        int oldSize = this.projects.size();
        this.projects.clear();
        if (projects != null) this.projects.addAll(projects);
        if (oldSize > 0) notifyItemRangeRemoved(0, oldSize);
        if (!this.projects.isEmpty()) notifyItemRangeInserted(0, this.projects.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Project p = projects.get(position);
        holder.tvHorario.setText(valueOrDash(p.getHorario()));
        holder.tvTitle.setText(valueOrDash(p.getTitle()));
        holder.tvAuthor.setText(valueOrDash(p.getAuthor()));
        holder.tvLocal.setText(valueOrDash(p.getLocal()));
        holder.tvDescription.setText(valueOrDash(p.getDescription()));
    }

    @Override
    public int getItemCount() { return projects.size(); }

    private String valueOrDash(String value) {
        return (value == null || value.trim().isEmpty()) ? "-" : value;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle, tvAuthor, tvHorario, tvLocal, tvDescription;

        public ViewHolder(View v) {
            super(v);
            tvTitle = v.findViewById(R.id.tvProjectTitle);
            tvAuthor = v.findViewById(R.id.tvProjectAuthor);
            tvHorario = v.findViewById(R.id.tvProjectHorario);
            tvLocal = v.findViewById(R.id.tvProjectLocal);
            tvDescription = v.findViewById(R.id.tvProjectDescription);
        }
    }
}
