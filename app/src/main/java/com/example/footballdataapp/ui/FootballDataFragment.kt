package com.example.footballdataapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.footballanalysis.utils.Resource
import com.example.footballdataapp.adapter.FootBallListAdapter
import com.example.footballdataapp.data.Competition
import com.example.footballdataapp.data.CompetitionDataClass
import com.example.footballdataapp.databinding.FragmentFootballDataBinding
import com.example.footballdataapp.viewmodel.FootballListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FootballDataFragment : Fragment() {
    private lateinit var footBallListAdapter: FootBallListAdapter
    private var _binding: FragmentFootballDataBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FootballListViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFootballDataBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.footballListRecyclerView

        observers()
        viewModel.getUserResponse()
    }


    //checking for network on success
    private fun observers() {
        viewModel.userResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    it.data?.let {
                        binding.footballProgressBar.visibility = View.INVISIBLE
                        setUpRecyclerView(it.competitions)
                    }

                }
                is Resource.Error -> {
                    binding.footballProgressBar.visibility = View.INVISIBLE

                }
                is Resource.Loading -> {
                    binding.footballProgressBar.visibility = View.VISIBLE
                }
            }
        }
    }


    //setting up the recyclerview with the adapterclass
    fun setUpRecyclerView(list: List<Competition>) { //setting the recyclerView to gridlayout
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        footBallListAdapter = FootBallListAdapter(list)
        recyclerView.adapter = footBallListAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}