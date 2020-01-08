package com.assignment.di.modules

import android.content.Context
import com.assignment.MainActivity
import com.assignment.di.qualifiers.ActivityContext
import com.assignment.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class MainActivityContextModule {
    private val mainActivity: MainActivity

    var context: Context

    constructor(mainActivity: MainActivity) {
        this.mainActivity = mainActivity
        context = mainActivity
    }

    @Provides
    @ActivityScope
    fun providesMainActivity(): MainActivity {
        return mainActivity
    }

    @Provides
    @ActivityScope
    @ActivityContext
    fun provideContext(): Context {
        return context
    }

}