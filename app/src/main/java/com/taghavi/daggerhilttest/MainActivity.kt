package com.taghavi.daggerhilttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
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
        println(someClass.doSomeOtherThing())
    }
}

@AndroidEntryPoint
class MyFragment : Fragment() {

    @Inject
    lateinit var someClass: SomeClass
}

@ActivityScoped
class SomeClass
@Inject
constructor(
    private val someOtherClass: SomeOtherClass
) {
    fun doAThing(): String {
        return "Look I did a thing!"
    }

    fun doSomeOtherThing(): String {
        return someOtherClass.doSomeOtherThing()
    }
}

class SomeOtherClass
@Inject
constructor() {
    fun doSomeOtherThing(): String {
        return "Look I did some other thing!"
    }
}