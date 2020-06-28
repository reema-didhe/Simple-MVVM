package com.example.mvvm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*for getting DAO's and entities together*/

@Database(entities = [User::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val usersDao: UsersDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context):AppDatabase{
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}