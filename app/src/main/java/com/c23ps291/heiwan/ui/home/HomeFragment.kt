package com.c23ps291.heiwan.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.c23ps291.heiwan.databinding.FragmentHomeBinding
import com.c23ps291.heiwan.ui.common.adapter.AnimalListAdapter
import com.c23ps291.heiwan.ui.common.adapter.LoadingStateAdapter
import com.c23ps291.heiwan.utils.ViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels {
        ViewModelFactory(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvAnimal.layoutManager = GridLayoutManager(requireActivity(), 2)

        getData()
    }

    private fun getData() {
        val adapter = AnimalListAdapter()
        binding.rvAnimal.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )
        viewModel.getListAnimal().observe(requireActivity()) {
            if(adapter.itemCount == 0) {
                showEmptyState(true)
            } else {
                showEmptyState(false)
            }
            adapter.submitData(lifecycle, it)
        }
    }

    private fun showEmptyState(isLoading: Boolean) {
        binding.emptyState.root.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}