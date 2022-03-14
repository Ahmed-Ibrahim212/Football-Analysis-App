package com.example.footballdataapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.footballanalysis.utils.Resource
import com.example.footballdataapp.adapter.FootballDetailsAdapter
import com.example.footballdataapp.data.Squad
import com.example.footballdataapp.databinding.FragmentFootballDetailsBinding
import com.example.footballdataapp.viewmodel.FootballDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FootballDetailsFragment : Fragment() {
    private lateinit var footballDetailsAdapter: FootballDetailsAdapter
    private var _binding: FragmentFootballDetailsBinding? = null
    private val binding get() = _binding!!
    private val args : FootballDetailsFragmentArgs by navArgs()
    private val viewModel: FootballDetailsViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFootballDetailsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val teamId = args.teamId
        recyclerView = binding.detailsRecyclerView
        observers()
        viewModel.getUserResponse(teamId)
    }

    private fun observers() {
        viewModel.userResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    it.data?.let {
                        binding.footballDetailsProgressBar.visibility = View.INVISIBLE
                        setUpRecyclerView(it.squad)
                    }

                }
                is Resource.Error -> {
                    binding.footballDetailsProgressBar.visibility = View.INVISIBLE

                }
                is Resource.Loading -> {
                    binding.footballDetailsProgressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setUpRecyclerView(list: List<Squad>) {
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        footballDetailsAdapter = FootballDetailsAdapter(list)
        recyclerView.adapter = footballDetailsAdapter
    }

}