package com.example.footballleague.ui.competitionslist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.footballleague.R
import com.example.footballleague.databinding.ItemListBinding
import com.example.footballleague.model.Competition
import com.example.footballleague.ui.competitionslist.CompetitionsNavigator


class CompetitionsListAdapter (
    private var competitionsList: ArrayList<Competition>,
    private var itemListener: CompetitionsNavigator
    ) : RecyclerView.Adapter<CompetitionsListAdapter.CompetitionsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitionsViewHolder {
        val binding: ItemListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_list, parent, false
        )
        return CompetitionsViewHolder(binding)
//        return MoviesViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    //bind the views from viewHolder with the data in arrayList
    override fun onBindViewHolder(holder: CompetitionsViewHolder, position: Int) {
        val item = competitionsList[position]
        holder.bind(itemListener, item)
    }

    //return num of array size
    override fun getItemCount() = competitionsList.size

    class CompetitionsViewHolder(private val binding: ItemListBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: CompetitionsNavigator, item: Competition) {
            binding.competition = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    //function for handling any data repetition
    fun addCompetitions(competitionsList: ArrayList<Competition>){
        this.competitionsList.apply {
            clear()
            addAll(competitionsList)
        }
    }
}