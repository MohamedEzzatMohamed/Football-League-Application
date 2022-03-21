package com.example.footballleague.ui.competitiondetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.footballleague.R
import com.example.footballleague.databinding.ItemListBinding
import com.example.footballleague.model.Competition
import com.example.footballleague.model.Team
import com.example.footballleague.ui.competitiondetails.CompetitionDetailsNavigator

class TeamsListAdapter (
    private var teamsList: ArrayList<Team>,
    private var itemListener: CompetitionDetailsNavigator
    ) : RecyclerView.Adapter<TeamsListAdapter.TeamsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        val binding: ItemListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.team_item_list, parent, false
        )
        return TeamsViewHolder(binding)
    }

    //bind the views from viewHolder with the data in arrayList
    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        val item = teamsList[position]
        holder.bind(itemListener, item)
    }

    //return num of array size
    override fun getItemCount() = teamsList.size

    class TeamsViewHolder(private val binding: ItemListBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: CompetitionDetailsNavigator, item: Team) {
//            binding. = item
//            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    //function for handling any data repetition
    fun addTeams(teamsList: ArrayList<Team>){
        this.teamsList.apply {
            clear()
            addAll(teamsList)
        }
    }
}