package com.example.tascade.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tascade.model.Todo

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao
    companion object{
        @Volatile
        private var Instance: TodoDatabase? = null

        fun getDatabase(context: Context): TodoDatabase{
            // if the Instance is not null, return it, otherwise create a new database instance
            return Instance ?: synchronized(this){
                Room
                    .databaseBuilder(context, TodoDatabase::class.java, "todo_database")
                    .build()
                    .also{Instance = it}
            }
        }
    }
}

//The DAO belongs to the database, and the TodoRepository belongs to the rest of your codebase.