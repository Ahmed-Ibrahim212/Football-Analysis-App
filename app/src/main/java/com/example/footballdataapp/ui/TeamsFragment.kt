package com.example.footballdataapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.footballanalysis.utils.Resource
import com.example.footballdataapp.R
import com.example.footballdataapp.adapter.TeamListAdapter
import com.example.footballdataapp.data.Teams
import com.example.footballdataapp.databinding.FragmentTeamsBinding
import com.example.footballdataapp.viewmodel.TeamsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamsFragment : Fragment() {
    private var _binding: FragmentTeamsBinding? = null
    private val binding get() = _binding!!
    private lateinit var teamListAdapter: TeamListAdapter
    private val args : TeamsFragmentArgs by navArgs()
    private var leagueId =0
    private lateinit var recyclerView: RecyclerView
    private val viewModel: TeamsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTeamsBinding.inflate(layoutInflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        leagueId = args.competitionId
        recyclerView = binding.teamsRecyclerView
        viewModel.fetchLeagueImage(leagueId)
        observers()
    }

    private fun observers() {
        viewModel.teamsResponse.observe(viewLifecycleOwner) {

            when (it) {
                is Resource.Success -> {
                    it.data?.let {
                        Log.d("userResponse", it.toString())
                        binding.teamsProgressBar.visibility = View.INVISIBLE
                        setUpRecyclerView(it)
                    }
                }
                is Resource.Error -> {
                    binding.teamsProgressBar.visibility = View.INVISIBLE
                    Snackbar.make(requireView(), R.string.api_permission_denied,Snackbar.LENGTH_INDEFINITE).show()
                }
                is Resource.Loading -> {
                    binding.teamsProgressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setUpRecyclerView(list: Teams) {
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        teamListAdapter = TeamListAdapter(list)
        recyclerView.adapter = teamListAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}