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
import com.worldremit.sousers.R
import com.worldremit.sousers.ViewModelFactory
import com.worldremit.sousers.ui.SanitisedUser
import com.worldremit.sousers.ui.ViewStateModel
import dagger.android.AndroidInjection
import javax.inject.Inject


class UserListActivity : AppCompatActivity(), UsersAdapter.ListItemClickListener {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory<UserListViewModel>
    private lateinit var viewModel: UserListViewModel

    private var adapter: UsersAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        AndroidInjection.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(UserListViewModel::class.java)
        viewModel.onViewLoaded()

        setupRecyclerView()
        observeViewState()
    }

    private fun observeViewState() {
        viewModel.viewState.observe(this, Observer<ViewStateModel> {
            it?.let {
                when (it.isDataReady) {
                    true -> showUsers(it.listData)
//                    false -> recyclerView.gone()
                }
                when (it.isLoading) {
//                    true -> loading_icon.visible()
//                    false -> loading_icon.gone()
                }
                when (it.isNetworkError) {
//                    true -> network_error.visible()
//                    false -> network_error.gone()
                }
            }
        })
    }


    private fun setupRecyclerView() {
        adapter = UsersAdapter(emptyList(), this, this)
        val recycleView = findViewById<RecyclerView>(R.id.users_list)
        recycleView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.setHasFixedSize(true)
    }

    private fun showUsers(users: List<SanitisedUser>) {
        adapter!!.setUsers(users)
    }

    override fun onFollowClick(view: View?, user: SanitisedUser) {
        (view as Button).text =
            if (user.isFollowed) getString(R.string.follow) else getString(R.string.unfollow)
        viewModel.saveUserFollowStatus(user)
    }

    override fun onBlockClick(view: View?, user: SanitisedUser) {
        (view as Button).text =
            if (user.isBlocked) getString(R.string.block) else getString(R.string.unblock)
        viewModel.saveUserBlockStatus(user)
    }

    override fun onListItemClick(user: SanitisedUser) {}
}