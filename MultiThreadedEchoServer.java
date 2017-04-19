import java.net.*;
import java.io.*;
public class MultiThreadedEchoServer {
private static int clientId=0; // clientId to identify clients
public static void main(String[] args) throws IOException {
ServerSocket serverSocket= null;
boolean listening= true;
int port= 9999;
System.out.println("EchoServer started");
if (args.length !=1) 
	{ // check usage
	  System.err.println("usage: java EchoServer <port>");
  	  System.exit(1);
	}	 
else
	port= Integer.parseInt(args[0]);

// Create a socket from the Server Socket
try 
{
serverSocket= new ServerSocket(port);
} 
catch (IOException e) 
{
System.err.println("Could not listen on port: "+port);
System.exit(1);
}
while (listening)
 {
	// listen to server socket
Socket clientSocket = serverSocket.accept();
	// start thread when client connects
new MultiServerThread(clientSocket, clientId++).start();
}
serverSocket.close();
} // main
} // MultiThreadedEchoServer
class MultiServerThread extends Thread {
private Socket socket = null;
public MultiServerThread(Socket socket,int clientId) {
super("Client " + clientId);
this.socket = socket;
}
public void run() {
System.out.println("Handle new connection");
try {
DataInputStream is;
PrintStream os;
is = new DataInputStream(socket.getInputStream());
os = new PrintStream(socket.getOutputStream());
String socketInput;
while ((socketInput = is.readLine()) != null) 
{
os.println(socketInput);
System.out.println(getName() + ": " + socketInput);
} // while
os.close();
is.close();
} 
catch (IOException e) {e.printStackTrace();
 }
System.out.println("close connection");
} // run
} // MultiServerThread


