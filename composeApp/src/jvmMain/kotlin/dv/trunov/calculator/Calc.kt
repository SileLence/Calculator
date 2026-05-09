package dv.trunov.calculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
fun digitButton(digit: String, calculatorState: CalculatorState) {
    Button(
        modifier = Modifier.padding(4.dp),
        onClick = {
            calculatorState.appendDigit(digit)
        }
    ) {
        Text(digit)
    }
}

@Composable
fun operatorButton(operator: Operator, calculatorState: CalculatorState) {
    Button(
        modifier = Modifier.padding(4.dp),
        onClick = {
            calculatorState.applyOperator(operator)
        }
    ) {
        Text(operator.symbol)
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Calculator"
    ) {
        val calculatorState = remember { CalculatorState() }

        Column {

            Text(calculatorState.display.value)

            Row {
                operatorButton(Operator.BACKSPACE, calculatorState)
                operatorButton(Operator.CLEAN, calculatorState)
                operatorButton(Operator.PERCENT, calculatorState)
                operatorButton(Operator.DIVIDE, calculatorState)
            }
            Row {
                digitButton("7", calculatorState)
                digitButton("8", calculatorState)
                digitButton("9", calculatorState)
                operatorButton(Operator.MULTIPLY, calculatorState)
            }
            Row {
                digitButton("4", calculatorState)
                digitButton("5", calculatorState)
                digitButton("6", calculatorState)
                operatorButton(Operator.MINUS, calculatorState)
            }
            Row {
                digitButton("1", calculatorState)
                digitButton("2", calculatorState)
                digitButton("3", calculatorState)
                operatorButton(Operator.PLUS, calculatorState)
            }
            Row {
                operatorButton(Operator.PLUS_MINUS, calculatorState)
                digitButton("0", calculatorState)
                operatorButton(Operator.DOT, calculatorState)
                operatorButton(Operator.EQUAL, calculatorState)
            }
        }
    }
}
