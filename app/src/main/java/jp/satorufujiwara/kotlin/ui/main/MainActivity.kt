package jp.satorufujiwara.kotlin.ui.main

import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import jp.satorufujiwara.kotlin.R
import jp.satorufujiwara.kotlin.util.ext.setContentFragment
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity(), LifecycleRegistryOwner, HasSupportFragmentInjector {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Fragment>
    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java) }
    private val lifecycleRegistry by lazy { android.arch.lifecycle.LifecycleRegistry(this) }

    override fun getLifecycle() = lifecycleRegistry

    override fun supportFragmentInjector() = androidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        Timber.d("ViewModel = $viewModel")
        setContentFragment(R.id.containerLayout) { MainFragment.newInstance() }
    }
}