package dv.trunov.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

val displayFont = FontFamily(
    Font(
        resource = "font/ds_digital.ttf",
        weight = FontWeight.Normal
    )
)

@Composable
fun calculatorRow(objects: List<Any>, calculatorState: CalculatorState) {
    Row (
        modifier = Modifier.padding(horizontal = 8.dp),
    ) {
        for (obj in objects) {
            if (obj is String) {
                digitButton(obj, calculatorState)
            } else if (obj is Operator) {
                operatorButton(obj, calculatorState)
            }
        }
    }
}

@Composable
fun RowScope.digitButton(digit: String, calculatorState: CalculatorState) {
    Button(
        modifier = Modifier
            .weight(1f)
            .height(64.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(20.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4A4A4A)),
        onClick = {
            calculatorState.appendDigit(digit)
        }
    ) {
        Text(
            text = digit,
            fontSize = 28.sp,
            fontFamily = FontFamily.Monospace
        )
    }
}

@Composable
fun RowScope.operatorButton(operator: Operator, calculatorState: CalculatorState) {
    var color = Color(0xFFF57C00)
    if (Operator.EQUAL == operator) {
        color = Color(0xFF2E7D32)
    } else if (Operator.CLEAN == operator) {
        color = Color(0xFFC62828)
    }
    Button(
        modifier = Modifier
            .weight(1f)
            .height(64.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(20.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
        ),
        onClick = {
            calculatorState.applyOperator(operator)
        }
    ) {
        Text(
            text = operator.symbol,
            fontSize = 28.sp,
            fontFamily = FontFamily.Monospace
        )
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Calculator",
        resizable = false,
        state = rememberWindowState(
            width = 380.dp,
            height = 460.dp)
    ) {
        val calculatorState = remember { CalculatorState() }

        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1E1E1E))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(12.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.Black)
            ) {
                Text(
                    text = calculatorState.display.value,
                    fontFamily = displayFont,
                    letterSpacing = 2.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    fontSize = 44.sp,
                    textAlign = TextAlign.End,
                    color = Color(0xFF7CFF7C)
                )
            }

            val row1Params = listOf<Any>(Operator.BACKSPACE, Operator.CLEAN, Operator.PERCENT, Operator.DIVIDE)
            val row2Params = listOf<Any>("7", "8", "9", Operator.MULTIPLY)
            val row3Params = listOf<Any>("4", "5", "6", Operator.MINUS)
            val row4Params = listOf<Any>("1", "2", "3", Operator.PLUS)
            val row5Params = listOf<Any>(Operator.PLUS_MINUS, "0", Operator.DOT, Operator.EQUAL)

            calculatorRow(row1Params, calculatorState)
            calculatorRow(row2Params, calculatorState)
            calculatorRow(row3Params, calculatorState)
            calculatorRow(row4Params, calculatorState)
            calculatorRow(row5Params, calculatorState)
        }
    }
}
