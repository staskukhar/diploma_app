package com.example.mypets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mypets.databinding.ActivityAboutVitaminsBinding

class ActivityAboutVitamins : AppCompatActivity() {
    private lateinit var binding: ActivityAboutVitaminsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutVitaminsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadFragment(DogVitaminsFragment())
        binding.vitaminsNavigation.setOnItemSelectedListener{
            when(it.itemId){
                R.id.mCats -> loadFragment(CatVitaminsFragment())
                R.id.mDogs -> loadFragment(DogVitaminsFragment())
            }
            true
        }
        binding.flbBack.setOnClickListener{
            finish()
        }
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}