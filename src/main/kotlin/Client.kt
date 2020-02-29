import java.net.Inet4Address
import java.net.Socket

class Client(val address: String = "127.0.0.1", val port: Int = 8089, val socket:Socket = Socket(address, port)) {
    fun send(message : String) {
        socket.outputStream.write(message.toByteArray())
    }
    fun close(){
        socket.close()
    }
}