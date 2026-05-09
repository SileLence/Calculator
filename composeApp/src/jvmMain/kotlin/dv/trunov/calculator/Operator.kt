package dv.trunov.calculator

enum class Operator(val symbol: String) {
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    EQUAL("="),
    CLEAN("C"),
    PERCENT("%"),
    PLUS_MINUS("+/-"),
    BACKSPACE("<"),
    DOT(".")
}
