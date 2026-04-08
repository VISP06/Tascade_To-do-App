package com.example.tascade.ui.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.tascade.R
import com.example.tascade.TodoViewModel
import com.example.tascade.model.Todo
import com.example.tascade.ui.components.TodoCard
import com.example.tascade.ui.theme.TascadeTheme
import com.example.tascade.util.halftoneBackground

val vm = TodoViewModel()
@Composable
fun TodoApp(){
    Scaffold(
        topBar = {TodoTopBar()},
        floatingActionButton = { TodoFAB() }
    ) { innerPadding->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFACC15))
                .halftoneBackground(),
            contentAlignment = Alignment.Center

        ) {

            TodoList(vm.todos, contentPaddingValues =innerPadding)

        }
    }
}

@Composable
fun TodoList(tasks:List<Todo>, modifier: Modifier = Modifier, contentPaddingValues: PaddingValues){
    if(tasks.isEmpty()){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(R.drawable.part_1_illustration),
                contentDescription = null

            )
            Image(
                painter = painterResource(R.drawable.part_2_illustration),
                contentDescription = null
            )
        }
    }else {
        LazyColumn(modifier = Modifier, contentPadding = contentPaddingValues) {
            items(items = tasks, key = { task -> task.id }) { task ->
                Spacer(Modifier.padding(8.dp))
                TodoCard(
                    task = task,
                    modifier = Modifier.animateItem()
                )
            }
        }
    }
}
@Composable
fun TodoTopBar(modifier:Modifier = Modifier){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .background(Color(0xFF7B1FA2))
            .border(width = 3.dp, color = Color.Black)
            .padding(start = 36.dp, top = 40.dp)
    ){

        Text(
            text = stringResource(R.string.top_bar_title),
            color = Color.White,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 5.sp,
            fontSize = 64.sp,
            modifier = modifier

        )
    }
}

@Composable
fun AddTodoDialog(showDialog:Boolean, onDismiss: () -> Unit){
    var userTodoInput by remember{mutableStateOf("")}
    Dialog(onDismissRequest = {onDismiss()}) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Black)
                .padding(start = 5.dp, top = 5.dp)
                .size(300.dp)
        ){
            Box(
                Modifier
                    .offset(x = -4.dp, y = -4.dp)
                    .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
                    .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                    .fillMaxSize()
                    .padding(vertical = 16.dp)
            ){
                Text(
                    text = "New_Task",
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(start = 12.dp, top = 8.dp)
                )
                Column() {
                    TextField(
                        value = userTodoInput,
                        onValueChange = { userTodoInput = it },
                        label = {Text("Task Description")},
                        modifier = Modifier
                            .border(width = 3.dp, color = Color.Black)
                            .background(color = Color.DarkGray)
                    )
                    Row(){

                    }
                }
            }
        }
    }
}
@Composable
fun TodoFAB(modifier:Modifier = Modifier) {
    var showAddDialog by remember{ mutableStateOf<Boolean>(false) }
    if(showAddDialog){
        AddTodoDialog(showAddDialog, onDismiss = {showAddDialog = false})
    }
    Box(
        Modifier
            .clip(CircleShape)
            .background(Color.Black)
            .offset(x = -4.dp, y = -4.dp)
    ) {
        LargeFloatingActionButton(
            onClick = {
                showAddDialog = true
                      },
            shape = CircleShape,
            containerColor = Color(0xFF1A237E),
            modifier = Modifier.border(3.dp, Color.Black, shape = CircleShape)
        ) {
            Icon(
                Icons.Filled.Add,
                "Floating action button.",
                modifier = Modifier
                    .size(50.dp),
                tint = Color.White
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun TodoAppPreview() {
    TascadeTheme {
        TodoApp()
    }
}


