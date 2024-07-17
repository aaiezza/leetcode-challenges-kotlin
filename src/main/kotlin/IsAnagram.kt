import java.util.*

fun main() {
    println(IsAnagram().isAnagram("rail safety", "fairy tales"))
    println(IsAnagram().isAnagram("interview", "dott share"))
}
class IsAnagram {
    fun isAnagram(word1: String, word2: String): Boolean {
        return if (word1.length == word2.length) {
            getCharFrequency(word1) == getCharFrequency(word2)
        } else false
    }

    private fun getCharFrequency(onlyLettersWords: String) =
        onlyLettersWords
            .lowercase(Locale.getDefault())
            .filter { it.isLetterOrDigit() }
            .groupingBy { it }
            .eachCount()
}