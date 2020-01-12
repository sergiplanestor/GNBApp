package bemobile.splanes.com.gnbapp.commons.util

import java.math.BigDecimal

/**
 * Math utils object
 */
object MathUtils {

    /**
     * Multiply two given strings and round result using Bankers Round.
     * @param number1 string representing number.
     * @param number2 string representing number.
     * @return result.
     */
    fun multiply(number1: String, number2: String) : String {
        val num1 = format(number1)
        val num2 = format(number2)
        return  if (num1 != null && num2 != null) round(num1.toFloat() * num2.toFloat())
                else ""
    }

    /**
     * Format given string to can cast it to float.
     * @param number string to be formatted.
     * @return formatted string. If it is empty or blank will return null.
     */
    fun format(number: String) : String? {
        return  if (number.isBlank() || number.isEmpty()) null
                else number.replace(",", ".").trim()
    }

    /**
     * Rounds given float number.
     * @param number float to be rounded.
     * @return string representing rounded number.
     */
    fun round(number: Float) : String {
        return bankRound(number.toString())
    }

    /**
     * Rounds given string number.
     * @param numberStr string representing number to be rounded.
     * @return string representing rounded number.
     */
    fun round(numberStr: String) : String {
        val formatted = format(numberStr)
        return  if (formatted == null) ""
                else return bankRound(formatted)
    }

    /**
     * Method to round given string using bankers round.
     * @param number string representing number to be rounded
     * @return string representing rounded number.
     */
    private fun bankRound(number: String) : String {
        var bigDecimal = number.toBigDecimal()
        bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_EVEN)
        return bigDecimal.toString()
    }

}