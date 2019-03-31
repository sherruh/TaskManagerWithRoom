package com.todoapp;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private ClickListener clickListener;
    public interface ClickListener {
        void onClick(int pos);
        void onLongClick(int pos);
    }

    List<Task> list;

    public TaskAdapter(List<Task> list) {
        this.list = list;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_task, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Task task = list.get(i);

        viewHolder.textTitle.setText(task.getTitle());
        switch (task.getStatus()) {
            case 0:
                viewHolder.textStatus.setText("важное");
                viewHolder.textStatus.setTextColor(Color.RED);
                break;
            case 1:
                viewHolder.textStatus.setText("срочное");
                viewHolder.textStatus.setTextColor(Color.BLUE);
                break;
            case 2:
                viewHolder.textStatus.setText("не срочное");
                viewHolder.textStatus.setTextColor(Color.YELLOW);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textTitle;
        TextView textStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textStatus = itemView.findViewById(R.id.textStatus);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClick(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    clickListener.onLongClick(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
