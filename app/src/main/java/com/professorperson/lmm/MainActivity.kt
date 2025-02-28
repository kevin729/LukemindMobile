package com.professorperson.lmm


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.compositionLocalOf
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
import com.professorperson.lmm.models.Note
import com.professorperson.lmm.ui.theme.LMMTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


class MainActivity : ComponentActivity() {

    val notes = listOf(
        Note("NI number", "123456"),
        Note("Clothes", "Sheer tops")
    )

    data class DarkTheme(val isDark: Boolean = false)

    val LocalTheme = compositionLocalOf { DarkTheme() }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            var darkTheme by remember { mutableStateOf(true) }
            LMMTheme(darkTheme = darkTheme) {
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    ToolBar(darkTheme, {darkTheme = !darkTheme})
                }) { innerPadding ->
                        Column(Modifier.padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                            Header()
                            Body()
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
        Modifier.fillMaxWidth()
    ) {
            Card(Modifier.size(150.dp, 120.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary, contentColor = MaterialTheme.colorScheme.tertiary)) {
                Text(text = stringResource(R.string.morning_greeting), modifier = Modifier.padding(10.dp).fillMaxWidth(), fontSize = 14.sp, textAlign = TextAlign.Center, maxLines = 1)
                Text(text = stringResource(R.string.advice), modifier = Modifier.padding(10.dp).fillMaxWidth(), fontSize = 12.sp, textAlign = TextAlign.Justify)
            }



        Card(Modifier.size(150.dp, 120.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary, contentColor = MaterialTheme.colorScheme.tertiary)) {
            Text(text = stringResource(R.string.morning_greeting), modifier = Modifier.padding(10.dp).fillMaxWidth(), fontSize = 14.sp, textAlign = TextAlign.Center, maxLines = 1)
            Text(text = stringResource(R.string.advice), modifier = Modifier.padding(10.dp).fillMaxWidth(), fontSize = 12.sp, textAlign = TextAlign.Justify)
        }
    }
}

@Composable
fun Body() {
    Card(Modifier.size(300.dp, 300.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary, contentColor = MaterialTheme.colorScheme.tertiary)) {
        Text(text = stringResource(R.string.morning_greeting), modifier = Modifier.padding(40.dp).fillMaxWidth(), fontSize = 40.sp, textAlign = TextAlign.Center)
        Text(text = stringResource(R.string.advice), modifier = Modifier.padding(40.dp).fillMaxWidth(), fontSize = 20.sp, textAlign = TextAlign.Left)
    }
}




