package com.example.footballdataapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.footballdataapp.databinding.TeamListRecyclerviewBinding
import com.example.footballdataapp.data.TeamsDataClass.Team

class TeamListAdapter(private var list: List<Team>) :
    RecyclerView.Adapter<TeamListAdapter.TeamViewHolder>() {
    inner class TeamViewHolder(binding: TeamListRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(team: Team) = with(itemView) {
            Glide.with(context).load(team.crestUrl).into(user)
        }

        var user: ImageView = binding.teamsImage
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TeamListAdapter.TeamViewHolder {
        val binding =
            TeamListRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamListAdapter.TeamViewHolder, position: Int) {
        holder.bind(list[position])
    }


    override fun getItemCount(): Int {
        return list.size
    }
}