import java.io.IOException
import java.nio.channels.SelectionKey

/*fun main() {
    val server = Server()
    try {
        while (true) {
            server.selector.select()
            val selectedKeys: MutableSet<SelectionKey> =
                server.selector.selectedKeys()
            val i = selectedKeys.iterator()
            while (i.hasNext()) {
                val key = i.next()
                if (key.isAcceptable) {
                    server.handleAccept(server.socket, key)
                } else if (key.isReadable) {
                    server.handleRead(key)
                }
                i.remove()
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }*/


fun main(args: Array<String>) {
    val state: State = State()
    while (state.end_status()) {
            state.turn()
            state.print_map()
    }
}





