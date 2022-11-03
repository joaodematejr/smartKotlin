package com.demate.appsmart

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.uol.pagseguro.plugpagservice.wrapper.PlugPag
import com.demate.appsmart.databinding.FragmentFirstBinding
import com.google.android.material.snackbar.Snackbar


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val plugPag: PlugPag? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    // PEGAR A SERIAL DA POS
    private fun getSerialNumber(view: View) {
        val deviceSerial = Build::class.java.getField("SERIAL")[null] as String
        Snackbar.make(view, "Version: $deviceSerial", Snackbar.LENGTH_LONG)
            .setAnchorView(R.id.fab)
            .setAction("Action", null).show()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.handleVersion.setOnClickListener{
            getSerialNumber(view);
        }

        binding.handleAtive.setOnClickListener{
            getSerialNumber(view);
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}