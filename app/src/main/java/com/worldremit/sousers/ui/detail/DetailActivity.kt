package com.worldremit.sousers.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.worldremit.sousers.R
import com.worldremit.sousers.ViewModelFactory
import dagger.android.AndroidInjection
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory<DetailViewModel>
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        AndroidInjection.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

//        viewModel.onViewLoaded()

    }

}