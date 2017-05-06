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

    private Field[][]  map;
    private List<Car> cars;

    public GarageEnvironment() {

        super();

        try {

            BufferedReader mapFile = new BufferedReader(new FileReader(mapPath));

            String[] dim = mapFile.readLine().split(" ");
            int x = Integer.parseInt(dim[0]);
            int y = Integer.parseInt(dim[1]);

            map = new Field[x][y];    

            for(int i=0; i<x; ++i) {

                String line = mapFile.readLine();

                for(int j=0; j<y; ++j) {
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

    @Override
        public void init(String[] args) {
            super.init(args);
            try {
                addPercept(Literal.parseLiteral("percept(demo)"));
                addPercept("ag1", Literal.parseLiteral("p(a)"));
                addPercept("navigator", ASSyntax.parseLiteral("n(a)"));
                addPercept("surveillance", ASSyntax.parseLiteral("s(a)"));
                addPercept("valet", ASSyntax.parseLiteral("v(a)"));

            } catch (ParseException e) {
                e.printStackTrace();
            }
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
            //Clear all percepts
            clearPercepts();
            clearPercepts("navigator");
            clearPercepts("surveillance");
            clearPercepts("valet");

            super.stop();

        }

}


