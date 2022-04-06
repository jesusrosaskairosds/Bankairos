package com.jesusrosas.kairosds.bankairos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.jesusrosas.kairosds.bankairos.databinding.FragmentBasicBinding
import com.jesusrosas.kairosds.bankairos.databinding.FragmentFirstBinding


class BasicFragment : Fragment() {

    private var _binding: FragmentBasicBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBasicBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBasic.setOnClickListener {
            findNavController().navigate(R.id.action_basicFragment_to_homeActivity)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}