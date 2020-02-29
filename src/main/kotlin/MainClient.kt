fun main() {
    val client = Client()
    for (i in 1..100) {
        client.send("Hello World $i\n")
    }
    client.close()
}