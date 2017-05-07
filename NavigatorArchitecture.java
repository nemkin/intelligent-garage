import jason.asSyntax.*;
import jason.architecture.*;
import java.util.*;

public class NavigatorArchitecture extends AgArch {

    int mapx;
    int mapy;
    boolean[][] map;

    @Override
        public Collection<Literal> perceive() {
            Collection<Literal> perceptCollection = super.perceive();
            parsePercepts(perceptCollection);
            printPercepts();
            return perceptCollection;
        }

    private void cleanPercepts() {
        mapx=0;
        mapy=0;
        map=null;
    }

    private void parsePercepts(Collection<Literal> perceptCollection) {

        cleanPercepts();

        if(perceptCollection == null) return; 

        //Transforming Objects back to Literals and those back to Strings.
        Object[] perceptObjs = perceptCollection.toArray();
        String[] percepts = new String[perceptObjs.length];
        for(int i=0; i<perceptObjs.length; ++i) {
            percepts[i] = ((Literal)(perceptObjs[i])).toString();
        }

        //Parsing the one dimension percept first, initializing map with dimensions.
        for(int i=0; i<percepts.length; ++i) {
            if(percepts[i].charAt(0) == 'd') {
                String[] result = percepts[i].split("dimension");
                Coord dim =  new Coord(result[1]);
                mapx = dim.x;
                mapy = dim.y;
                map = new boolean[mapx][mapy];
            }
        }

        //Parsing obstacle percepts, anything that is not a dimension percept considered an obstacle percept.
        for(int i=0; i<percepts.length; ++i) {
            if(percepts[i].charAt(0) == 'd') continue;
            String[] result = percepts[i].split("obstacle");
            Coord coord = new Coord(result[1]);
            map[coord.x][coord.y] = !(result[0].equals("~"));
        }

    }

    private void printPercepts() {
        System.out.println("Navigator currently sees this map:");
        if(map == null) System.out.println("Map is not perceived.");
        for(int i=0; i<mapx; ++i) {
            for(int j=0; j<mapy; ++j) {
                System.out.print(map[i][j] ? 1 : 0);
            }
            System.out.println("");
        }
    }
}
