package com.h.testapi.di.module

import com.h.testapi.ui.add_entry.AddEntryFragment
import com.h.testapi.ui.entry.EntryFragment
import com.h.testapi.ui.list.ListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributesListFragment() : ListFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributesEntryFragment() : EntryFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributesAddEntryFragment() : AddEntryFragment
}