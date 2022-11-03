package com.demate.appsmart

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.uol.pagseguro.plugpagservice.wrapper.PlugPag
import br.com.uol.pagseguro.plugpagservice.wrapper.PlugPagAppIdentification
import com.demate.appsmart.databinding.FragmentFirstBinding
import com.google.android.material.snackbar.Snackbar


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var _binding: FragmentFirstBinding? = null
    private var plugPag: PlugPag? = null
    private val appIdentification: PlugPagAppIdentification? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }


    private fun renderMsg(view: View, txt: String) {
        Snackbar.make(view, "Version: $txt", Snackbar.LENGTH_LONG)
            .setAnchorView(R.id.fab)
            .setAction("Action", null).show()
    }

    // PEGAR A SERIAL DA POS
    private fun getSerialNumber(view: View) {
        val deviceSerial = Build::class.java.getField("SERIAL")[null] as String
        renderMsg(view, deviceSerial)
    }

    private fun getLibVersion(view: View) {
        val appContext = context!!.applicationContext
        val (context) = PlugPagAppIdentification(appContext)
        val plugpag = PlugPag(context)

        var abc = plugpag.isAuthenticated()

        renderMsg(view, abc.toString())
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.handleVersion.setOnClickListener {
            getSerialNumber(view);
        }

        binding.handleAtive.setOnClickListener {
            getLibVersion(view);
        }


    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}