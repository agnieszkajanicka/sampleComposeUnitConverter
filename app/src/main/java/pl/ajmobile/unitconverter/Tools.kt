package pl.ajmobile.unitconverter

import android.util.FloatMath
import androidx.compose.Model

enum class Unit(val unitName: String) {
    KG("kilogramy"),
    OZ("uncje")
}

@Model
class CalculatorModel(
    var sourceUnit: Unit,
    var destinationUnit: Unit,
    var inputValue: Float = 0f,
    var result: Float = 0f
) {
    fun calculate(){
        result = if (sourceUnit == destinationUnit) {
            inputValue
        } else {
            when (sourceUnit) {
                Unit.KG -> inputValue * 35.2739619f
                Unit.OZ -> inputValue * 0.0283495231f
            }
        }
    }
}


fun String.toUnit(): Unit? {
    return when (this) {
        Unit.OZ.unitName -> Unit.OZ
        Unit.KG.unitName -> Unit.KG
        else -> null
    }
}