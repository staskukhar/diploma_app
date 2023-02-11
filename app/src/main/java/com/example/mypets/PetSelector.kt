package com.example.mypets

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypets.databinding.ActivityPetSelectorBinding
import java.time.LocalDate
import java.time.Period
import java.util.Calendar

class PetSelector : AppCompatActivity(), PetAdapter.Listener {
    private lateinit var binding: ActivityPetSelectorBinding
    private val adapter = PetAdapter(this)

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPetSelectorBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        db = AppDatabase.getDb(this)

        db.getDao().getAll().asLiveData().observe((this as LifecycleOwner)){
            adapter.clearPets()
            adapter.addPet(it)
            init()
        }
    }
    private fun init() {
        binding.rvPetSelector.layoutManager = LinearLayoutManager(this)
        binding.rvPetSelector.adapter = adapter
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClick(pet: Pet) {
        /*
        startActivity(Intent(this, ActivityCheckBMI::class.java).apply {
            putExtra(Constance.PetKey, pet.id)
        })
         */
        val extras = intent.extras
        processingRequest(extras!!.getInt(Constace.PetKey), pet)
    }
    private fun getBMIForDog(pet: Pet?): Double?{
        if (pet != null) {
            return (pet.weight / 0.45) / (pet.height / 2.54)
        }
        return 0.0
    }
    private fun getBMI(pet: Pet?): Double?{
        when(pet?.type){
            Constace.DogEU, Constace.DogUA -> return getBMIForDog(pet)
        }
        return 0.0
    }
    private fun showResultBMICheck(pet: Pet) {
        Toast.makeText(this, "${getString(R.string.weightToHeight)}: ${String.format("%.1f",getBMI(pet))}", Toast.LENGTH_LONG).show();
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun processingRequest(code: Int, pet: Pet){
        when(code) {
            RequestCode.BMITest -> showResultBMICheck(pet)
            RequestCode.VaccineTest -> showResultVaccineCheck(pet)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showResultVaccineCheck(pet: Pet) {
        if(pet.vaccineDate != null){
            try{
                var currentDate = LocalDate.now()
                val period = Period.between(LocalDate.parse(pet.vaccineDate), currentDate)
                if(isVaccineDo(period)) {
                    Toast.makeText(this, getString(R.string.vaccineDo), Toast.LENGTH_LONG).show()
                }
                else {
                    Toast.makeText(this, getString(R.string.vaccineDont), Toast.LENGTH_LONG).show()
                }
            }
            catch (e: Exception){
                Toast.makeText(this, getString(R.string.invalidDateFormat), Toast.LENGTH_LONG).show()
            }
        }
        else{
            Toast.makeText(this, getString(R.string.setDateVaccine), Toast.LENGTH_LONG).show()
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun isVaccineDo(period: Period): Boolean{
        return period.years < 1
    }
    //String.format("%.1f", getBMI(pet))
}