package com.assignment.di.modules

import com.assignment.MainActivity
import com.assignment.adapter.RecyclerViewAdapter
import com.assignment.di.scopes.ActivityScope
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides

@Module(includes = [MainActivityContextModule::class])
class AdapterModule {
    @Provides
    @ActivityScope
    fun getStarWarsPeopleLIst(picasso: Picasso, clickListener: RecyclerViewAdapter.ClickListener): RecyclerViewAdapter {
        return RecyclerViewAdapter(picasso, clickListener)
    }

    @Provides
    @ActivityScope
    fun getClickListener(mainActivity: MainActivity): RecyclerViewAdapter.ClickListener {
        return mainActivity
    }
}