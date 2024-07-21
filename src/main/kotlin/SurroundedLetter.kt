class SurroundedLetter {
    fun isSurroundedLetter(input: String): Boolean {
        var surrounded = false
        input.firstOrNull {
            if (it.isLetter() && !surrounded) {
                surrounded = true
                false
            } else if (it.isLetter() && surrounded) {
                surrounded = false
                true // violation
            } else false
        }
        return surrounded
    }
}