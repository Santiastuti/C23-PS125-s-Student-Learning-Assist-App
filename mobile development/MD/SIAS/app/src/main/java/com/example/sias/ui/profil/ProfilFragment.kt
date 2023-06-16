package com.example.sias.ui.profil

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sias.databinding.FragmentProfilBinding
import com.example.sias.ui.login.LoginActivity

class ProfilFragment : Fragment() {

    private var _binding: FragmentProfilBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel = ViewModelProvider(this).get(ProfilViewModel::class.java)

        _binding = FragmentProfilBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val txtName = binding.txtName
        val txtGender = binding.txtGender
        val txtEmail = binding.txtEmail
        val txtAddress = binding.txtAddress

        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val name = sharedPref.getString("name", "")
        val gender = sharedPref.getString("gender", "")
        val email = sharedPref.getString("email", "")
        val address = sharedPref.getString("address", "")

        txtName.setText(name)
        txtGender.setText(gender)
        txtEmail.setText(email)
        txtAddress.setText(address)

        val btnExit = binding.btnExit
        btnExit.setOnClickListener {
            val name = txtName.text.toString()
            val gender = txtGender.text.toString()
            val email = txtEmail.text.toString()
            val address = txtAddress.text.toString()

            val editor = sharedPref.edit()
            editor.putString("name", name)
            editor.putString("gender", gender)
            editor.putString("email", email)
            editor.putString("address", address)
            editor.apply()

            // Navigasi ke LoginActivity
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)

            // Tutup activity saat ini (ProfilFragment)
            activity?.finish()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
