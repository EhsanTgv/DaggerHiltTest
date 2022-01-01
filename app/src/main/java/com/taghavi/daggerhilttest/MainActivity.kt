package com.taghavi.daggerhilttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var someClass: SomeClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(someClass.doAThing())
    }
}

@ActivityScoped
class SomeClass
@Inject
constructor(
    private val someInterfaceImpl: SomeInterface,
    private val gson: Gson
) {
    fun doAThing(): String {
        return "Look I : ${someInterfaceImpl.getAThing()}"
    }
}

class SomeInterfaceImpl
@Inject
constructor() : SomeInterface {
    override fun getAThing(): String {
        return "A Thing!"
    }
}

interface SomeInterface {
    fun getAThing(): String
}

@InstallIn(ActivityScoped::class)
@Module
abstract class MyModule {
    @ActivityScoped
    @Binds
    abstract fun bindSomeDependency(someImpl: SomeInterfaceImpl): SomeInterface

    @ActivityScoped
    @Binds
    abstract fun bindGson(gson: Gson): Gson
}