package com.example.mvvm.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/*for database CRUD operations*/

@Dao
interface UsersDao{

    /*for insert operation*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user : List<User>):List<Long>

    @Query("SELECT * FROM users")
    fun getAllUsers() : List<User>

}