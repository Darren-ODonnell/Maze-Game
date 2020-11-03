import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Spawn {


    private Handler handler = new Handler();
    private HUD hud = new HUD();
    private Random r = new Random();
    private int enemy;
    int width = 30;


    //private float

    private int scoreKeep = 0;

    public Spawn() {

    }

    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }

    public void tick() {
        scoreKeep++;

        enemy = r.nextInt(3); // any integer value from 0 - 3

        if (scoreKeep >= 200) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);


            //30 length
            //15 box size

            List<List<Integer>> maze = new ArrayList<>();
            maze = CSV.readInts("src/Maze1.csv");


            if (hud.getLevel() == 2) {
//                handler.addObject(new BoundaryHorizontal(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BoundaryHorizontal, handler));

//                for(int i = 0; i < maze.size(); i++){
//                    List<Integer> row = maze.get(i);
//                    for(int j = 0; j < row.size(); j++){
//                        if(row.get(j) == 1)
//                            handler.addObject(new Box(i*15, j*15, ID.Box, handler));
//
//                    }
//                }

            }
        }
    }
}
