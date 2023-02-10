package com.example.mypets
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mypets.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var homeActivity: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeActivity = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeActivity.root)
        homeActivity.mainNavigation.selectedItemId = R.id.mHome
        loadFragment(HomeFragment())
        homeActivity.mainNavigation.setOnItemSelectedListener{
            when(it.itemId){
                R.id.mHome -> loadFragment(HomeFragment())
                R.id.mPets -> loadFragment(PetsFragment())
                R.id.mAddPet -> loadFragment(EventsFragment())
            }
            true
        }
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
