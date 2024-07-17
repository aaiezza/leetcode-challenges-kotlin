class CaesarCipher {
    fun encodeCaesarCipher(input: String, shift: Int): String {
        val letters = ('a'..'z').map { it }.joinToString("")
        val window = input.length
        val index = letters.indexOf(input[0]) + shift
        return letters.substring(index, index+window);
    }
}