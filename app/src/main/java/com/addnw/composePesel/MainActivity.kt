package com.addnw.composePesel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import androidx.compose.ui.Modifier
import com.addnw.composePesel.ui.theme.ComposePeselTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePeselTheme {
                Surface(color = MaterialTheme.colors.background) {
                    PeselValidator()
                }
            }
        }
    }
}


@Composable
fun PeselValidator(peselViewModel: PeselViewModel = viewModel()) {
    val pesel: State<String> = peselViewModel.pesel.observeAsState("")

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        TextField(value = pesel.value, onValueChange = {new -> peselViewModel.onPeselChanged(new) })

        if (peselViewModel.valid) {
            Text(peselViewModel.birthdateText)
            Text(peselViewModel.genderText)
            Text(peselViewModel.correctText)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePeselTheme {
        PeselValidator()
    }
}