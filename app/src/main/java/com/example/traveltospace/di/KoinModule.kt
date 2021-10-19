package com.example.traveltospace.di

import com.example.traveltospace.PODState
import com.example.traveltospace.model.PODViewModel
import com.example.traveltospace.repository.PODsRepository
import com.example.traveltospace.services.PODRepositoryInterface
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { PODViewModel(get()) }
}