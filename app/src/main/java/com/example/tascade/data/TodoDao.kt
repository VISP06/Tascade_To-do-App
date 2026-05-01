package com.example.tascade.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.example.tascade.model.Todo
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

//Data Access Object is basically an interface which defines a set of methods used to interact with the database
@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Update
    suspend fun updateTodo(todo:Todo)

    //in old code, we used _todos.sortBy{it.isCompleted} but we don't do that here instead we just change the way in which we receive the list
    //we get the list ordered in ascending order according to isCompleted
    @Query("SELECT * FROM todo_table ORDER BY isCompleted ASC")
    fun getAllTodos(): Flow<List<Todo>>
    // Because of the Flow return type, Room also runs the query on the background thread. You don't need to explicitly make it a suspend function and call it inside a coroutine scope.
}