import javax.vecmath.Vector3d;

// this is a Asynch thread that keeps running until its interrupted by a query
public class BackGround extends Thread implements Runnable {
	static Home b; 
	static GUI g;
	int a=10;
	SatsCollection sats;
	static double[]xs,ys,zs;
	// put here the object id -> which object details to flood
	public BackGround(){
		
	}
	public BackGround(GUI g2) {
		g = g2; // use to access the gui
		  // use to access the bodies info
	}

	@Override
	public void run() {
		// create satellites here
		//System.out.println(b.objects_sats);
		sats = new SatsCollection(a);  // create n number of satellites 
		xs = new double[a];
		ys = new double[a];
		zs = new double[a];
		while(true){
 
			//System.out.println("BACKGROUND!!!!");
			get_all();
			 //from bodies which is updated by gui
			//xs = xs.clone();
			//b.ys = ys.clone();
			for(int i=0;i<a;i++) 	
				g.update(xs[i],ys[i],zs[i],i); 
			
			
			//b.xs = xs;
		 	//b.ys = ys; // in bodies
		 	//System.out.println(xs[0]+" "+ys[0]);
			// later calculations can go here
		 	
		 	try {
				Thread.sleep(150);// priority
			} catch (InterruptedException e) {}	
		 	 
		}
	}

	public void get_all() {
		for(int i=0;i<a;i++){
			Vector3d pos = sats.getSatPositionByID(i); 
			xs[i] = pos.x;
			ys[i] = pos.y;
			//zs[i] = pos.z;
			zs[i] = -0.2 ;
			//System.out.println(xs[i]+ " "+ys[i]);
		}
	}	 
}
