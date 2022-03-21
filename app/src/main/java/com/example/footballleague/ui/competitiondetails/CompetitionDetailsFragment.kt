package com.example.footballleague.ui.competitiondetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballleague.R
import com.example.footballleague.databinding.FragmentCompetitionDetailsBinding
import com.example.footballleague.model.Team
import com.example.footballleague.ui.competitiondetails.adapter.TeamsListAdapter

class CompetitionDetailsFragment : Fragment(), CompetitionDetailsNavigator {


    private lateinit var binding : FragmentCompetitionDetailsBinding
    private val mArgs: CompetitionDetailsFragmentArgs by navArgs()
    private val viewModel: CompetitionDetailsViewModel by lazy {
        ViewModelProvider(this)[CompetitionDetailsViewModel::class.java]
    }
    private lateinit var teamsListAdapter: TeamsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val to bind the view with the data
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_competition_details, container, false)
        binding.lifecycleOwner = this
        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCompetitionsRecyclerView()

        viewModel.thisCompetitionId = mArgs.extraCompetitionId

        viewModel.getCompetitionData()

        (activity as AppCompatActivity).supportActionBar?.title = mArgs.extraCompetitionName

        viewModel.teamsList.observe(viewLifecycleOwner){ teamsList->
            retrieveTeamssList(teamsList)
        }
    }

    //set the main parameters for the recyclerView with the adapter
    private fun setCompetitionsRecyclerView() {
        binding.teamsListRecyclerView.layoutManager = LinearLayoutManager(context)
        teamsListAdapter = TeamsListAdapter(arrayListOf(), this)
        binding.teamsListRecyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.teamsListRecyclerView.context,
                (binding.teamsListRecyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.teamsListRecyclerView.adapter = teamsListAdapter
    }

    //get the competitions list from ViewModel
    private fun retrieveTeamssList(teamsListModel: ArrayList<Team>) {
        teamsListAdapter.apply {
            addTeams(teamsListModel)
            notifyDataSetChanged()
        }
    }

    override fun showTeamDetails(team: Team) {
        Log.d("TAG", "showTeamDetails: ${team.id}")
//        val action = CompetitionsListFragmentDirections
//            .actionCompetitionsListToCompetitionDetails(team.id, team.name)
//        findNavController().navigate(action)
    }

}