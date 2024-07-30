package domainprimitives

data class Password private constructor(val value: String) : CharSequence by value {
    constructor(firstEntry: String, secondEntry: String) : this(firstEntry) {
        require(firstEntry == secondEntry) { "Passwords must match" }
        require(!firstEntry.matches(Regex("^\\s+.+$|^.+\\s+$"))) { "Password cannot begin or end with whitespace" }
    }
}
