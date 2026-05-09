package dv.trunov.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

@Composable
fun RowScope.digitButton(digit: String, calculatorState: CalculatorState) {
    Button(
        modifier = Modifier
            .weight(1f)
            .height(64.dp)
            .padding(4.dp),
        onClick = {
            calculatorState.appendDigit(digit)
        }
    ) {
        Text(digit)
    }
}

@Composable
fun RowScope.operatorButton(operator: Operator, calculatorState: CalculatorState) {
    Button(
        modifier = Modifier
            .weight(1f)
            .height(64.dp)
            .padding(4.dp),
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
        title = "Calculator",
        state = rememberWindowState(
            width = 320.dp,
            height = 520.dp)
    ) {
        val calculatorState = remember { CalculatorState() }

        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.Black)
            ) {
                Text(
                    text = calculatorState.display.value,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    fontSize = 32.sp,
                    textAlign = TextAlign.End,
                    color = Color.Green
                )
            }

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
