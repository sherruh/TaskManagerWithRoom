package com.todoapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolderUser> {



    List<User> list;

    public UserAdapter(List<User> list) {
        this.list = list;

    }

    @NonNull
    @Override
    public ViewHolderUser onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_user, viewGroup, false);
        return new ViewHolderUser(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderUser viewHolder, int i) {
        User user = list.get(i);
        viewHolder.textName.setText(user.getName());
        viewHolder.textAge.setText(String.valueOf(user.getAge()));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolderUser extends RecyclerView.ViewHolder {

        TextView textName;
        TextView textAge;

        public ViewHolderUser(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textAge = itemView.findViewById(R.id.textAge);

        }
    }
}
