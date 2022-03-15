package com.example.footballdataapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.footballdataapp.data.Squad
import com.example.footballdataapp.databinding.FootballDetailsRecyclerviewBinding

class FootballDetailsAdapter(private var list: List<Squad>) :
    RecyclerView.Adapter<FootballDetailsAdapter.MyViewHolder>() {
    inner class MyViewHolder(binding: FootballDetailsRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(squad: Squad){
            user.text = squad.position
            users.text = squad.nationality
            date.text = squad.dateOfBirth
            first.text = squad.name


        }

        var user: TextView = binding.playerPosition
        var first: TextView = binding.recyclerPlayerId
        var users: TextView = binding.countryId
        var date: TextView = binding.dateOfBirth


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FootballDetailsAdapter.MyViewHolder {
        val binding = FootballDetailsRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FootballDetailsAdapter.MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}