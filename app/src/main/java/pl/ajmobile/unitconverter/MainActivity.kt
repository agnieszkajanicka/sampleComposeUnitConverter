package pl.ajmobile.unitconverter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.Text
import androidx.ui.core.TextField
import androidx.ui.core.setContent
import androidx.ui.graphics.Color
import androidx.ui.input.KeyboardType
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.material.RadioGroup
import androidx.ui.material.surface.Surface
import androidx.ui.text.TextFieldValue
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
    val units = state { TextFieldValue("20") }
    val radioOptions = listOf(Unit.KG.unitName, Unit.OZ.unitName)
    val (selectedOption, onOptionSelected) = state { radioOptions.first() }

    val calculatedValue = state { 0f }

    MaterialTheme {
        Surface(color = Color.White) {
            Center {
                FlowColumn(crossAxisAlignment = FlowCrossAxisAlignment.Center) {
                    Text(text = "Kalkulator wag")
                    TextField(
                        keyboardType = KeyboardType.Number,
                        value = units.value,
                        onValueChange = { textFieldValue -> units.value = textFieldValue })
                    Spacer(modifier = LayoutHeight(20.dp))
                    Column {
                        RadioGroup(
                            options = radioOptions,
                            selectedOption = selectedOption,
                            onSelectedChange = onOptionSelected
                        )
                    }
                    Spacer(modifier = LayoutHeight(20.dp))
                    Button(onClick = {
                        calculatedValue.value = calculateValue(selectedOption, units.value.text)
                    }) {
                        Text(text = "Przelicz")
                    }

                    Spacer(modifier = LayoutHeight(40.dp))

                    Text(text = "Wartość:  ${calculatedValue.value}")
                }
            }
        }
    }
}

fun calculateValue(unit: String, value: String): Float {
    val floatValue = value.toFloat()
    return when (unit) {
        Unit.KG.unitName -> {
            floatValue * 35.2739619f
        }
        Unit.OZ.unitName -> {
            floatValue * 0.0283495231f
        }
        else -> 0f
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