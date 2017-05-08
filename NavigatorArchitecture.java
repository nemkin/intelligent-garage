import jason.asSyntax.*;
import jason.architecture.*;
import java.util.*;
import java.lang.Math;

public class NavigatorArchitecture extends AgArch {

    int mapx;
    int mapy;
    boolean[][] map;

    @Override
        public Collection<Literal> perceive() {
            Collection<Literal> perceptCollection = super.perceive();
            parsePercepts(perceptCollection);
            //printPercepts();
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

    private void astar(int startx, int starty, int endx, int endy) {

        //Distance array for Dijkstra's algorithm
        int[][] g = new int[mapx][mapy];
        for(int i=0; i<mapx; ++i) {
            for(int j=0; j<mapy; ++j) {
                g[i][j] = mapx*mapy;
            }
        }
        g[startx][starty] = 0; 

        //Added heuristics to array g for A*
        int[][] f = new int[mapx][mapy];
        for(int i=0; i<mapx; ++i) {
            for(int j=0; j<mapy; ++j) {
                f[i][j] = g[i][j] + manhattan(i,j,endx,endy);
            }
        }

        Coord[][] cameFrom = new Coord[mapx][mapy];

        //Nodes that have been expanded from and closed in Dijsktra's algorithm 
        boolean[][] closed = new boolean[mapx][mapy];
        for(int i=0; i<mapx; ++i) {
            for(int j=0; j<mapy; ++j) {
                closed[i][j] = false;
            }
        }

        Coord curr = new Coord(startx, starty);

        while(curr.x!=endx || curr.y!=endy) {
            closed[curr.x][curr.y] = true; 

            Coord left = new Coord(curr.x, curr.y-1); 
            if(steppable(left)) {
                if(g[curr.x][curr.y] + 1 < g[left.x][left.y]) {
                    g[left.x][left.y] = g[curr.x][curr.y] + 1;
                    f[left.x][left.y] = g[left.x][left.y] + manhattan(left.x, left.y, endx, endy);
                }
            }

            Coord right = new Coord(curr.x, curr.y+1);
            if(steppable(right)) {
                if(g[curr.x][curr.y] + 1 < g[right.x][right.y]) {
                    g[right.x][right.y] = g[curr.x][curr.y] + 1;
                    f[right.x][right.y] = g[right.x][right.y] + manhattan(right.x, right.y, endx, endy);
                }
            } 

            Coord up = new Coord(curr.x-1, curr.y);
            if(steppable(up)) {
                if(g[curr.x][curr.y] + 1 < g[up.x][up.y]) {
                    g[up.x][up.y] = g[curr.x][curr.y] + 1;
                    f[up.x][up.y] = g[up.x][up.y] + manhattan(up.x, up.y, endx, endy);
                }
            }

            Coord down = new Coord(curr.x+1, curr.y);
            if(steppable(down)) {
                if(g[curr.x][curr.y] + 1 < g[down.x][down.y]) {
                    g[down.x][down.y] = g[curr.x][curr.y] + 1;
                    f[down.x][down.y] = g[down.x][down.y] + manhattan(down.x, down.y, endx, endy);
                }
            }

           Coord from = curr;
           
           int minf = -1;
           for(int i=0; i<mapx; ++i) {
                for(int j=0; j<mapy; ++j) {
                   if(map[i][j] && !closed[i][j]) {
                        if(minf == -1 || f[i][j] < minf) {
                            curr = new Coord(i,j); 
                        }
                   } 
                }
           } 
           
           if(minf == -1) break;
           
           cameFrom[curr.x][curr.y] = from;
 
        }
    }

    private int manhattan(int fromx, int fromy, int tox, int toy) {
        return Math.abs(tox-fromx + toy-fromy);
    }

    private boolean steppable(Coord c) {
        return (0<=c.x && c.x<mapx && 0<=c.y && c.y<mapy && map[c.x][c.y]);
    }
}
