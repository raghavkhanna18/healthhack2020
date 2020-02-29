import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.BufferedReader
import java.nio.file.Files
import java.nio.file.Paths


fun main() {
    val client = Client()
    val Path = "healthHack.csv"
    // read the file
    val reader: BufferedReader = Files.newBufferedReader(Paths.get(Path))
    // parse the file into csv values
    val csvParser = CSVParser(reader, CSVFormat.DEFAULT)
    for (csvRecord in csvParser) {

        // Accessing Values by Column Index
        val id = csvRecord.get(0)
        val news = csvRecord.get(1)
        val fake = csvRecord.get(2)
        val noInf = csvRecord.get(3)
        val noEco = csvRecord.get(4)
        val yesInf = csvRecord.get(5)
        val yesEco = csvRecord.get(6)
        val longterm = false
        val actionMessage = ""
        // print the value to console
        System.out.println("Record No - " + csvRecord.getRecordNumber())
        var newsList = News()
        val article = Article(
            id = id.toInt(),
            headline = news,
            isFake = fake.toBoolean(),
            noInf = noInf.toDouble(),
            noEco = noEco.toDouble(),
            yesEco = yesEco.toDouble(),
            yesInf = yesInf.toDouble(),
            actionMessage = actionMessage,
            longTerm = longterm
        )
        newsList.addArticle(article)
        client.send("---------------\n")
        client.send("$article")
        client.send("---------------\n")
    }

    client.close()
}