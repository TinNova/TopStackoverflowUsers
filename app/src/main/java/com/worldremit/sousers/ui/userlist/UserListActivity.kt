package com.worldremit.sousers.ui.userlist

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.worldremit.sousers.*
import com.worldremit.sousers.ui.SanitisedUser
import com.worldremit.sousers.ui.SqlUserState
import com.worldremit.sousers.ui.ViewStateModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_user_list.*
import javax.inject.Inject


class UserListActivity : AppCompatActivity(), UsersAdapter.ListItemClickListener {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory<UserListViewModel>
    private lateinit var viewModel: UserListViewModel

    private lateinit var users: MutableList<SanitisedUser>

    private lateinit var blockBtn: View
    private lateinit var followBtn: View

    private var adapter: UsersAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        AndroidInjection.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(UserListViewModel::class.java)
        viewModel.onViewLoaded()

        setupRecyclerView()
        observeViewState()
        observerSqlUserState()
    }

    private fun observeViewState() {
        viewModel.viewState.observe(this, Observer<ViewStateModel> {
            it?.let {
                when (it.isDataReady) {
                    true -> showUsers(it.sanitisedUsers)
                    false -> users_list_recycler_view.gone()
                }
                when (it.isLoading) {
                    true -> loading_icon.visible()
                    false -> loading_icon.gone()
                }
                when (it.isNetworkError) {
                    true -> network_error.visible()
                    false -> network_error.gone()
                }
            }
        })
    }

    private fun observerSqlUserState() {
        viewModel.sqlUserState.observe(this, Observer<SqlUserState> {
            it?.let {
                when (it.isSqlError) {
                    true -> this.toast(getString(R.string.sql_error))
                    false -> updateButton(it.userStateChange, it.sanitisedUser)
                }
            }
        })
    }

    private fun updateButton(
        userStateChange: UserListViewModel.UserStateChange,
        sanitisedUser: SanitisedUser
    ) {

        if (userStateChange == UserListViewModel.UserStateChange.BLOCK) {
            (blockBtn as Button).text =
                if (sanitisedUser.isBlocked) getString(R.string.unblock) else getString(R.string.block)
        } else {
            (followBtn as Button).text =
                if (sanitisedUser.isFollowed) getString(R.string.unfollow) else getString(
                    R.string.follow
                )
        }
    }

    private fun setupRecyclerView() {
        adapter = UsersAdapter(emptyList(), this)
        users_list_recycler_view.adapter = adapter
        users_list_recycler_view.layoutManager = LinearLayoutManager(this)
        users_list_recycler_view.setHasFixedSize(true)
    }

    private fun showUsers(users: List<SanitisedUser>) {
        users_list_recycler_view.visible()
        adapter!!.setUsers(users)
    }

    override fun onFollowClick(view: View, user: SanitisedUser) {
        followBtn = view
        viewModel.saveUserFollowStatus(user)
    }

    override fun onBlockClick(view: View, user: SanitisedUser) {
        blockBtn = view
        viewModel.saveUserBlockStatus(user)
    }

    override fun onListItemClick(user: SanitisedUser) {
        //Intent To DetailActivity
    }
}