package com.vasilyevskii.testcurrency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.vasilyevskii.testcurrency.databinding.ActivityMainBinding
import com.vasilyevskii.testcurrency.fragments.FragmentFavorite
import com.vasilyevskii.testcurrency.fragments.FragmentMain
import com.vasilyevskii.testcurrency.fragments.FragmentSort
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bindingActivityMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingActivityMain.root)


        bindingActivityMain.navView.setOnNavigationItemSelectedListener { item ->

            var selectedFragment: Fragment = FragmentMain()
            when(item.itemId){
                R.id.all_currency -> selectedFragment = FragmentMain()
                R.id.favorite -> selectedFragment = FragmentFavorite()
            }

            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.fragment_container_view, selectedFragment).commit()

            return@setOnNavigationItemSelectedListener true
        }

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view, FragmentMain()).commit()


        bindingActivityMain.buttonSort.setOnNavigationItemSelectedListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view, FragmentSort()).commit()
            return@setOnNavigationItemSelectedListener true
        }
    }
}