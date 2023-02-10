package com.example.mypets

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.mypets.databinding.FragmentPetsBinding
import kotlinx.coroutines.flow.forEach

// TODO: Rename parameter arguments, choose names that match

class PetsFragment : Fragment(), PetAdapter.Listener {
    private lateinit var binding: FragmentPetsBinding
    private val adapter = PetAdapter(this)

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPetsBinding.inflate(inflater)
        return binding.root

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        //updateData()
        db = AppDatabase.getDb(requireContext())

        //adapter.addPet(Pet(null,"Tom", "Cat", 4, 2131296493))

        db.getDao().getAll().asLiveData().observe(viewLifecycleOwner){
            adapter.clearPets()
            adapter.addPet(it)
            init()
        }
    }
    private fun init() {
        binding.rvPets.layoutManager = LinearLayoutManager(this.context)
        binding.rvPets.adapter = adapter
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = PetsFragment()
    }

    override fun onClick(pet: Pet) {
        startActivity(Intent(requireContext(), PetActivityExtended::class.java).apply {
            putExtra(Constace.PetKey, pet.id)
        })

    }
}