package com.example.footballleague.ui.competitiondetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballleague.model.Competition
import com.example.footballleague.model.Team
import com.example.footballleague.repository.CompetitionsRepository
import com.example.footballleague.repository.CompetitionsRepositoryImplementer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CompetitionDetailsViewModel : ViewModel() {

    private val repository : CompetitionsRepository = CompetitionsRepositoryImplementer()

    var thisCompetitionId: Int = 0

    private val _competitionDetails = MutableLiveData<Competition>()
    val competitionDetails: LiveData<Competition>
        get() = _competitionDetails

    private val _teamsList = MutableLiveData<ArrayList<Team>>()
    val teamsList: LiveData<ArrayList<Team>>
        get() = _teamsList

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean>
        get() = _isSuccess

    private val _failed = MutableLiveData<Boolean>()
    val failed: LiveData<Boolean>
        get() = _failed

    // number of tries for sending request
    private var retry: Int = 0

    init {
        retry = 0
        _isSuccess.value = false
        _failed.value = false
    }

    fun getCompetitionData(){
        _isSuccess.value = false
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getTeamsList(thisCompetitionId)
            Log.d("TAG", "getCompetitionData: $response")
            if(response!!.isSuccessful)
            {
                withContext(Dispatchers.Main) {
                    _isSuccess.value = true
                    _competitionDetails.value = response.body()!!.competition
                    _teamsList.value = response.body()!!.teams
                }
            } else {
                retry+=1
                if(retry<3)
                    withContext(Dispatchers.Main) {
                        getCompetitionData()
                    }
                else
                    withContext(Dispatchers.Main) {
                        _failed.value = true
                    }
            }
        }
    }

    // function to retry loading data
    fun tryGetCompetitionDetails(){
        retry = 0
        _failed.value = false
        getCompetitionData()
    }


}