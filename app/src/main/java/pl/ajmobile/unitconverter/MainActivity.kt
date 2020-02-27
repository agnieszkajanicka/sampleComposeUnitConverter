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
    val inputTextValue = state { TextFieldValue("20") }
    val radioOptions = listOf(Unit.KG.unitName, Unit.OZ.unitName)
    val (selectedSourceOption, onSourceOptionSelected) = state { radioOptions.first() }
    val (selectedDestinationOption, onDestinationOptionSelected) = state { radioOptions[1] }

    val model = state {
        CalculatorModel(
            sourceUnit = selectedSourceOption.toUnit()!!,
            destinationUnit = selectedDestinationOption.toUnit()!!,
            inputValue = inputTextValue.value.text.toFloat()
        )
    }

    MaterialTheme {
        Surface(color = Color.White) {
            Center {
                FlowColumn(crossAxisAlignment = FlowCrossAxisAlignment.Center) {
                    Text(text = "Kalkulator wag")
                    Spacer(modifier = LayoutHeight(20.dp))
                    TextField(
                        keyboardType = KeyboardType.Number,
                        value = inputTextValue.value,
                        onValueChange = { textFieldValue -> inputTextValue.value = textFieldValue })

                    Column {
                        RadioGroup(
                            options = radioOptions,
                            selectedOption = selectedSourceOption,
                            onSelectedChange = onSourceOptionSelected
                        )
                    }

                    Spacer(modifier = LayoutHeight(20.dp))
                    Text(text = "Na jednostkę:")
                    Column {
                        RadioGroup(
                            options = radioOptions,
                            selectedOption = selectedDestinationOption,
                            onSelectedChange = onDestinationOptionSelected
                        )
                    }

                    Spacer(modifier = LayoutHeight(20.dp))
                    Button(onClick = { model.value.calculate() }) {
                        Text(text = "Przelicz")
                    }

                    Spacer(modifier = LayoutHeight(40.dp))

                    Text(text = "Wartość:  ${model.value.result}")
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