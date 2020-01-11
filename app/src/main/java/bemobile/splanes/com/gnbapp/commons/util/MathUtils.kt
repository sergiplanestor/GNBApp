package bemobile.splanes.com.gnbapp.commons.util

import java.math.BigDecimal

object MathUtils {

    fun multiply(number1: String, number2: String) : String {
        val num1 = format(number1)
        val num2 = format(number2)
        return  if (num1 != null && num2 != null) round(num1.toFloat() * num2.toFloat())
                else ""
    }

    fun format(number: String) : String? {
        return  if (number.isBlank() || number.isEmpty()) null
                else number.replace(",", ".").trim()
    }

    fun round(number: Float) : String {
        return bankRound(number.toString())
    }

    fun round(numberStr: String) : String {
        val formatted = format(numberStr)
        return  if (formatted == null) ""
                else return bankRound(formatted)
    }

    private fun bankRound(number: String) : String {
        var bigDecimal = number.toBigDecimal()
        bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_EVEN)
        return bigDecimal.toString()
    }

}