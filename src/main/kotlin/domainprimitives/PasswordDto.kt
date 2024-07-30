package domainprimitives

interface PasswordDto {
    val password: String

    val confirmPassword: String
}

fun PasswordDto.toPassword() = Password(password, confirmPassword)
