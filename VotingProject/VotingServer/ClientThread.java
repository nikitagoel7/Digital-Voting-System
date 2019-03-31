import java.net.*;
import java.io.*;

public class ClientThread extends Thread {
    private Socket socket = null;
    private TallyBuffer tally1;
    private TallyBuffer tally2;
    private TallyBuffer tally3;
    private TallyBuffer tally4;
    private TallyBuffer tally5;
	public String[] already_voted=new String[250];

    public ClientThread(Socket socket, TallyBuffer tally1,TallyBuffer tally2,TallyBuffer tally3,TallyBuffer tally4,TallyBuffer tally5, String[] already_voted) {
	    super("ClientThread");
	    this.socket = socket;
	    this.tally1 = tally1;
	    this.tally2 = tally2;
	    this.tally3 = tally3;
	    this.tally4 = tally4;
	    this.tally5 = tally5;
		this.already_voted=already_voted;
    }

    public void run() {

	try { System.out.println("Voter starting");

	//1. Open a new input and output stream with the client.
		PrintWriter out = new PrintWriter(
	    	                 socket.getOutputStream(), true);
	    BufferedReader in = new BufferedReader(
		                 new InputStreamReader(socket.getInputStream()));

	// 2. Initialize a new ballot and protocol for interacting with the voter.
	    String inputLine, outputLine;
	    int input,output;
	    ClientProtocol voter = new ClientProtocol();
	    outputLine = voter.processInput(null, tally1, tally2, tally3, tally4, tally5, already_voted);
	    out.println(outputLine);

	// 3. Loop to read and process individual votes from the voter.
	    while ((inputLine = in.readLine()) != null) {
		   outputLine = voter.processInput(inputLine, tally1, tally2, tally3, tally4, tally5, already_voted);
		   out.println(outputLine);    // relay message to client
		   if (outputLine.equals("Bye")){
            break;
		   }
	 	}

	 	System.out.println("\nVoter finishing");
	    tally1.display("North-West district");
	    tally2.display("South-West district");
	    tally3.display("North-East district");
	    tally4.display("South-East district");
	    tally5.display("Central district");

        output = voter.getVoter();
        System.out.println("\nThe number of people that have voted till now are: " + output);
        System.out.println("\nThe number of people still remaining for voting are: " + (already_voted.length - output));

        tally1.displayWinner("North-West district");
        tally2.displayWinner("South-West district");
        tally3.displayWinner("North-East district");
        tally4.displayWinner("South-East district");
        tally5.displayWinner("Central district");
	// 4. Close the two streams, and finally the socket
	    out.close();
	    in.close();
	    socket.close();

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
