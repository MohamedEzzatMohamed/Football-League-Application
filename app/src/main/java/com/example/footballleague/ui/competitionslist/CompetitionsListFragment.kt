package com.example.footballleague.ui.competitionslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballleague.R
import com.example.footballleague.databinding.FragmentCompetitionsListBinding
import com.example.footballleague.model.Competition
import com.example.footballleague.ui.competitionslist.adapter.CompetitionsListAdapter


class CompetitionsListFragment : Fragment(), CompetitionsNavigator {

    private lateinit var binding : FragmentCompetitionsListBinding

    private val viewModel: CompetitionsListViewModel by lazy {
        ViewModelProvider(this)[CompetitionsListViewModel::class.java]
    }
    private lateinit var competitionsListAdapter: CompetitionsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val to bind the view with the data
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_competitions_list, container, false)
        binding.lifecycleOwner = this
        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCompetitionsRecyclerView()

        viewModel.getCompetitionsList()

        viewModel.competitionsList.observe(viewLifecycleOwner){ competitionsList->
            retrieveCompetitionsList(competitionsList)
        }
    }

    override fun onResume() {
        super.onResume()
        // change actionBar name with the application name
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

    }

    //set the main parameters for the recyclerView with the adapter
    private fun setCompetitionsRecyclerView() {
        binding.competitionsListRecyclerView.layoutManager = LinearLayoutManager(context)
        competitionsListAdapter = CompetitionsListAdapter(arrayListOf(), this)
        binding.competitionsListRecyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.competitionsListRecyclerView.context,
                (binding.competitionsListRecyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.competitionsListRecyclerView.adapter = competitionsListAdapter
    }

    //get the competitions list from ViewModel
    private fun retrieveCompetitionsList(competitionsListModel: ArrayList<Competition>) {
        competitionsListAdapter.apply {
            addCompetitions(competitionsListModel)
            notifyDataSetChanged()
        }
    }

    override fun showCompetitionDetails(competition: Competition) {
        val action = CompetitionsListFragmentDirections
            .actionCompetitionsListToCompetitionDetails(competition.id, competition.name)
        findNavController().navigate(action)
    }
}