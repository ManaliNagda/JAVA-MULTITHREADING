import java.io.*;
import java.net.*;
public class EchoClient 
{
public static void main(String[] args) throws IOException 
     {
Socket echoSocket = null;
PrintWriter out = null;
BufferedReader in = null;
String host = new String();
int port = 9999;
       if (args.length != 2) 
            { // usage check
	System.err.println("usage: java EchoClient <server host> <port>");
	System.exit(1);
             } 
 else 
            {
	host = args[0];
               try {
	   port = Integer.parseInt(args[1]);
	} 
               catch (Exception e)
	 {
		System.err.println("<port> has to be a number");
		System.exit(2);
	}
               }
try {
		// open socket
       echoSocket = new Socket(host, port);
	
	
//Creates a new PrintWriter from an existing OutputStream.
       out = new PrintWriter(echoSocket.getOutputStream(), true);
		
// creating input stream (using BufferedReader)
        in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        }
catch (Exception e)
       {
	System.out.println("Exception: "+ e);
	System.exit(1);
        } 
BufferedReader stdIn = new BufferedReader( new  InputStreamReader(System.in));
String userInput;
System.out.println("EchoClient started");
while ((userInput = stdIn.readLine()) != null) 
{ // reading from stdin
	out.println(userInput); // writing to socket
	System.out.println(in.readLine()); // reading from socket
 // writing to stdout
}
// closing resources
out.close();
in.close();
stdIn.close();
echoSocket.close();
} // main
}


