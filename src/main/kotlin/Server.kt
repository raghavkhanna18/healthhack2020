import java.io.IOException
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.nio.ByteBuffer
import java.nio.channels.SelectionKey
import java.nio.channels.Selector
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel


class Server(
    private var port: Int = 8089,
    private var address: String = "localhost",
    private val inetSocketAddress: InetSocketAddress = InetSocketAddress(address, port)
) {
    var selector: Selector = Selector.open()
    var socket: ServerSocketChannel = ServerSocketChannel.open()
    var serverSocket: ServerSocket

    init {
        serverSocket = socket.socket()
        serverSocket.bind(inetSocketAddress)
        socket.configureBlocking(false)
        val ops = socket.validOps()
        socket.register(selector, ops, null)
    }

    @Throws(IOException::class)
    fun handleAccept(
        mySocket: ServerSocketChannel,
        key: SelectionKey
    ) {
        println("Connection Accepted...")
        val client = mySocket.accept()
        client.configureBlocking(false)
        client.register(selector, SelectionKey.OP_READ)
    }

    @Throws(IOException::class)
    fun handleRead(key: SelectionKey) {
        val client = key.channel() as SocketChannel
        val buffer: ByteBuffer = ByteBuffer.allocate(1024)
        client.read(buffer)
        val data: String = String(buffer.array()).trim { it <= ' ' }
        if (data.isNotEmpty()) {
            println("Received message: $data")
            if (data.equals("exit", ignoreCase = true)) {
                client.close()
                println("Connection closed...")
            }
        }
    }
}


