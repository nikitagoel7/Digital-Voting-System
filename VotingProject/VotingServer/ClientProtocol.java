//This code was adapted by Allen Tucker in February, 2005
//out of programs downloaded from "The Java Tutorial"
//http://java.sun.com/docs/books/tutorial/networking/sockets/clientServer.html
public class ClientProtocol {
	private static final int MENU=0;
	private static final int AUTHENTICATE=1;
    private static final int WAITING = 2;
    private static final int SENTBALLOT = 3;
    private static final int DONE = 4;

    private int state = MENU;
    int[] ballot_nwdel = {0,0,0,0};
	int[] ballot_swdel ={0,0,0,0};
	int[] ballot_nedel ={0,0,0,0};
	int[] ballot_sedel ={0,0,0,0};
	int[] ballot_cdel ={0,0,0,0};
	int pos=0, flag;
	public static int number_of_users_voted = 0;

    boolean check_list(String response, String[] already_voted){
		int i=0;
		for (i=0;i<already_voted.length;i++){
			if (response.equals(already_voted[i])){
				return true;
			}
		}
		return false;
	}


    public String processInput(String response, TallyBuffer tally1, TallyBuffer tally2, TallyBuffer tally3, TallyBuffer tally4, TallyBuffer tally5, String[] already_voted) {
        String theOutput = "";
		if (state == MENU){
			theOutput="CHOOSE THE DISTRICT YOU BELONG TO 1.NWDEL 2.SWDEL 3. NEDEL 4. SEDEL 5. CDEL";
			state=AUTHENTICATE;
		}
		else if (state == AUTHENTICATE){
			if (response.equals("NWDEL")){
				theOutput="Enter the client id: ";
			    state=WAITING;
				flag=1;
			}
			else if(response.equals("SWDEL")){
				theOutput="Enter the client id: ";
			    state=WAITING;
				flag=2;
			}
			else if(response.equals("NEDEL")){
				theOutput="Enter the client id: ";
			    state=WAITING;
				flag=3;
			}
			else if(response.equals("SEDEL")){
				theOutput="Enter the client id: ";
			    state=WAITING;
				flag=4;
			}
			else if(response.equals("CDEL")){
				theOutput="Enter the client id: ";
			    state=WAITING;
				flag=5;
			}
			else theOutput="Invalid district code";
		}
		else if(state==WAITING){
			if(flag==1){
				if (response.substring(0,5).equals("NWDEL") && !check_list(response, already_voted)){
					already_voted[pos++]=response;
					number_of_users_voted++;
					theOutput = "Vote for only one candidate: 1. MODI 2. GANDHI 3. KEJRIWAL 4. ADVANI" ;
					state = SENTBALLOT;
				}
				else{
					theOutput="Invalid Client";
				}
			}
			else if(flag==2){
				if (response.substring(0,5).equals("SWDEL") && !check_list(response, already_voted)){
					already_voted[pos++]=response;
					number_of_users_voted++;
					theOutput = "Vote for only one candidate: 1. MANMOHAN SINGH 2. SHEILA DIXIT 3. SMRITI IRANI 4. ASHUTOSH ROY" ;
					state = SENTBALLOT;
				}
				else{
					theOutput="Invalid Client";
				}
			}
			else if(flag==3){
				if (response.substring(0,5).equals("NEDEL") && !check_list(response, already_voted)){
					already_voted[pos++]=response;
					number_of_users_voted++;
					theOutput = "Vote for only one candidate: 1. AMIT SHAH 2. MANISH SISODIYA 3. DR.HARSHWARDHAN 4. INDIRA GANDHI" ;
					state = SENTBALLOT;
				}
				else{
					theOutput="Invalid Client";
				}
			}
			else if(flag==4){
				if (response.substring(0,5).equals("SEDEL") && !check_list(response, already_voted)){
					already_voted[pos++]=response;
					number_of_users_voted++;
					theOutput = "Vote for only one candidate: 1. MAMTA BANERJEE 2. AKHILESH YADAV 3. MAYAWATI 4. MALAYUM SINGH YADAV" ;
					state = SENTBALLOT;
				}
				else{
					theOutput="Invalid Client";
				}
			}
			else if(flag == 5){
				if (response.substring(0,4).equals("CDEL") && !check_list(response, already_voted)){
					already_voted[pos++]=response;
					number_of_users_voted++;
					theOutput = "Vote for only one candidate: 1. BAL THAKRE 2. RAJ THAKRE 3. JAYALALITHA 4. NAVJOT SINGH SIDHU" ;
					state = SENTBALLOT;
				}
				else{
					theOutput="Invalid Client";
				}
			}

		}
		else if (state == SENTBALLOT){
            if (flag == 1){
				if (response.length() > 0 && response.compareTo("1") >= 0 &&
						response.compareTo("4") <= 0) {
					   int n = (new Integer(response)).intValue();
					   if (ballot_nwdel[n-1] == 0) {
						   ballot_nwdel[n-1] = 1;
					   }
					   state=DONE;
				}
				else theOutput = "Invalid number: try again.";
			}
			else if (flag == 2){
				if (response.length() > 0 && response.compareTo("1") >= 0 &&
						response.compareTo("4") <= 0) {
					   int n = (new Integer(response)).intValue();
					   if (ballot_swdel[n-1] == 0) {
						   ballot_swdel[n-1] = 1;
					   }
					   state=DONE;
				}
				else theOutput = "Invalid number: try again.";
			}
			else if (flag == 3){
				if (response.length() > 0 && response.compareTo("1") >= 0 &&
						response.compareTo("4") <= 0) {
					   int n = (new Integer(response)).intValue();
					   if (ballot_nedel[n-1] == 0) {
						   ballot_nedel[n-1] = 1;
					   }
					   state=DONE;
				}
				else theOutput = "Invalid number: try again.";
			}
			else if (flag == 4){
				if (response.length() > 0 && response.compareTo("1") >= 0 &&
						response.compareTo("4") <= 0) {
					   int n = (new Integer(response)).intValue();
					   if (ballot_sedel[n-1] == 0) {
						   ballot_sedel[n-1] = 1;
					   }
					   state=DONE;
				}
				else theOutput = "Invalid number: try again.";
			}
			else if (flag == 5){
				if (response.length() > 0 && response.compareTo("1") >= 0 &&
						response.compareTo("4") <= 0) {
					   int n = (new Integer(response)).intValue();
					   if (ballot_cdel[n-1] == 0) {
						   ballot_cdel[n-1] = 1;
					   }
					   state=DONE;
				}
				else theOutput = "Invalid number: try again.";
			}
		}
		if (state==DONE){
          if (flag == 1){
			  tally1.update(ballot_nwdel);
			  theOutput = "Bye";
		  }
		  else if (flag == 2){
			  tally2.update(ballot_swdel);
			  theOutput = "Bye";
          }
		  else if (flag == 3){
			  tally3.update(ballot_nedel);
			  theOutput = "Bye";

		  }
		  else if (flag == 4){
			  tally4.update(ballot_sedel);
			  theOutput = "Bye";

		  }
		  else if (flag == 5){
			  tally5.update(ballot_cdel);
			  theOutput = "Bye";

		  }
		}
	return theOutput;
	   //return theOutput;
  }

  public int getVoter(){
      return number_of_users_voted;
  }

}
