import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.BufferedReader
import java.nio.file.Files
import java.nio.file.Paths

fun main() {
    val state: State = State()


    val path = "healthHack.csv"
    val reader: BufferedReader = Files.newBufferedReader(Paths.get(path))
    val csvParser = CSVParser(reader, CSVFormat.DEFAULT)
    val newsList = News()

    for (csvRecord in csvParser) {

        // Accessing Values by Column Index
        val id = csvRecord.get(0)
        val news = csvRecord.get(1)
        val actionMessage = csvRecord.get(2)
        val fake = csvRecord.get(3)
        val noInf = csvRecord.get(4)
        val noEco = csvRecord.get(5)
        val yesInf = csvRecord.get(6)
        val yesEco = csvRecord.get(7)
        val transferFactor = csvRecord.get(8)
        val article = Article(
            id = id.toInt(),
            headline = news,
            isFake = fake.toBoolean(),
            noInf = noInf.toDouble(),
            noEco = noEco.toInt(),
            yesEco = yesEco.toInt(),
            yesInf = yesInf.toDouble(),
            actionMessage = actionMessage,
            transferFactor = transferFactor.toDouble()
        )
        newsList.addArticle(article)

    }
    while (state.end_status() && !newsList.isEmpty()) {
        val article: Article = newsList.getAndRemoveRandomArticle()
        println("You have: $${state.money}")
        println(article.headline)
        println(article.actionMessage)
        println("This will cost \$${article.yesEco * 1000000}.")
        var input = readLine()
        var validInput = false
        while (!validInput) {
            when (input) {
                "Y", "Yes", "y", "yes" -> {
                    validInput = true
                    state.change_money(article.yesEco * 1000000)
                    state.change_global_growth(article.yesInf)
                }
                "N", "No", "n", "no" -> {
                    validInput = true
                    state.change_money(article.noEco * 1000000)
                    state.change_global_growth(article.noInf)
                }
                else -> {
                    println("Invalid input. Please try again. Yes or No or Y or N")
                    input = readLine()
                }
            }
        }
        state.turn()
        state.print_map()
    }

    while (state.end_status()) {
            state.turn()
            state.print_map()
    }
}





