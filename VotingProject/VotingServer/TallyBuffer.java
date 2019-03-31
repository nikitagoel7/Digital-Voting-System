public class TallyBuffer {

	int[] tally = {0,0,0,0};
	boolean valueSet = false;

	public synchronized void update(int[] aBallot) {
		for (int i=0; i<tally.length; i++)
		   tally[i] = tally[i] + aBallot[i];
	}

	public synchronized void display(String district_name) {


		System.out.println("Current voting tally "+district_name+" :");
		for (int i=0; i<tally.length; i++)
		   System.out.println("  candidate " + (i+1) + ". " + tally[i]);


	}

	public synchronized void displayWinner(String district_name) {
	    int i;
	    int max = tally[0];
	    int posmax = 0;
	    for(i=1; i<tally.length; i++){
                if(tally[i] > max){
                    max = tally[i];
                    posmax = i;
                }
	    }
	    if(max == 0){
            System.out.println("\nNo voting performed till now in " + district_name);
	    }
	    else{
            System.out.println("\nWinner of " + district_name + " in voting results is: " + "Candidate " + (posmax+1) + " who has maximum i.e. " + max + " votes");
	    }
	}
}
