package com.efhem.byteworksassessment.di

import com.efhem.byteworksassessment.data.repository.CountryStateRepo
import com.efhem.byteworksassessment.data.repository.ICountryStateRepo
import com.efhem.byteworksassessment.data.local.database
import com.efhem.byteworksassessment.data.remote.RemoteApi
import com.efhem.byteworksassessment.data.remote.createNetworkClient
import com.efhem.byteworksassessment.data.repository.AdminRepo
import com.efhem.byteworksassessment.data.repository.IAdminRepo
import com.efhem.byteworksassessment.viewmodels.SignInViewModel
import com.efhem.byteworksassessment.viewmodels.SignUpViewModel
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

    single { CountryStateRepo( get(), get()) as ICountryStateRepo }
    single { AdminRepo( get() ) as IAdminRepo }

}

val mViewModelsModules = module {

    viewModel { SignInViewModel(get(), get()) }
    viewModel { SignUpViewModel(get(), get()) }

}
