package com.example.avocado_android.ui.vocalist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.avocado_android.R
import com.example.avocado_android.base.BaseFragment
import com.example.avocado_android.databinding.FragmentVocaListBinding

class VocaListFragment : BaseFragment<FragmentVocaListBinding>(R.layout.fragment_voca_list) {
    override fun setLayout() {
        toast()
    }

    fun toast() {
        Toast.makeText(requireContext(), "vocalist", Toast.LENGTH_SHORT).show()
    }

}