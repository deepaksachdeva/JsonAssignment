package com.assignment.di.modules

import com.assignment.MainActivity
import com.assignment.adapter.RecyclerViewAdapter
import com.assignment.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides

@Module(includes = [MainActivityContextModule::class])
class AdapterModule {
    @Provides
    @ActivityScope
    fun getStarWarsPeopleLIst(clickListener: RecyclerViewAdapter.ClickListener): RecyclerViewAdapter {
        return RecyclerViewAdapter(clickListener)
    }

    @Provides
    @ActivityScope
    fun getClickListener(mainActivity: MainActivity): RecyclerViewAdapter.ClickListener {
        return mainActivity
    }
}