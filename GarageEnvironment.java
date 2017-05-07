// Environment code for project Garage.mas2j

import java.io.*;
import java.util.*;
import java.util.logging.*;

import jason.asSyntax.*;
import jason.environment.*;
import jason.asSyntax.parser.*;

public class GarageEnvironment extends Environment {

    private Logger logger = Logger.getLogger("Garage.mas2j."+GarageEnvironment.class.getName());

    private Timer eventTimer;
    public class EventTimer extends TimerTask {

        private GarageEnvironment env;

        public EventTimer(GarageEnvironment env) {
            super();
            this.env = env;
        }

        @Override
            public void run() {
                env.randomizeEvents();
            }
    }

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

    @Override
        public void init(String[] args) {
            super.init(args);
            updatePercepts();

            eventTimer = new Timer();
            eventTimer.schedule(new EventTimer(this), 0, 500);
        }

    @Override
        public void stop() {
            deletePercepts();
            super.stop();
        }

    public void randomizeEvents() {

        Random rand = new Random();

        for(int i=0; i<mapx; ++i) {
            for(int j=0; j<mapy; ++j) {

                // Car can arrive here.
                if(map[i][j].type == Field.Type.Gate && map[i][j].car==null) {
                    if(rand.nextInt(100) < 50) {
                        int index = rand.nextInt(cars.size());
                        map[i][j].car = cars.get(index);
                        cars.remove(index);
                    } 
                }

                // Car can leave here.
                if(map[i][j].type == Field.Type.ParkingSpot && map[i][j].car!=null && map[i][j].car.leaving==false) {
                    if(rand.nextInt(100) < 50) {
                        map[i][j].car.leaving = true;
                    }
                }
            }
        }
        
        updatePercepts();
    }

    private void updatePercepts() {

         deletePercepts();

         try {
            for(int i=0; i<mapx; ++i) {
                for(int j=0; j<mapy; ++j) {

                    if(map[i][j].obstacle()) {
                        addPercept("navigator", ASSyntax.parseLiteral("obstacle("+i+","+j+")"));
                    } else {
                        addPercept("navigator", ASSyntax.parseLiteral("~obstacle("+i+","+j+")"));
                    }

                    if(map[i][j].type == Field.Type.ParkingSpot) {
                        addPercept("surveillance", ASSyntax.parseLiteral("parkingspot("+i+","+j+")"));
                    }

                    if(map[i][j].type == Field.Type.Gate) {
                        addPercept("surveillance", ASSyntax.parseLiteral("gate("+i+","+j+")"));
                        if(map[i][j].car != null) {
                            addPercept("surveillance", ASSyntax.parseLiteral("carArrived("+i+","+j+")"));
                        }
                    }

                    if(map[i][j].car != null && map[i][j].car.leaving) {
                        addPercept("surveillance", ASSyntax.parseLiteral("carLeaving("+i+","+j+")"));
                    } 

                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    } 

    private void deletePercepts() {
        clearPercepts();
        clearPercepts("navigator");
        clearPercepts("surveillance");
        clearPercepts("valet");
    }


    @Override
        public boolean executeAction(String agName, Structure action) {
            logger.info("executing: "+action+", but not implemented!");
            if (true) { // you may improve this condition
                informAgsEnvironmentChanged();
            }
            return true; // the action was executed with success 
        }



}


