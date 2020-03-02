package com.worldremit.sousers.userlist;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.worldremit.sousers.App;
import com.worldremit.sousers.R;
import com.worldremit.sousers.api.model.User;
import com.worldremit.sousers.common.BaseMvpActivity;

import java.util.List;

public class UserListActivity extends BaseMvpActivity implements UserListPresenter.UserListView {

    private UserListPresenter userListPresenter;
    private UsersAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_list);

        App app = (App) getApplication();
        userListPresenter = new UserListPresenter(app.getUsersRepository());
        userListPresenter.onCreate(this);
        userListPresenter.onStart();

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        adapter = new UsersAdapter();
        RecyclerView list = findViewById(R.id.users_list);
        list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showUsers(List<User> users) {
        adapter.setUsers(users);
    }
}
