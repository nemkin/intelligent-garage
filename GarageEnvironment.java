// Environment code for project Garage.mas2j

import java.io.*;
import java.util.*;
import java.util.logging.*;

import jason.asSyntax.*;
import jason.environment.*;
import jason.asSyntax.parser.*;

public class GarageEnvironment extends Environment {

    private Logger logger = Logger.getLogger("Garage.mas2j."+GarageEnvironment.class.getName());

    private String mapPath = "map.txt";
    private String carsPath = "cars.txt";

    int mapx, mapy;
    private Field[][]  map;
    private List<Car> cars;

    public GarageEnvironment() {

        super();

        try {

            BufferedReader mapFile = new BufferedReader(new FileReader(mapPath));

            String[] dim = mapFile.readLine().split(" ");
            mapx = Integer.parseInt(dim[0]);
            mapy = Integer.parseInt(dim[1]);

            map = new Field[mapx][mapy];    

            for(int i=0; i<mapx; ++i) {

                String line = mapFile.readLine();

                for(int j=0; j<mapy; ++j) {
                    map[i][j] = new Field();
                    switch(line.charAt(j)) {
                        case '-': map[i][j].type = Field.Type.Road; break;
                        case 'W': map[i][j].type = Field.Type.Wall; break;
                        case 'E': map[i][j].type = Field.Type.ParkingSpot; break;
                        case 'G': map[i][j].type = Field.Type.Gate; break;
                    }
                }
            }
            mapFile.close(); 

            BufferedReader carsFile = new BufferedReader(new FileReader(carsPath));

            cars = new ArrayList<>();

            String line;
            while((line = carsFile.readLine()) != null) {
                String[] carData = line.split(";");
                Car car = new Car(carData[0], carData[1]);
                cars.add(car);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deletePercepts() {
        clearPercepts();
        clearPercepts("navigator");
        clearPercepts("surveillance");
        clearPercepts("valet");
    }

    private void updatePercepts() {
        try {
            for(int i=0; i<mapx; ++i) {
                for(int j=0; j<mapy; ++j) {
                    String percept;
                    switch(map[i][j].type) {
                        case Road: percept="road"; break;
                        case Wall: percept="wall"; break; 
                        case ParkingSpot: percept="parkingspot"; break;
                        case Gate: percept="gate"; break;
                        default: percept="none"; break;
                    }
                    addPercept("navigator", ASSyntax.parseLiteral(""+percept+"("+i+","+j+")"));
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    } 

    @Override
        public void init(String[] args) {
            super.init(args);
            deletePercepts();
            updatePercepts();
        }

    @Override
        public boolean executeAction(String agName, Structure action) {
            logger.info("executing: "+action+", but not implemented!");
            if (true) { // you may improve this condition
                informAgsEnvironmentChanged();
            }
            return true; // the action was executed with success 
        }

    @Override
        public void stop() {
            deletePercepts();
            super.stop();
        }

}


