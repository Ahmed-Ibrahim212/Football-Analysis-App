package com.example.footballdataapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextClock
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.footballdataapp.data.Competition
import com.example.footballdataapp.databinding.FragmentFootballDetailsBinding

class FootballDetailsAdapter(private var list: List<Competition>): RecyclerView.Adapter<FootballDetailsAdapter.MyViewHolder>() {
    inner class MyViewHolder(binding: FragmentFootballDetailsBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(competition: Competition){
            user.text = competition.name
            first.text = competition.lastUpdated
            users.text = competition.name
            second.text = competition.name
        }

        var user: TextView = binding.selectTeamTextView
        var first: TextView = binding.foundedDateTextView
        var users: TextView = binding.teamNameTextView
        var second: TextView = binding.addressNameTextView
        var phone: TextView = binding.phoneNumberUserTextView
        var website: TextView = binding.websiteUrlTextView
        var email: TextView = binding.emailUrlTextView

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FootballDetailsAdapter.MyViewHolder {
        val binding = FragmentFootballDetailsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FootballDetailsAdapter.MyViewHolder, position: Int) {
     holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}