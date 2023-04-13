package com.example.mypets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.drawToBitmap
import com.example.mypets.databinding.ActivityEditPetBinding

class ActivityEditPet : AppCompatActivity() {
    private lateinit var activity: ActivityEditPetBinding
    private lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = ActivityEditPetBinding.inflate(layoutInflater)
        setContentView(activity.root)

        db = AppDatabase.getDb(this)
        val petId = intent.getIntExtra(Constace.PetKey,0)
        if(petId != 0) setCurrentData(petId)

        activity.spinnerPetsType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                when(position){
                    0 -> activity.ivPhoto.setImageResource(R.drawable.kitty)
                    1 -> activity.ivPhoto.setImageResource(R.drawable.corgi)
                }
            } // to close the onItemSelected

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
        activity.fbtEdit.setOnClickListener{
            if (activity.edNameValue.text.isEmpty() or activity.edAgeValue.text.isEmpty()) {
                Toast.makeText(this, R.string.registrationWarning, Toast.LENGTH_SHORT).show()
            }
            else {
                val pet = Pet(petId,activity.edNameValue.text.toString(),
                    activity.spinnerPetsType.selectedItem.toString(),
                    activity.edAgeValue.text.toString().toDouble(),
                    activity.ivPhoto.drawToBitmap(),
                    activity.edWeightValue.text.toString().toDouble(),
                    activity.edHeightValue.text.toString().toDouble(),
                    activity.edVaccinationDate.text.toString()
                )
                giveEditQuestion(pet)
            }

        }
    }

    fun giveEditQuestion(pet: Pet){
        val editQuestion = AlertDialog.Builder(this)
        editQuestion.setMessage(R.string.editQuestion)
        editQuestion.setPositiveButton(R.string.yes){ dialogInterface, i ->
            Thread{
                db.getDao().update(pet)
            }.start()
            Toast.makeText(this, R.string.doneEditing, Toast.LENGTH_SHORT).show()
            finish()
        }
        editQuestion.setNegativeButton(R.string.no){ dialogInterface, i ->
            finish()
        }
        editQuestion.show()
    }
    fun setCurrentData(idPet: Int){
        Thread{
            val pet = db.getDao().findById(idPet)
            activity.spinnerPetsType.setSelection(getPositionForSpinner(pet.type.toString()))
            activity.edNameValue.setText(pet.name)
            activity.edAgeValue.setText(pet.age.toString())
            activity.ivPhoto.setImageBitmap(pet.image)
            activity.edWeightValue.setText(pet.weight.toString())
            activity.edHeightValue.setText(pet.height.toString())
            activity.edVaccinationDate.setText(pet.vaccineDate)
        }.start()
    }
    fun getPositionForSpinner(type: String): Int{
        when(type){
            Constace.CatEN -> return 0
            Constace.CatUA -> return 0
            Constace.DogEU -> return 1
            Constace.DogUA -> return 1
        }
        return 0
    }
}