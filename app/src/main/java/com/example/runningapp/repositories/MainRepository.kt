package com.example.runningapp.repositories

import com.example.runningapp.db.Run
import com.example.runningapp.db.RunDao
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val runDao: RunDao
) {
    suspend fun insertRun(run: Run) = runDao.insertRun(run)

    suspend fun deleteRun(run: Run) = runDao.deleteRun(run)

    fun getAllRunsSortedByDate() = runDao.getAllRunsSortedByDate()/// gausah pake korutin soalnya livedata udah async

    fun getAllRunsSortedByAvgSpeed() = runDao.getAllRunsSortedByAvgSpeed()

    fun getAllRunsSortedByCalories() = runDao.getAllRunsSortedByCalories()

    fun getAllRunsSortedByDistance() = runDao.getAllRunsSortedByDistance()

    fun getAllRunsSortedByTime() = runDao.getAllRunsSortedByTime()

    fun getTotalAvgSpeed() =  runDao.getTotalAvgSpeed()

    fun getTotalDistance() =  runDao.getTotalDistance()

    fun getTotalCalories() =  runDao.getTotalCalories()

    fun getTotalTime() = runDao.getTotalTime()

}