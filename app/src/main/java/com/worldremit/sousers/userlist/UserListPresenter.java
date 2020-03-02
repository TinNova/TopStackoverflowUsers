package com.worldremit.sousers.userlist;

import com.worldremit.sousers.api.model.User;
import com.worldremit.sousers.common.BaseMvpPresenter;
import com.worldremit.sousers.common.BaseMvpView;
import com.worldremit.sousers.repository.UsersRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserListPresenter extends BaseMvpPresenter {

    private UserListView view;

    interface UserListView extends BaseMvpView {
        void showUsers(List<User> users);
    }

    @Override
    public void onCreate(@NotNull BaseMvpView view) {
        this.view = (UserListView)view;
    }

    private final UsersRepository usersRepository;

    public UserListPresenter(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public void onStart() {
        observe(usersRepository
                .fetchTopUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> view.showUsers(users)));
    }
}
