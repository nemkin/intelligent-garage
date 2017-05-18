import java.io.*;
import java.util.*;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.Location;

public class GarageModel extends GridWorldModel {

    public static final String mapPath = "map.txt";
    public static final String carsPath = "cars.txt";
    
    public static final int VALET = 0;

    //Built in
    //public static final int CLEAN = 0;
    //public static final int AGENT = 2;
    //public static final int OBSTACLE = 4;
    public static final int PARKINGSPOT = 8;
    public static final int CAR = 16;
    public static final int GATE = 32;

    public List<Car> cars;
    
    public GarageModel(int mapx, int mapy) {
        
        super(mapx, mapy, 1);    
       
        try {

            BufferedReader mapFile = new BufferedReader(new FileReader(mapPath));
            mapFile.readLine(); //Dimensions
        
            for(int i=0; i<width; ++i) {

                String line = mapFile.readLine();

                for(int j=0; j<height; ++j) {
                    
                    switch(line.charAt(j)) {
                        case 'W': add(OBSTACLE, i,j); break;
                        case 'E': add(PARKINGSPOT, i,j); break;
                        case 'G': add(GATE, i,j); break;
                        default: break;
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

            carsFile.close();

 
loop:       for(int i=0; i<width; ++i) {
                for(int j=0; j<height; ++j) {
                    if(hasObject(GATE,i,j)) {
                        setAgPos(VALET, i, j);  
                        break loop;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean moveAgentUp(int agent) {
        return moveAgent(agent, -1, 0);
    }
    public boolean moveAgentDown(int agent) {
        return moveAgent(agent, +1, 0);
    }
    public boolean moveAgentLeft(int agent) {
        return moveAgent(agent, 0, -1);
    }
    public boolean moveAgentRight(int agent) {
        return moveAgent(agent, 0, +1);
    }
      
    private boolean moveAgent(int agent, int xdif, int ydif) {
        if(agent!=0) return false;

        Location agLoc = getAgPos(agent);
        Location newAgLoc = new Location(agLoc.x+xdif, agLoc.y+ydif);
        
        if(!inGrid(newAgLoc)) return false;

        if(!(isFree(newAgLoc) || (hasObject(PARKINGSPOT, newAgLoc) && !hasObject(CAR, newAgLoc)))) return false;

        setAgPos(agent, newAgLoc);

        if(view!=null) {
            //Repaint?
        }

        return true;
    }

    
    public List<Car> incomingCars() {
        List<Car> ret = new ArrayList<>();
        for(Car car : cars) {
            if(!car.leaving && hasObject(GATE, car.location)) {
                ret.add(car);
            }
        }
        return ret; 
    }


    public List<Car> leavingCars() {
        List<Car> ret = new ArrayList<>();
        for(Car car : cars) {
            if(car.leaving && hasObject(PARKINGSPOT, car.location)) {
                ret.add(car);
            }
        }
        return ret;
    }

    public Car getCarAt(int x, int y) {
        return getCarAt(new Location(x,y));
    }
    
    public Car getCarAt(Location location) {
        for(Car car : cars) {
            if(car.location != null && car.location.equals(location)) {
               return car; 
            }
        }
        return null;
    }

    public boolean generateCar(int x, int y) {
        for(Car car : cars) {
            if(car.location == null) {
                car.location = new Location(x,y);
                return true;
            }
        }
        return false;
    }
}
