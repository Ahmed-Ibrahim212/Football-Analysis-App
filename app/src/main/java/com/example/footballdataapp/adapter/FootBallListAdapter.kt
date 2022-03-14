package com.example.footballdataapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.footballdataapp.data.Competition
import com.example.footballdataapp.databinding.FootballListItemBinding
import com.example.footballdataapp.ui.FootballDataFragmentDirections


class FootBallListAdapter(private var list: List<Competition>) :
    RecyclerView.Adapter<FootBallListAdapter.FootBallViewHolder>() {

    inner class FootBallViewHolder(binding: FootballListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(competition: Competition) = with(itemView){
            user.text = competition.name
            first.text = competition.currentSeason.startDate
            users.text = competition.area.name
            itemView.setOnClickListener {
                val directions = FootballDataFragmentDirections.actionFootballDataFragmentToTeamsFragment(competition.id)
                itemView.findNavController().navigate(directions)
            }
        }

        var user: TextView = binding.leagueNameTextView
        var users: TextView = binding.countryTextView
        var first: TextView = binding.dateTextView
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FootBallListAdapter.FootBallViewHolder {
        val binding =
            FootballListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FootBallViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FootBallListAdapter.FootBallViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}