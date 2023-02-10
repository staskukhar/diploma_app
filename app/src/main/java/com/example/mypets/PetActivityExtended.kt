package com.example.mypets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mypets.databinding.ActivityPetExtendedBinding
import kotlin.properties.Delegates

class PetActivityExtended : AppCompatActivity() {
    private lateinit var activity: ActivityPetExtendedBinding
    private lateinit var db: AppDatabase
    private var petId by Delegates.notNull<Int>()
    private var currentPet: Pet? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = ActivityPetExtendedBinding.inflate(layoutInflater)
        setContentView(activity.root)

        petId = intent.getIntExtra(Constace.PetKey,0)
    }

    override fun onResume() {
        super.onResume()
        db = AppDatabase.getDb(this)
        if(petId > 0){
            Thread{
                currentPet = db.getDao().findById(petId)
            }.start()
        }
        while(currentPet == null){

        }
        currentPet?.let { showPetInfo(it) }
        activity.editTools.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.itemDelete ->{
                    giveDeleteQuestion(petId)
                }
                R.id.itemEdit ->{
                    startActivity(Intent(this, ActivityEditPet::class.java).apply {
                        putExtra(Constace.PetKey, petId)
                    })
                    finish()
                }
            }
            true
        }
    }
    fun showPetInfo(pet: Pet){
        activity.imPhoto.setImageBitmap(pet.image)
        activity.tvTypeNameE.text = "${pet.type}: ${pet.name}"
        activity.tvOldE.text =  "${this.getString(R.string.Age)} ${pet.age}"
        activity.tvShowWeightE.text = "${this.getString(R.string.Weight)}: ${pet.weight} ${Constace.kg}"
        activity.tvShowHeightE.text = "${this.getString(R.string.Height)}: ${pet.height} ${Constace.cm}"
        activity.tvShowDateVaccinationE.text = "${getString(R.string.VaccineDate)}: ${pet.vaccineDate}"
    }
    fun giveDeleteQuestion(petId: Int){
        val deleteQuestion = AlertDialog.Builder(this)
        deleteQuestion.setMessage(R.string.deleteQuestion)
        deleteQuestion.setPositiveButton(R.string.yes){ dialogInterface, i ->
            Thread{
                db.getDao().delete(db.getDao().findById(petId))
            }.start()
            Toast.makeText(this, R.string.doneDeleting, Toast.LENGTH_SHORT).show()
            finish()
        }
        deleteQuestion.setNegativeButton(R.string.no){ dialogInterface, i -> }
        deleteQuestion.show()
    }

}