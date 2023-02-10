package com.example.mypets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.drawToBitmap
import com.example.mypets.databinding.FragmentAddPetBinding


class EventsFragment : Fragment() {
    private var _binding: FragmentAddPetBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase.getDb(requireContext())


        binding.spinnerPetsF.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                when(position){
                    0 -> binding.ivPhotoF.setImageResource(R.drawable.kitty)
                    1 -> binding.ivPhotoF.setImageResource(R.drawable.corgi)
                }
            } // to close the onItemSelected

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        binding.btRegisterF.setOnClickListener {
            if (binding.edSetName.text.isEmpty() or binding.edSetAge.text.isEmpty() or binding.edSetWeight.text.isEmpty() or binding.edSetHeight.text.isEmpty()) {
                Toast.makeText(this.context, R.string.registrationWarning, Toast.LENGTH_SHORT).show()
            }
            else {
                val pet = Pet(null ,binding.edSetName.text.toString(),
                    binding.spinnerPetsF.selectedItem.toString(),
                    binding.edSetAge.text.toString().toInt(),
                    binding.ivPhotoF.drawToBitmap(),
                    binding.edSetWeight.text.toString().toDouble(),
                    binding.edSetHeight.text.toString().toDouble(),
                    null
                )

                Thread{
                    db.getDao().insert(pet)
                }.start()
                toDefaultFields()
                Toast.makeText(requireContext(), R.string.doneRegistering, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun toDefaultFields() {
        binding.edSetName.text.clear()
        binding.edSetAge.text.clear()
        binding.spinnerPetsF.setSelection(0)
        binding.ivPhotoF.setImageResource(R.drawable.kitty)
        binding.edSetWeight.text.clear()
        binding.edSetHeight.text.clear()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddPetBinding.inflate(inflater, container,false)
        val view = binding.root
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        //@JvmStatic
        //fun newInstance() = EventsFragment()
    }
}