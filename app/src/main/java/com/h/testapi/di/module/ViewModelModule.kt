package com.h.testapi.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.h.testapi.ui.add_entry.AddEntryViewModel
import com.h.testapi.ui.list.ListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory) : ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    abstract fun bindListViewModel(viewModel: ListViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddEntryViewModel::class)
    abstract fun bindAddEntryViewModel(viewModel: AddEntryViewModel) : ViewModel
}