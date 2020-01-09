package com.assignment

import android.app.Activity
import android.app.Application
import com.assignment.di.components.ApplicationComponent
import com.assignment.di.components.DaggerApplicationComponent
import com.assignment.di.modules.ContextModule

class MyApplication : Application() {
    private var applicationComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()

        applicationComponent =
            DaggerApplicationComponent.builder().contextModule(ContextModule(this)).build()
        applicationComponent?.injectApplication(this)

    }


    companion object{
        fun get(activity: Activity): MyApplication {
            return activity.application as MyApplication
        }
    }

    fun getApplicationComponen(): ApplicationComponent {
        return applicationComponent!!
    }
}