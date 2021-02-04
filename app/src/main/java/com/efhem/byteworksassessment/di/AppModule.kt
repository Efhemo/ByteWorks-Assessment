package com.efhem.byteworksassessment.di

import com.efhem.byteworksassessment.data.CountryStateRepo
import com.efhem.byteworksassessment.data.ICountryStateRepo
import com.efhem.byteworksassessment.data.local.database
import com.efhem.byteworksassessment.data.remote.RemoteApi
import com.efhem.byteworksassessment.data.remote.createNetworkClient
import com.efhem.byteworksassessment.viewmodels.SignInViewModel
import com.efhem.byteworksassessment.viewmodels.SignUpViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val mNetworkModules = module {
    single { createNetworkClient("https://api.printful.com/").create(RemoteApi::class.java) }
}

val mLocalModules = module {
    single { database(androidContext()) }
}

val mRepositoryModules = module {

    single { CountryStateRepo( get(), get(), Dispatchers.IO) as ICountryStateRepo }

}

val mViewModelsModules = module {

    viewModel { SignInViewModel(get()) }
    viewModel { SignUpViewModel(get()) }

}
