import javax.imageio.ImageIO;
import javax.media.j3d.Alpha;
import java.util.Timer;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.vecmath.Color3f;
import javax.vecmath.Color4f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
/*
 * Created by JFormDesigner on Sat Apr 12 15:53:47 BST 2014
 */
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

/**
 *  
 */
public class GUI extends JFrame {
    int choice=0;
    final static int ID_LENGTH = 4; // declare max and min and length of the id
	final static int Min = 10;
	final static int Max = 15; 
	
	public GUI() {
		initComponents();
		canvas_draw();
	}
	
	Timer timer;

	private class ScalingUpTask extends TimerTask {
	    public void run() {
	    	scale += 0.1;
	    	Scale();
	        // your code here
	    }
	}
	
	private class ScalingDownTask extends TimerTask {
	    public void run() {
	    	scale -= 0.1;
	    	Scale();
	        // your code here
	    }
	}

    private void initComponents() {
        this2 = new JPanel();
        label1 = new JLabel();
        cb_shuttle = new JComboBox();
        b_locate = new JButton();
        label2 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        bg =  new BackGround();
        l_positionX = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        l_positionY = new JLabel();
        l_positionZ = new JLabel();
        scrollPane1 = new JScrollPane();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label10 = new JLabel();
        label11 = new JLabel();
        scrollPane2 = new JScrollPane();
        ta_NearBySuttles = new JTextArea();
        b_zoomIn = new JButton();
        b_zoomOut = new JButton();
        frame = new JPanel(); // put the thing here
        Container contentPane = getContentPane(); {
            //---- label1 ----
            label1.setText("Shuttle Object Advisor");
            label1.setForeground(Color.decode("#806080"));
            label1.setFont(new Font("PortagoITC TT", Font.BOLD, 44));

cb_shuttle.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    //code       //http://stackoverflow.com/questions/14647439/how-do-i-get-the-previous-or-last-item
                	String bbb = ""+e.getItem();
                	char[] arr = bbb.toCharArray();
                	choice = arr[1]-49;
                	//System.out.println(choice);	
                	label5.setText(""+(choice+1));
                	
                }
            });

            //---- b_locate ----
            b_locate.setText("Locate");
            b_locate.setForeground(new Color(5, 71, 5));
            b_locate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	//JOptionPane.showMessageDialog(null, "hey");
                	l_positionX.setText((bg.xs[choice]+""));      	
                	l_positionY.setText((bg.ys[choice]+""));      	
                	l_positionZ.setText("-0.5"+get_randomID());
                	label11.setText("10");
                    // do nearby calcs
                	double [] temp = new double[10];
                	double[] temp2 = new double[10];
                	for (int i=0;i<10;i++){
                		 
                			temp [i] = bg.xs[i];
                			temp2 [i] = bg.ys[i];
                		 
                	}  
                	double diffx = 0.0;
                	String abc="";
                	for (int i=0;i<10;i++){
                		if(!(choice==i)){
                			 diffx =  ((temp[i]-bg.xs[choice])*(temp[i]-bg.xs[choice])+(temp2[i]-bg.ys[choice])*(temp2[i]-bg.ys[choice]));
                			diffx = Math.sqrt(diffx);
                			if(((int)(diffx*10000))<=3000)
                				abc = (abc+"Shuttle : "+(i+1)+":\t"+((int)(diffx*10000))+"km\n");  
                		}
                	}  
                	ta_NearBySuttles.setText(abc);
                }
            });

            //---- label2 ----
            label2.setText("Shuttle in Space");
            label2.setForeground(Color.blue);

            //---- label6 ----
            label6.setText("Position: ");
            label6.setForeground(Color.blue);

            //---- label7 ----
            label7.setText("X: ");

            //---- l_positionX ----
            l_positionX.setText("l_positionX");

            //---- label8 ----
            label8.setText("Y: ");

            //---- label9 ----
            label9.setText("Z: ");

            //---- l_positionY ----
            l_positionY.setText("l_positionY");

            //---- l_positionZ ----
            l_positionZ.setText("l_positionZ");

            //---- label3 ----
            label3.setText("Near By Shuttles:");
            label3.setForeground(Color.blue);

            //---- label4 ----
            label4.setText("Selected:");

            //---- label5 ----
            label5.setText("none");

            //---- label10 ----
            label10.setText("Total Shuttles:");

            //---- label11 ----
            label11.setText("10");

            //======== scrollPane2 ========
            {
                scrollPane2.setViewportView(ta_NearBySuttles);
            }

            //---- b_zoomIn ----
            b_zoomIn.setText("+");
            b_zoomIn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                     
                }
            });

            //---- b_zoomIn ----
            b_zoomIn.setText("+");
            b_zoomIn.addMouseListener(new MouseInputAdapter() {
            	TimerTask task = new ScalingUpTask();
            	
                @Override
                public void mousePressed(MouseEvent e) {
                    //mousePressed(e);
                	timer = new Timer();
                	TimerTask task = new ScalingUpTask();
                	timer.scheduleAtFixedRate(task, 0, 100);
                }
                
                @Override
                public void mouseReleased(MouseEvent e) {
                    timer.cancel();
                }
            });
            
            b_zoomOut.setText("-");
            
            b_zoomOut.addMouseListener(new MouseInputAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    //mousePressed(e);
                	timer = new Timer();
                	TimerTask task = new ScalingDownTask();
                	timer.scheduleAtFixedRate(task, 0, 100);
                	
                }
                
                @Override
                public void mouseReleased(MouseEvent e) {
                    timer.cancel();
                }
            }); 
            
            GroupLayout this2Layout = new GroupLayout(this2);
            this2.setLayout(this2Layout);
            this2Layout.setHorizontalGroup(
                this2Layout.createParallelGroup()
                    .addGroup(this2Layout.createSequentialGroup()
                        .addGroup(this2Layout.createParallelGroup()
                            .addGroup(this2Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(this2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addGroup(this2Layout.createSequentialGroup()
                                        .addComponent(label2)
                                        .addGap(30, 30, 30)
                                        .addComponent(cb_shuttle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(b_locate))
                                    .addComponent((frame), GroupLayout.PREFERRED_SIZE, 442, GroupLayout.PREFERRED_SIZE))
                                .addGroup(this2Layout.createParallelGroup()
                                    .addGroup(this2Layout.createSequentialGroup()
                                        .addGap(38, 38, 38)
                                        .addGroup(this2Layout.createParallelGroup()
                                            .addGroup(this2Layout.createSequentialGroup()
                                                .addGroup(this2Layout.createParallelGroup()
                                                    .addGroup(this2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(label7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(label8))
                                                    .addComponent(label9, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
                                                .addGap(28, 28, 28)
                                                .addGroup(this2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                    .addComponent(l_positionY)
                                                    .addComponent(l_positionX)
                                                    .addComponent(l_positionZ)))
                                            .addGroup(this2Layout.createSequentialGroup()
                                                .addComponent(label4)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(label5))
                                            .addComponent(label6)))
                                    .addGroup(this2Layout.createSequentialGroup()
                                        .addGap(100, 100, 100)
                                        .addGroup(this2Layout.createParallelGroup()
                                            .addComponent(label3)
                                            .addGroup(this2Layout.createSequentialGroup()
                                                .addComponent(label10)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(label11))))
                                    .addGroup(GroupLayout.Alignment.TRAILING, this2Layout.createSequentialGroup()
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(scrollPane2)))
                                .addGap(6, 6, 6))
                            .addGroup(this2Layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addComponent(label1)))
                        .addGap(3, 3, 3))
                    .addGroup(this2Layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(b_zoomIn)
                        .addGap(18, 18, 18)
                        .addComponent(b_zoomOut)
                        .addContainerGap())
            );
            this2Layout.setVerticalGroup(
                this2Layout.createParallelGroup()
                    .addGroup(this2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(label1)
                        .addGap(18, 18, 18)
                        .addGroup(this2Layout.createParallelGroup()
                            .addGroup(this2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label2)
                                .addComponent(cb_shuttle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addComponent(b_locate, GroupLayout.Alignment.TRAILING))
                        .addGroup(this2Layout.createParallelGroup()
                            .addGroup(this2Layout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(frame, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE))
                            .addGroup(this2Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(label6)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(this2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label7)
                                    .addComponent(l_positionX))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(this2Layout.createParallelGroup()
                                    .addGroup(this2Layout.createSequentialGroup()
                                        .addComponent(label8)
                                        .addGap(14, 14, 14))
                                    .addGroup(GroupLayout.Alignment.TRAILING, this2Layout.createSequentialGroup()
                                        .addComponent(l_positionY)
                                        .addGap(18, 18, 18)))
                                .addGroup(this2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label9, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(l_positionZ))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(this2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label4)
                                    .addComponent(label5))
                                .addGap(46, 46, 46)
                                .addComponent(label3)
                                .addGap(18, 18, 18)
                                .addGroup(this2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label10)
                                    .addComponent(label11))
                                .addGap(18, 18, 18)
                                .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(this2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(b_zoomOut)
                            .addComponent(b_zoomIn))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            setVisible(true);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createParallelGroup()
                    .addGroup(contentPaneLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(this2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 663, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createParallelGroup()
                    .addGroup(contentPaneLayout.createSequentialGroup()
                        .addGap(0, 5, Short.MAX_VALUE)
                        .addComponent(this2, GroupLayout.PREFERRED_SIZE, 585, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 6, Short.MAX_VALUE)))
                .addGap(0, 596, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
    /******************SET FUNCTIONS*******************************/
    public void set_lister(String[] lists){ // set the list
    	cb_shuttle.setModel(new DefaultComboBoxModel(lists));
    }
    public void set_it(){
    	JOptionPane.showMessageDialog(null, "hey");
    }
    private static String get_randomID() {// create the random id 
		String cc = "0123456789",sum="";
		Random rand = new Random(); 
		char[] charArray = cc.toCharArray();
		for(int i=0;i<15;i++){
			sum = sum + charArray[rand.nextInt(cc.length()-1)+1];
		}
		return sum;
	}
    /************************************************/
    private JPanel this2;
    private JLabel label1;
    private JComboBox cb_shuttle;
    private JButton b_locate;
    private JLabel label2;
    private JLabel label6;
    private JLabel label7;
    private JLabel l_positionX;
    private JLabel label8;
    private JLabel label9;
    private JLabel l_positionY;
    private JLabel l_positionZ;
    private JScrollPane scrollPane1;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label10;
    private JLabel label11;
    private JScrollPane scrollPane2;
    private JTextArea ta_NearBySuttles;
    private JButton b_zoomIn;
    private JButton b_zoomOut;
    static JPanel frame;
    static BackGround bg;
/******************************************************************/
/************************DRAW GRAPHICS*****************************/
	 ////////////////// global variables
	static double[] xs,ys; 
    static int sat_num; 
    
    static Canvas3D canvas;
	static SimpleUniverse univ;
	static ArrayList<TransformGroup> satellites;
	
	static float scale = 1.0f;
	
	//Earth scaling
	static TransformGroup  planetTransform;
	static float planetDiam = 0.075f;
	static float satDiam = 0.008f;
	static float spaceStationDiam = 0.01f;
	
	static float testIter = (float)-0.5; //TEST VARIABLE HERE - REMOVE LATER
	
	//Element scaling
			public static void Scale()
			{
				Transform3D oldTransform = new Transform3D();
		    	planetTransform.getTransform(oldTransform);
		    	oldTransform.setScale(planetDiam * scale);
		    	planetTransform.setTransform(oldTransform);
		    	
				for (int i = 0; i < satellites.size(); i++)
				{
					TransformGroup sat = satellites.get(i);
					
					oldTransform = new Transform3D();
					
					sat.getTransform(oldTransform);
				    oldTransform.setScale(satDiam * scale);
					sat.setTransform(oldTransform);
					
				}
			}
			
	public static BranchGroup createSceneGraph()
	{
		BoundingSphere bounds = new BoundingSphere(new Point3d(), 1000.0);
		BranchGroup branch = new BranchGroup();
		
		//Transformations for Earth
		planetTransform = new TransformGroup();
		
		//Earth scale
		Transform3D planetParam = new Transform3D();
		
		Vector3d scale = new Vector3d(planetDiam, planetDiam, planetDiam);
	    planetParam.setScale(scale);
		planetTransform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		planetTransform.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		planetTransform.setTransform(planetParam);
		
		
		// Make a shape
		//params
		/*ColorCube demo = new ColorCube( 0.4 );
		trans.addChild( demo );*/
		
		TransformGroup planetCore = new TransformGroup();
		planetCore.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		planetCore.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		
		satellites = new ArrayList<TransformGroup>();
		
		int n = 10;
		for (int i = 0; i < n; i++)
		{
			satellites.add(new TransformGroup());
			satellites.get(i).setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			satellites.get(i).setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		}
		
		try
		{
			//Loading background image
			File imgFile = new File("StarrySkyStock.jpg");
			BufferedImage img = ImageIO.read(imgFile);
			ImageComponent2D imgCom = new ImageComponent2D(ImageComponent2D.FORMAT_RGB, img);
			Background bg = new Background(imgCom);
			bg.setApplicationBounds(new BoundingSphere());
			branch.addChild(bg);
			
			//Loading the planet
			Scene s = null;
		   	ObjectFile f = new ObjectFile ();
		    f.setFlags (ObjectFile.RESIZE | ObjectFile.TRIANGULATE | ObjectFile.STRIPIFY);
		    Shape3D earth;
		    
		    //System.out.println("Working Directory = " + System.getProperty("user.dir"));
			s = f.load("earth.obj");
			
		    BranchGroup branchGroup = s.getSceneGroup();
		    earth = (Shape3D) branchGroup.getChild(0);
		    
		    
			
			//Texturing the planet
			TextureLoader loader = new TextureLoader("earth_day.jpg","RGB", new Container());
			Texture texture = loader.getTexture();
			
			texture.setBoundaryModeS(Texture.WRAP);
			texture.setBoundaryModeT(Texture.WRAP);
			texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 0.0f, 0.0f));
			TextureAttributes texAttr = new TextureAttributes();
			texAttr.setTextureMode(TextureAttributes.MODULATE);
			texAttr.setTextureMode(TextureAttributes.ALLOW_BLEND_COLOR_READ); //Test values
			texAttr.setTextureMode(TextureAttributes.ALLOW_BLEND_COLOR_WRITE); //Test values
			texAttr.setTextureMode(TextureAttributes.COMBINE_SRC_ALPHA); //Test values
			Appearance ap = new Appearance();
			ap.setTexture(texture);
			ap.setTextureAttributes(texAttr);
			
			//This can be used for lighting
			
			/*Material material = new Material(); 
			material.setDiffuseColor(0.1f, 0.1f, 0.1f);   // red
			material.setSpecularColor(0.2f, 0.2f, 0.2f);  // reduce default values
			ap.setMaterial(material);*/
			
			earth.setAppearance(ap);
		   
			//Adding the planet data to planet core
			planetCore.addChild(branchGroup);
			
			//Loading satellites
			for (int i = 0; i < satellites.size(); i++)
			{
				//Loading satellite object
				s = f.load("UHFSatcom.obj");
				
				//Iterate through satellite transform list
				TransformGroup sat = satellites.get(i);
				
				//Satellite transform parameters
				Transform3D satParam = new Transform3D();
				Vector3d satScale = new Vector3d(satDiam, satDiam, satDiam);
				Vector3d satTranslate = new Vector3d(-0.5,-0.5,-0.5);
				satParam.setScale(satScale);
			    satParam.setTranslation(satTranslate);
			    sat.setTransform(satParam);
				sat.addChild(s.getSceneGroup());
				branch.addChild(sat);
			}
		}
		catch (java.io.FileNotFoundException ex)
		{
			System.out.println("Uh-oh! Object file is not found.");
      	}
		catch(java.io.IOException ex)
		{
			System.out.println("Uh-oh! Background image file is not found.");
		}
		
		// Set up the ambient light
	    Color3f ambientColor = new Color3f(1.0f, 1.0f, 1.0f);
	    AmbientLight ambientLightNode = new AmbientLight(ambientColor);
	    ambientLightNode.setInfluencingBounds(bounds);
	    branch.addChild(ambientLightNode);

	    // Set up the directional lights
	    Color3f light1Color = new Color3f(1.0f, 1.0f, 0.9f);
	    Vector3f light1Direction = new Vector3f(1.0f, 1.0f, 1.0f);
	    Color3f light2Color = new Color3f(1.0f, 1.0f, 1.0f);
	    Vector3f light2Direction = new Vector3f(-1.0f, -1.0f, -1.0f);

	    DirectionalLight light1 = new DirectionalLight(light1Color,
	        light1Direction);
	    light1.setInfluencingBounds(bounds);
	    branch.addChild(light1);

	    DirectionalLight light2 = new DirectionalLight(light2Color,
	        light2Direction);
	    light2.setInfluencingBounds(bounds);
	  branch.addChild(light2);
      	
		/* Make a behavior to spin the shape
		params: loopCount: -1 for indefinite count of loops
				increasingAlphaDuration: time it takes to increase alpha from 0 to 1
		*/
		Alpha spinAlpha = new Alpha( -1, 30000 );
		bounds.transform(planetParam);
		Transform3D rotationAxis = new Transform3D();
		rotationAxis.rotZ(0);
		RotationInterpolator spinner = new RotationInterpolator(spinAlpha, planetCore, rotationAxis, 0.0f, (float)(2.0 * Math.PI));
		spinner.setSchedulingBounds(bounds);
		planetCore.addChild(spinner);
		
		//planet transformation should sit on top of the core planet data
		planetTransform.addChild(planetCore);
		
		//Finally, adding the planet to the branch
		branch.addChild(planetTransform);
		return branch;
	}

	public static void canvas_draw(){
		//Frame frame = new Frame( );
		frame.setSize( 440, 440 );
		frame.setLayout( new BorderLayout( ) );
	 
		
		canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
		canvas.setSize(400,400);
		frame.add("Center", canvas );
		univ = new SimpleUniverse(canvas);
		univ.getViewingPlatform().setNominalViewingTransform();
		BranchGroup scene = createSceneGraph( );
		scene.compile();
		univ.addBranchGraph( scene );
		 
		//frame.setVisible(true);
	}
	public static void update(double x,double y,double z,int satNumber)
	{ 
			TransformGroup satellite = satellites.get(satNumber);
			Transform3D oldTransform = new Transform3D();
			
			satellite.getTransform(oldTransform);
			Vector3d pos = new Vector3d(x * scale, y * scale, -0.5);
		    oldTransform.setTranslation(pos);
			satellite.setTransform(oldTransform);
		  
	}
	
	
	
	 
}
