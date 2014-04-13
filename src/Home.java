import java.util.ArrayList;
import java.util.Random; 

// Abdul and Manu
// imports libraries
public class Home {
	static GUI g; 
	static BackGround bg;
	static String[] lister;
	static int objects_sats = 10,current_Obj=0;;
	static int locker = 0; // for perfect asynch***  1 - means GUI updates 2 -- means process requested**  0- means free to access
	static ArrayList<String> satellites_IDs ; 
	final static int ID_LENGTH = 4; // declare max and min and length of the id
	final static int Min = 10;
	final static int Max = 15; 
	
	/*****************MAIN********************/
	public static void main(String[] args) {
		setupGUI(); // gets the gui
		// create objects
		 bg = new BackGround(g); // here lock out the variables
		//JOptionPane.showMessageDialog(null, "hey"); // for alerts
		startProcess(); // start the updating thread
	}
	/*******************FUNCTIONS*****************/
	private static void setupGUI() {
		//get the gui
		g = new GUI();  
		//g.setResizable(false);
		g.setVisible(true);
		//g.setLocationRelativeTo(null);	
	}
	private static void startProcess() { // starts updating stuff real-time
		sat_id_set();
		sat_creater();
		bg.start();  // get started
		 
	}
	
	public static void sat_creater(){
		//satellites = new TheObject[objects_sats]; // create only  necessary satellites 
		lister = new String[objects_sats];
		int j=0;
		for(String id_ar: satellites_IDs){
			lister[j++] = id_ar; 
		}
		g.set_lister(lister);
		//System.out.println("Planetary and Satellite Objects Has Been Created : ");
	
	}	
	public static void sat_id_set(){
		String ID_gen = "";
		//create the  earth and other planets and satellite
		//System.out.println("Planetary and Satellite Objects being created : ");
		// To make Other Objects make properties
		satellites_IDs = new ArrayList();
		objects_sats = (Min + (int)(Math.random() * ((Max - Min) + 1))); 
		for(int i=0;i<10;i++){ // creates random number of satellites between 40- 50
			ID_gen = get_randomID();
			//if(checkID(ID_gen)); // for clash of ids
			satellites_IDs.add("S"+(i+1)+"-"+ID_gen);
		}
	}
	private static String get_randomID() {// create the random id 
		String cc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789",sum="";
		Random rand = new Random(); 
		char[] charArray = cc.toCharArray();
		for(int i=0;i<ID_LENGTH;i++){
			sum = sum + charArray[rand.nextInt(cc.length()-1)+1];
		}
		return sum;
	}
	
	

}
