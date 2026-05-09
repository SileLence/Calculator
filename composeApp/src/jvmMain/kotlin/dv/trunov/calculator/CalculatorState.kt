package dv.trunov.calculator

import androidx.compose.runtime.mutableStateOf

class CalculatorState {

    val display = mutableStateOf("0")
    val firstNumber = mutableStateOf("0")
    var selectedOperator = mutableStateOf<Operator?>(null)

    fun appendDigit(digit: String) {
        if (display.value == "0") {
            display.value = digit
        } else {
            display.value += digit
        }
    }

    fun applyOperator(operator: Operator) {
        when (operator) {
            Operator.EQUAL -> {
                val operatorValue = selectedOperator.value
                if (operatorValue != null) {
                    calculate(operatorValue)
                    return
                }
            }
            Operator.CLEAN -> {
                display.value = "0"
                firstNumber.value = "0"
                selectedOperator.value = null
            }
            else -> {
                firstNumber.value = display.value
                selectedOperator.value = operator
                display.value += operator.symbol
            }
        }
    }

    fun calculate(operatorValue: Operator) {
        val split = display.value.split(Regex.escape(operatorValue.symbol).toRegex())
        val firstNumber = split[0].toDouble()
        var secondNumber = split[0].toDouble()
        if (split.size == 2) {
            secondNumber = split[1].toDouble()
        }
        when (selectedOperator.value) {
            Operator.PLUS -> display.value = (firstNumber + secondNumber).toString()
            Operator.MINUS -> display.value = (secondNumber - firstNumber).toString()
            Operator.MULTIPLY -> display.value = (secondNumber * firstNumber).toString()
            Operator.DIVIDE -> display.value = (secondNumber / firstNumber).toString()
            else -> {}
        }
    }
}
