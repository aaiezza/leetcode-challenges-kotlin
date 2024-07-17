import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    val formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD")

    var d = LocalDate.now()

    for(i in 1..510) {
        d = d.plusDays(1)
        println(d.format(formatter))
    }
}
