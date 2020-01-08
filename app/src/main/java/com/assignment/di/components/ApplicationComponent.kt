package com.assignment.di.components

import android.content.Context
import com.assignment.MyApplication
import com.assignment.di.modules.ContextModule
import com.assignment.di.modules.RetrofitModule
import com.assignment.di.qualifiers.ApplicationContext
import com.assignment.di.scopes.ApplicationScope
import com.assignment.retrofit.APIInterface
import dagger.Component

@ApplicationScope
@Component(modules = [ContextModule::class, RetrofitModule::class])
interface ApplicationComponent {

    fun getApiInterface(): APIInterface

    @ApplicationContext
    fun getContext(): Context

    fun injectApplication(myApplication: MyApplication)

}