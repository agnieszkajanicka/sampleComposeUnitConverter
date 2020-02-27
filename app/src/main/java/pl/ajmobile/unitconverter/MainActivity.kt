package pl.ajmobile.unitconverter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.core.Text
import androidx.ui.core.TextField
import androidx.ui.core.setContent
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.material.RadioButton
import androidx.ui.material.RadioGroup
import androidx.ui.material.surface.Surface
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterUI()
        }
    }
}

@Preview
@Composable
fun UnitConverterUI() {
    MaterialTheme {
        Surface(color = Color.White) {
            Center {
                FlowColumn(crossAxisAlignment = FlowCrossAxisAlignment.Center) {
                    Text(text = "Kalkulator wag")
                    TextField(value = "tutaj wpisz wartość")
                    Spacer(modifier = LayoutHeight(20.dp))
                    Column {
                        RadioGroup {
                            Row {
                                RadioButton(selected = false, onSelect = {})
                                Text(text = "Kilogramy")
                            }
                            Row {
                                RadioButton(selected = false, onSelect = {})
                                Text(text = "Uncje")
                            }
                        }
                    }
                    Spacer(modifier = LayoutHeight(20.dp))
                    Button {
                        Text(text = "Przelicz")
                    }

                    Spacer(modifier = LayoutHeight(40.dp))

                    Text(text = "Wartość: ")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", style = MaterialTheme.typography().subtitle1)
}

//@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        Greeting("Android")
    }
}