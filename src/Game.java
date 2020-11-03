import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1550691097823471818L;
    public static final int WIDTH = 750, HEIGHT = 750;

    private Thread thread;
    private boolean running = false;

    private Random r;
    private Handler handler;
    private HUD hud;
    private Spawn spawner = new Spawn();
    private Menu menu;
    public Leaderboard leaderboard;


    public enum STATE {
        Menu,
        Help,
        Game,
        Finish,
        Options,
        Scoreboard,
//      GameChanged,
        End

    }

    final static int EASY = 1;
    final static int MEDIUM = 2;
    final static int DIFFICULT = 3;
    final static int INSANE = 4;
    public int blockSize = 0;
    public STATE gameState = STATE.Game;
    public int gameLevel = EASY;
    public int gameLast = 1;
    public List<Integer> playerPositions;
    public List<List<Integer>> maze;

    public Game() {

        handler = new Handler();
        hud = new HUD();
        menu = new Menu(this, handler, hud);
        gameLast = getLastGame();
        leaderboard = new Leaderboard(gameLast);
        leaderboard.load();
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);

        new Window(WIDTH, HEIGHT, "Let's Build A Game!", this);

        spawner = new Spawn(handler, hud);
        menu = new Menu(this, handler, hud);

        playerPositions = new ArrayList<>();
        maze = new ArrayList<>();

        // The first row of the maze files contains the player position at start and winning position
        // this must be extracted from the maze file

        setupNewGame(gameLast);

        loadMaze(gameLast);
        buildMaze();
        r = new Random();
        if (gameState == STATE.Game){

            //if gameLevel == Game.Easy
            //setVelX for player and horizontal = 4
            //setVelY for player and vertical = 4

            setupPlayer();
            setupFinish();
            setupSmartEnemy();
            buildObstacles();
        }
    }

    public void setupNewGame(int choice) {
        loadMaze(choice);
        setupPlayer();
        setupFinish();
        buildObstacles();
    }

    public void loadMaze(int choice) {
        maze = CSV.readInts("src/Maze" + gameLast + ".csv");
        playerPositions = maze.get(0);
        blockSize = playerPositions.get(4);
        maze.remove(0);

    }
    public void setupPlayer() {
        handler.addObject(new Player(playerPositions.get(1) * this.blockSize,playerPositions.get(0)* this.blockSize,
                this.blockSize/2, ID.Player, handler, hud, this));

    }

    public void setupFinish() {
        handler.addObject(new Finish(playerPositions.get(3) * this.blockSize,playerPositions.get(2) * this.blockSize, this.blockSize/2, ID.Finish, handler));
//        handler.addObject(new Finish(playerPositions.get(1) * this.blockSize,playerPositions.get(0) * this.blockSize, this.blockSize/2, ID.Finish, handler));

    }

    public void setupSmartEnemy() {
//        handler.addObject(new SmartEnemy(0,0, this.blockSize, ID.SmartEnemy, handler));

    }

    public void buildObstacles() {

        int gap = 150;
        int nextPos = gap;

        for(int i = 0; i<4 ;i++) {
            handler.addObject(new BoundaryHorizontal(0, nextPos, this.blockSize, ID.BoundaryHorizontal, handler));
            handler.addObject(new BoundaryHorizontal(300, nextPos, this.blockSize, ID.BoundaryHorizontal, handler));
            handler.addObject(new BoundaryVertical(nextPos, 0, this.blockSize, ID.BoundaryHorizontal, handler));
            handler.addObject(new BoundaryVertical(nextPos, 300, this.blockSize, ID.BoundaryHorizontal, handler));
            nextPos += gap;
        }

    }

    public synchronized void start(){
            thread = new Thread(this);
            thread.start();
            running = true;
        }

    public synchronized void stop(){
        try {
            if(hud.HEALTH ==0){
                running = true;
                System.out.println("Your Score = " + hud.getScore());
            }
            thread.join();
            running = false;
        } catch ( Exception e){
            e.printStackTrace();
        }



    }

    public void run(){
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();

        int frames = 0;

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime)/ ns;
            lastTime = now;

            while(delta >= 1){
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                //System.out.println("Fps: " + frames);
                frames = 0;
            }
        }
        stop();

    }

    private void tick(){
        handler.tick();

       if(gameState == STATE.Game) {
            hud.tick();
            spawner.tick();


            if(HUD.HEALTH <= 0){
                HUD.HEALTH = 100;
                handler.clearEnemies();
                gameState = STATE.End;
            }
        } else if (gameState == STATE.Menu || gameState == STATE.End )
            menu.tick();


    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        if (gameState == STATE.Game) {
            hud.render(g);
        } else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Options || gameState == STATE.Scoreboard || gameState == STATE.End || gameState == STATE.Finish) {
            menu.render(g);
        }

        g.dispose();
        bs.show();

    }

    public static float clamp(float var, float min, float max){
        if(var >= max)       var = max;
        else if(var <= min)  var = min;

        return var;
    }

    public int getLastGame(){

        return CSV.readInt("src/LastGamePlayed.csv");

    }


    public static void main(String[] args) {
        new Game();

    }

    public void buildMaze() {
        for(int i = 0; i < maze.size(); i++){
            List<Integer> row = maze.get(i);
            for(int j = 0; j < row.size(); j++)
                if(row.get(j) == 1)
                    handler.addObject(new Box(j*blockSize, i*blockSize, blockSize, ID.Box, handler));


        }
    }

    public void setState(STATE state) {
        this.gameState = state;
    }

}

