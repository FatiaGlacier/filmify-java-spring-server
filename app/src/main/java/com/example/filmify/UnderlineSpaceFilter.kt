import android.text.InputFilter
import android.text.Spanned

class UnderlineSpaceFilter : InputFilter {

    private val pattern = "___-___" // Define your desired pattern

    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence {
        val filteredStringBuilder = StringBuilder(dest)

        // Iterate through the source and replace digits at specific positions
        for (i in start until end) {
            val currentChar = source[i]

            // Check if the position allows a digit
            if (dstart + i < dest.length && Character.isDigit(currentChar) && pattern[dstart + i] == '_') {
                // Replace the underscore with the digit
                filteredStringBuilder[dstart + i] = currentChar
            }
        }

        return filteredStringBuilder.toString()
    }
}
