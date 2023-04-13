package com.example.mypets

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypets.databinding.FragmentHomeBinding
import com.example.mypets.databinding.FragmentPetsBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val adapter = MediaAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.addMedia(MediaContent.content)
        init()
        binding.imbttBMITest.setOnClickListener {
            startActivity(Intent(requireContext(), PetSelector::class.java).apply {
                putExtra(Constace.PetKey, RequestCode.BMITest)
            })
        }
        binding.imbttVaccineTest.setOnClickListener {
            startActivity(Intent(requireContext(), PetSelector::class.java).apply {
                putExtra(Constace.PetKey, RequestCode.VaccineTest)
            })
        }
        binding.ibAddShedule.setOnClickListener {
            startActivity(Intent(requireContext(), ScheduleActivity::class.java))
        }
        binding.btAboutDogs.setOnClickListener {
            startActivity(Intent(requireContext(), AboutDogs::class.java))
        }
        binding.btAboutCats.setOnClickListener {
            startActivity(Intent(requireContext(), AboutCats::class.java))
        }
        binding.ibAboutVitamins.setOnClickListener {
            startActivity(Intent(requireContext(), ActivityAboutVitamins::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }
    private fun init() {
        binding.rvMediaContent.layoutManager = LinearLayoutManager(this.context,  LinearLayoutManager.HORIZONTAL, false)
        binding.rvMediaContent.adapter = adapter
    }

    companion object {

    }
}