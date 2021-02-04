package com.efhem.byteworksassessment

import android.app.Application
import com.efhem.byteworksassessment.di.mLocalModules
import com.efhem.byteworksassessment.di.mNetworkModules
import com.efhem.byteworksassessment.di.mRepositoryModules
import com.efhem.byteworksassessment.di.mViewModelsModules
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        loadKoin()
    }

    private fun loadKoin() {
        startKoin(this, listOf(mLocalModules, mNetworkModules, mViewModelsModules, mRepositoryModules))
    }
}