package com.example.runningapp.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RunDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)// kalo ada data yg id nya sama bakal ke jiplak sama data yg baru
    suspend fun insertRun(run: Run)

    @Delete
    suspend fun deleteRun(run: Run)

    @Query("SELECT * FROM running_table ORDER BY timestamp")
    fun getAllRunsSortedByDate(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY time")
    fun getAllRunsSortedByTime(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY caloriesBurned")
    fun getAllRunsSortedByCalories(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY avgSpeed")
    fun getAllRunsSortedByAvgSpeed(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY distance")
    fun getAllRunsSortedByDistance(): LiveData<List<Run>>

    @Query("SELECT SUM(time) FROM running_table")
    fun getTotalTime() : LiveData<Long>

    @Query("SELECT SUM(caloriesBurned) FROM running_table")
    fun getTotalCalories() : LiveData<Int>

    @Query("SELECT SUM(distance) FROM running_table")
    fun getTotalDistance() : LiveData<Int>

    @Query("SELECT AVG(avgSpeed) FROM running_table")
    fun getTotalAvgSpeed() : LiveData<Float>


}