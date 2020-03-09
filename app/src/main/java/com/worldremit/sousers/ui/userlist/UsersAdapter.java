package com.worldremit.sousers.ui.userlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.worldremit.sousers.R;
import com.worldremit.sousers.ui.SanitisedUser;

import java.util.List;

class UsersAdapter extends RecyclerView.Adapter {

    private final ListItemClickListener onClickListener;
    private List<SanitisedUser> users;


    public interface ListItemClickListener {
        void onListItemClick(SanitisedUser clickedItemIndex);

        void onBlockClick(View view, SanitisedUser clickedItemIndex);

        void onFollowClick(View view, SanitisedUser clickedItemIndex);

    }

    public UsersAdapter(List<SanitisedUser> users, ListItemClickListener listener) {
        this.users = users;
        this.onClickListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new StackOverflowUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((StackOverflowUserViewHolder) holder).bind(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    void setUsers(List<SanitisedUser> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    class StackOverflowUserViewHolder extends RecyclerView.ViewHolder {

        final Button followButton = itemView.findViewById(R.id.item_user_follow);
        final Button blockButton = itemView.findViewById(R.id.item_user_block);

        StackOverflowUserViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(view ->
                    onClickListener.onListItemClick(users.get(getAdapterPosition())));
            followButton.setOnClickListener(view ->
                    onClickListener.onFollowClick(view, users.get(getAdapterPosition())));
            blockButton.setOnClickListener(view ->
                    onClickListener.onBlockClick(view, users.get(getAdapterPosition())));
        }

        void bind(SanitisedUser user) {
            TextView txtName = itemView.findViewById(R.id.item_user_username);

            txtName.setText(user.getUserName());

            followButton.setText(user.isFollowed() ? R.string.unfollow : R.string.follow);
            blockButton.setText(user.isBlocked() ? R.string.unblock : R.string.block);

        }

    }
}
