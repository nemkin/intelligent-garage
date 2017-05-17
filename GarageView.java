//import jason.environment.grid.GridWorldModel;

import java.awt.GridLayout;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;



public class GarageView {

	  public static void main(String[] args) throws InterruptedException {
		//GarageEnvironment garageEnv = new GarageEnvironment();
		
	   
        JFrame frame = new JFrame("Intelligent Garage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set the size of the frame
        frame.setSize(450, 550);

        
		// set the rows and cols of the grid, as well the distances between them
        GridLayout grid = new GridLayout(9, 11, 0, 0);
        // what layout we want to use for our frame
        frame.setLayout(grid);
        
        //Mapos
        /*for(int i=0;i<garageEnv.mapx;i++){
        	for(int j=0;j<garageEnv.mapy;j++){
        		JLabel label = new JLabel(garageEnv.map[i][j]);
        		label.setHorizontalAlignment(JLabel.CENTER);
        		frame.add(label);
        	}
        }*/
        
        for(int i=0;i<9*11;i++){
        	JLabel label = new JLabel("1");
        	label.setHorizontalAlignment(JLabel.CENTER);
        	frame.add(label);
        }
        //System.out.println(frame.getComponentAt(5, 5));
        
		frame.setVisible(true);
		//TimeUnit.SECONDS.sleep(3);
		frame.removeAll();
		//frame.revalidate();
		//frame.repaint();
		
		frame.setVisible(false);
		
		for(int i=0;i<9*11;i++){
        	JLabel label = new JLabel("2");
        	label.setHorizontalAlignment(JLabel.CENTER);
        	
        	//label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        	frame.add(label);
        }
		frame.setVisible(true);
    }
}