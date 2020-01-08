package com.assignment.di.components

import android.content.Context
import com.assignment.MainActivity
import com.assignment.di.modules.AdapterModule
import com.assignment.di.qualifiers.ActivityContext
import com.assignment.di.scopes.ActivityScope
import dagger.Component

@ActivityScope
@Component(modules = [AdapterModule::class], dependencies = [ApplicationComponent::class])
interface MainActivityComponent {

    @ActivityContext
    fun getContext(): Context

    fun injectMainActivity(mainActivity: MainActivity)
}