package com.assignment.di.modules

import android.content.Context
import com.assignment.di.qualifiers.ApplicationContext
import com.assignment.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val context: Context) {

    @Provides
    @ApplicationScope
    @ApplicationContext
    fun provideContext(): Context {
        return context
    }
}