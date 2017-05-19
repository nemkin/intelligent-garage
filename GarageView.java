import jason.environment.grid.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

    
public class GarageView extends GridWorldView {

    GarageModel model;
   
    public GarageView(GarageModel model) {
        super(model, "Domestic Robot", 700);
        this.model = model;
        defaultFont = new Font("Arial", Font.BOLD, 16); // change default font
        setVisible(true);
        repaint();
    }

    //Built in
    //public static final int CLEAN = 0;
    //public static final int AGENT = 2;
    //public static final int OBSTACLE = 4;
    public static final int PARKINGSPOT = 8;
    public static final int CAR = 16;
    public static final int GATE = 32;

    @Override
    public void draw(Graphics g, int x, int y, int object) {
        switch (object) {

            case GarageModel.PARKINGSPOT: 
                g.setColor(Color.black);
                drawString(g, x, y, defaultFont, "P");  
                break;

            case GarageModel.CAR:
                g.setColor(Color.red);
                drawString(g, x, y, defaultFont, "CAR");  
                break;
            
            case GarageModel.GATE:
                g.setColor(Color.red);
                drawString(g, x, y, defaultFont, "G");  
                break;
            
            default:
            
                break;
        }
    }

    @Override
    public void drawAgent(Graphics g, int x, int y, Color c, int id) {
            g.setColor(Color.yellow);
            super.drawString(g, x, y, defaultFont, "Valet");
    }
}
