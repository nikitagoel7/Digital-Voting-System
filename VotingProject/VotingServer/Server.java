// This code was adapted by Allen Tucker in February, 2005
// out of programs downloaded from "The Java Tutorial" 
// http://java.sun.com/docs/books/tutorial/networking/sockets/clientServer.html
import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws IOException {
// 1. Initialize a shared variable to tally the votes.       
    	    TallyBuffer tally1 = new TallyBuffer();
    	    TallyBuffer tally2 = new TallyBuffer();
    	    TallyBuffer tally3 = new TallyBuffer();
    	    TallyBuffer tally4 = new TallyBuffer();
    	    TallyBuffer tally5 = new TallyBuffer();
        int port = 9600;
        ServerSocket serverSocket = null;
        ClientThread ct;
		String[] already_voted=new String[250];
		String number_of_users=args[0];
		int number_of_clients=Integer.valueOf(number_of_users);
		
		
// 2. Open a new Socket for communicating with clients.
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server listening on port " + port);
        } catch (IOException e) {
            System.err.println("Port: " + port + " unavailable.");
            System.exit(-1);
        }
// 3. Listen for connection requests from clients and start new Threads and sends the list of all the already voted clients.
        try {
			while (number_of_clients>0) {
            ct = new ClientThread(serverSocket.accept(), tally1, tally2, tally3, tally4, tally5, already_voted);
	        number_of_clients--;
		    ct.start();
			}
			
        }
		catch(Exception e) { e.printStackTrace(); }
    }
}
