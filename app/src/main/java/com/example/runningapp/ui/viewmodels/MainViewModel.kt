package com.example.runningapp.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runningapp.db.Run
import com.example.runningapp.other.SortType
import com.example.runningapp.repositories.MainRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

 class MainViewModel @ViewModelInject constructor(
    val mainRepository: MainRepository
) :ViewModel() {

     private val runsSortedByDate = mainRepository.getAllRunsSortedByDate()
     private val runsSortedByDistance = mainRepository.getAllRunsSortedByDistance()
     private val runsSortedByCaloriesBurned = mainRepository.getAllRunsSortedByCalories()
     private val runsSortedByTime = mainRepository.getAllRunsSortedByTime()
     private val runsSortedByAvgSpeed = mainRepository.getAllRunsSortedByAvgSpeed()

     val runs = MediatorLiveData<List<Run>>()

     var sortType = SortType.DATE // default value

     init {
         //check every sort by the SortType
         runs.addSource(runsSortedByDate) {result ->
             if(sortType == SortType.DATE){
                result?.let { runs.value = it }
             }
         }
         runs.addSource(runsSortedByAvgSpeed) {result ->
             if(sortType == SortType.AVG_SPEED){
                 result?.let { runs.value = it }
             }
         }
         runs.addSource(runsSortedByCaloriesBurned) {result ->
             if(sortType == SortType.CALORIES_BURNED){
                 result?.let { runs.value = it }
             }
         }
         runs.addSource(runsSortedByDistance) {result ->
             if(sortType == SortType.DISTANCE){
                 result?.let { runs.value = it }
             }
         }
         runs.addSource(runsSortedByTime) {result ->
             if(sortType == SortType.RUNNING_TIME){
                 result?.let { runs.value = it }
             }
         }
     }

     //when sortType Changed
     fun sortRuns(sortType: SortType) = when(sortType) {
         SortType.DATE -> runsSortedByDate.value?.let { runs.value = it }
         SortType.AVG_SPEED -> runsSortedByAvgSpeed.value?.let { runs.value = it }
         SortType.DISTANCE -> runsSortedByDistance.value?.let { runs.value = it }
         SortType.CALORIES_BURNED -> runsSortedByCaloriesBurned.value?.let { runs.value = it }
         SortType.RUNNING_TIME -> runsSortedByTime.value?.let { runs.value = it }
     }.also {
         this.sortType = sortType
     }


     fun insertRun(run: Run) = viewModelScope.launch {
        mainRepository.insertRun(run)
    }
}