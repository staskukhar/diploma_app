package com.example.mypets

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mypets.databinding.PetItem2Binding

class PetAdapter(val listener: Listener) : RecyclerView.Adapter<PetAdapter.PetHolder>() {
    private val petList = ArrayList<Pet>()
    class PetHolder(item: View, context: Context) : RecyclerView.ViewHolder(item) {
        val binding = PetItem2Binding.bind(item)
        val _context = context

        fun bind(pet: Pet, listener: Listener){
            binding.apply {
                ivImage.setImageBitmap(pet.image)
                tvTypeName.text = "${pet.type}: ${pet.name}"
                tvOld.text =  "${_context.getString(R.string.Age)}: ${pet.age}"
                tvShowWeight.text = "${_context.getString(R.string.Weight)}: ${pet.weight} ${Constace.kg}"
                tvShowHeight.text = "${_context.getString(R.string.Height)}: ${pet.height} ${Constace.cm}"
            }
            itemView.setOnClickListener{
                listener.onClick(pet)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pet_item2, parent, false)
        return PetHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: PetHolder, position: Int) {
        holder.bind(petList[position], listener)
    }

    override fun getItemCount(): Int {
        return petList.size
    }
    fun addPet(pet: Pet){
        petList.add(pet)
    }
    fun addPet(pets: List<Pet>){
        pets.forEach(){
            petList.add(it)
        }
    }
    fun clearPets(){
        petList.clear()
    }

    interface Listener{
        fun onClick(pet: Pet)
    }
}