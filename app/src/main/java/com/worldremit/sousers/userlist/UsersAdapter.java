package com.worldremit.sousers.userlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.worldremit.sousers.R;
import com.worldremit.sousers.api.model.User;

import java.util.Collections;
import java.util.List;

class UsersAdapter extends RecyclerView.Adapter {

    private List<User> users = Collections.emptyList();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new StackOverflowUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((StackOverflowUserViewHolder)holder).bind(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    class StackOverflowUserViewHolder extends RecyclerView.ViewHolder {

        StackOverflowUserViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(User user) {
            TextView txtName = itemView.findViewById(R.id.name);
            txtName.setText(user.getDisplayName());
        }

    }
}
