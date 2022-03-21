package com.example.footballleague.ui.competitionslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballleague.model.Competition
import com.example.footballleague.repository.CompetitionsRepository
import com.example.footballleague.repository.CompetitionsRepositoryImplementer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CompetitionsListViewModel : ViewModel() {

    private val repository : CompetitionsRepository = CompetitionsRepositoryImplementer()
    
    private val _competitionsList = MutableLiveData<ArrayList<Competition>>()
    val competitionsList: LiveData<ArrayList<Competition>>
        get() = _competitionsList

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
        _competitionsList.value = ArrayList()
    }

    fun getCompetitionsList(){
        _isSuccess.value = false
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getCompetitionsList()
            if(response!!.isSuccessful)
            {
                withContext(Dispatchers.Main) {
                    _isSuccess.value = true
                    _competitionsList.value = response.body()!!.competitions!!
                }
            } else {
                retry+=1
                if(retry<3)
                    getCompetitionsList()
                else
                    _failed.value = true
            }
        }
    }

    // function to retry loading data
    fun tryGetCompetitionsList(){
        retry = 0
        _failed.value = false
        getCompetitionsList()
    }


}