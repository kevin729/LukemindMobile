package com.professorperson.lmm


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Room
import com.professorperson.lmm.models.Note
import com.professorperson.lmm.models.database.LMDatabase
import com.professorperson.lmm.ui.theme.LMMTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val lmdb = Room.databaseBuilder(
            applicationContext,
            LMDatabase::class.java, "lukemindDB"
        ).build()

        Thread() {
            lmdb.noteDAO().deleteAll()
        }.start()


        setContent {
            var darkTheme by remember { mutableStateOf(true) }
            LMMTheme(darkTheme = darkTheme) {
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    ToolBar(darkTheme, {darkTheme = !darkTheme})
                }) { innerPadding ->
                    Column(
                        Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .padding(20.dp, 0.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround) {
                        Header()
                        ListTasks(lmdb)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBar(darkTheme: Boolean, callback: () -> Unit) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.tertiary,
        ),
        title = {
            Text("LukeMind")
        },
        navigationIcon = {
            IconButton(onClick = {/* Go Back*/}) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }


        },
        actions = {
            IconButton(onClick = callback) {
                Icon(
                    painter = if (darkTheme) painterResource(R.drawable.ic_light_mode) else painterResource(R.drawable.ic_dark_mode) ,
                    contentDescription = "Dark Mode"
                )
            }
        }
    )
}

@Composable
fun Header() {
    Row(
        Modifier.fillMaxWidth(),
        Arrangement.SpaceBetween
    ) {
        Card(Modifier.size(160.dp, 120.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary, contentColor = MaterialTheme.colorScheme.tertiary)) {
            Column(Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = stringResource(R.string.morning_greeting), modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(), fontSize = 14.sp, textAlign = TextAlign.Center, maxLines = 1)

                IconButton(onClick = {/* Go Back*/}) {
                    Icon(
                        painter = painterResource(R.drawable.ic_cog),
                        contentDescription = "notes"
                    )
                }
            }
        }

        Card(Modifier.size(160.dp, 120.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary, contentColor = MaterialTheme.colorScheme.tertiary)) {
            Column(Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = stringResource(R.string.timer), modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(), fontSize = 16.sp, textAlign = TextAlign.Center, maxLines = 1)
                IconButton(onClick = {/* Go Back*/}) {
                    Icon(
                        painter = painterResource(R.drawable.ic_cog),
                        contentDescription = "Cog"
                    )
                }
            }
        }
    }
}

@Composable
fun ListTasks(lmdb: LMDatabase) {
    var noteList = emptyList<Note>()
    val thread = Thread {
        noteList = lmdb.noteDAO().getAll()
    }
    thread.start()
    thread.join()

    var notes by remember {
        mutableStateOf(noteList)
    }

    Column(Modifier.fillMaxWidth().fillMaxHeight(0.8f).background(MaterialTheme.colorScheme.tertiary), horizontalAlignment = Alignment.End) {
        Column(Modifier.fillMaxHeight(0.5f).verticalScroll(rememberScrollState())) {
            notes.forEach() {
                Card() {
                    Text(text = it.date)
                    Text(text = it.title)
                    Text(text = it.text)
                }

                Spacer(Modifier.height(20.dp))
            }
        }

        IconButton(onClick = {
            val thread = Thread {
                lmdb.noteDAO().insert(Note("Testtitle", "TestText"))
                notes = lmdb.noteDAO().getAll()
            }

            thread.start()

        }) {
            Icon(
                painter = painterResource(R.drawable.ic_cog),
                contentDescription = "Cog"
            )
        }
    }
}







