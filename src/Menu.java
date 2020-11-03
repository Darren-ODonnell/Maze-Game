import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;


public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;
    private HUD hud;
    public Game.STATE gameState = Game.STATE.Game;
    int lineSpace = 60;
    int ColumnSpace = 160;
    int ScoreSpace = 80;
    int optSize = 40;
    int width = 60;
    int height = 40;


    int x = Game.WIDTH/2 - 110;
    int y = 70;

    Random r;

    public Menu(Game game, Handler handler, HUD hud) {
        this.game = game;
        this.handler = handler;
        this.hud = hud;
        r = new Random();
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();


        if (game.gameState == Game.STATE.Menu) {
            //play button
            if (mouseOver(mx, my, 210, 150, 200, 64)) {
                game.gameState = Game.STATE.Game;

                game.buildMaze();

                r = new Random();

                if (gameState == Game.STATE.Game) {

                    game.setupPlayer();
                    game.setupFinish();
                    game.buildObstacles();

                }
            }

            // Options button in Menu
            if (mouseOver(mx, my, game.WIDTH / 2 - 120, 250, 200, 64))
                game.gameState = Game.STATE.Options;

            // Scoreboard button in Menu
            if (mouseOver(mx, my, game.WIDTH / 2 - 120, 350, 200, 64))
                game.gameState = Game.STATE.Scoreboard;

            // help button in Menu
            if (mouseOver(mx, my, game.WIDTH / 2 - 120, 450, 200, 64))
                game.gameState = Game.STATE.Help;

            // Quit button in Menu
            if (mouseOver(mx, my, game.WIDTH / 2 - 120, 550, 200, 64))
                System.exit(1);

        }
        else if (game.gameState == Game.STATE.Options) {
            //MazeSelection1
            if(mouseOver(mx, my, x, y + lineSpace*2, optSize, optSize))  {
                if (game.gameLast != 1) {
                    game.loadMaze(1);
                    game.gameLast = 1;
                }
            }
            //MazeSelection2
            if(mouseOver(mx, my, x, y + lineSpace*3, optSize, optSize))  {
                if (game.gameLast != 2) {
                    game.loadMaze(2);
                    game.gameLast = 2;
                }
            }
            //Mazeselection3
            if(mouseOver(mx, my, x, y + lineSpace*4, optSize, optSize))     {
                if (game.gameLast != 3) {
                    game.loadMaze(3);
                    game.gameLast = 3;
                }
            }

            //Difficulty1
            if(mouseOver(mx, my, x + ColumnSpace, y + lineSpace * 2, optSize, optSize)) game.gameLevel = 1;
            //Difficulty2
            if(mouseOver(mx, my, x + ColumnSpace, y + lineSpace * 3, optSize, optSize)) game.gameLevel = 2;
            //Difficulty3
            if(mouseOver(mx, my, x + ColumnSpace, y + lineSpace * 4, optSize, optSize)) game.gameLevel = 3;
            //Difficulty4
            if(mouseOver(mx, my, x + ColumnSpace, y + lineSpace * 5, optSize, optSize)) game.gameLevel = 4;

            //back button for Options
            if (mouseOver(mx, my, game.WIDTH / 2 - 110, 550, 200, 64))
                game.gameState = Game.STATE.Menu;

        }
        else if (game.gameState == Game.STATE.Scoreboard) {

            //back button for Scoreboard
            if (mouseOver(mx, my, game.WIDTH / 2 - 110, 550, 200, 64))
                game.gameState = Game.STATE.Menu;
        }
        else if (game.gameState == Game.STATE.Help) {

            //back button for help
            if (mouseOver(mx, my, game.WIDTH/2 -110, 350, 200, 64))
                game.gameState = Game.STATE.Menu;


        }
        else if (game.gameState == Game.STATE.End) {


            // menu button
            if (mouseOver(mx, my, game.WIDTH / 2 - 110, 250, 200, 64))
                game.gameState = Game.STATE.Menu;

            // try again button
            if (mouseOver(mx, my, game.WIDTH / 2 - 120, 350, 200, 64)) {

                game.gameState = Game.STATE.Game;
                game.buildMaze();

                r = new Random();

                if (gameState == Game.STATE.Game) {
                    game.setupPlayer();
                    game.setupFinish();
                    game.buildObstacles();
                    hud.setScore(0);
                }
            }
        }
        else if (game.gameState == Game.STATE.Finish) {


            // menu button
            if (mouseOver(mx, my, game.WIDTH / 2 - 110, 250, 200, 64))
                game.gameState = Game.STATE.Menu;

            // try again button
            if (mouseOver(mx, my, game.WIDTH / 2 - 120, 350, 200, 64)) {

                game.gameState = Game.STATE.Game;
                game.buildMaze();

                r = new Random();

                if (gameState == Game.STATE.Game) {
                    game.setupPlayer();
                    game.setupFinish();
                    game.buildObstacles();
                    hud.setScore(0);
                }
            }
        }
        else if (game.gameState == Game.STATE.Options) {
            // Menu button in Options
            if (mouseOver(mx, my, game.WIDTH/2 -120, 550, 200, 64))
                game.gameState = Game.STATE.Menu;
        }
        else if (game.gameState == Game.STATE.Scoreboard) {
                // Menu button in Scoreboard
                if (mouseOver(mx, my, game.WIDTH / 2 - 120, 550, 200, 64))
                    game.gameState = Game.STATE.Menu;
            }
        }

    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if(mx > x && mx < x + width){
            if(my > y && my < y + height){
                return true;
            }else return false;
        }else return false;
    }

    public void tick() {
    }

    public void render(Graphics g) {
        Font fnt = new Font("arial", 1, 50);
        Font fnt2 = new Font("arial", 1, 30);
        Font fnt3 = new Font("arial", 1, 20);



        if(game.gameState == Game.STATE.Menu ) {
            //
            g.drawRect(100, 100, 100, 64);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Menu", game.WIDTH/2 -90, 70);

            // Play Rect and text
            g.setFont(fnt2);
            g.drawRect(game.WIDTH/2 -120, 150, 200, 64);
            g.drawString("Play", game.WIDTH/2 -50, 190);

            // Options rect and text
            g.drawRect(game.WIDTH/2 -120, 250, 200, 64);
            g.drawString("Options", game.WIDTH/2 -73, 290);

            // Scoreboard Rect and text
            g.drawRect(game.WIDTH/2 -120, 350, 200, 64);
            g.drawString("Scoreboard", game.WIDTH/2 -103, 390);

            g.drawRect(game.WIDTH/2 -120, 450, 200, 64);
            g.drawString("Help", game.WIDTH/2 -50, 490);

            // Quit Rect and text
            g.drawRect(game.WIDTH/2 -120, 550, 200, 64);
            g.drawString("Quit", game.WIDTH/2 -50, 590);

        }
        else if(game.gameState == Game.STATE.Scoreboard) {
            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Scoreboard", Game.WIDTH/2 - 150, 70);

            List<Score> EasyScores      = game.leaderboard.topFive(game.gameLast,game.EASY);
            List<Score> MediumScores    = game.leaderboard.topFive(game.gameLast,game.MEDIUM);
            List<Score> DifficultScores = game.leaderboard.topFive(game.gameLast,game.DIFFICULT);
            List<Score> InsaneScores    = game.leaderboard.topFive(game.gameLast,game.INSANE);

            int X1 = 50;
            int Y1 = 120;

            // headings
            g.setColor(Color.WHITE);

            g.setFont(fnt2);
            g.drawString("Easy",X1,Y1);
            g.drawString("Medium",X1 + ColumnSpace,Y1);
            g.drawString("Difficult",X1 + ColumnSpace * 2,Y1);
            g.drawString("Insane",X1 + ColumnSpace * 3,Y1);

            // Leaderboard
            g.setFont(fnt3);
            showScores(g, X1 , Y1, EasyScores);
            showScores(g, X1 + ColumnSpace , Y1, MediumScores);
            showScores(g, X1 + ColumnSpace * 2, Y1, DifficultScores);
            showScores(g, X1 + ColumnSpace * 3, Y1, InsaneScores);

            g.setFont(fnt2);
            g.setColor(Color.WHITE);

            g.drawRect(game.WIDTH/2 -120, 550, 200, 64);
            g.drawString("Menu", game.WIDTH/2 -55, 590);
        }
        else if(game.gameState == Game.STATE.Help) {

            // Help text
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Help", game.WIDTH/2 -90, 70);

            g.setFont(fnt2);
            // WASD Help and Text
            g.drawString("Use WASD Keys to manoeuvre player", 80, 200);
            g.drawString("away from walls and moving enemies", 80, 250);

            // Back Rect and text
            g.drawRect(game.WIDTH/2 - 120, 350, 200, 64);
            g.drawString("Back", game.WIDTH/2 -55, 390);
        }
        else if(game.gameState == Game.STATE.End) {
            handler.clearPlayer();

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Game Over", game.WIDTH/2 - 140, 70);

            g.setFont(fnt2);
            g.drawString("You lost with a score of: " + hud.getScore(), game.WIDTH/2 - 200, 200);

            g.drawRect(game.WIDTH/2 -100 , 250, 200, 64);
            g.drawString("Menu", game.WIDTH/2 - 40, 290);

            g.drawRect(game.WIDTH/2 - 100, 350, 200, 64);
            g.drawString("Try Again", game.WIDTH/2 - 70, 390);

        }
        else if(game.gameState == Game.STATE.Finish) {
            handler.clearPlayer();

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Well Done", game.WIDTH / 2 - 140, 70);

            g.setFont(fnt2);
            g.drawString("You Won With A Score Of: " + hud.getScore(), game.WIDTH / 2 - 200, 200);

            g.drawRect(game.WIDTH / 2 - 100, 250, 200, 64);
            g.drawString("Menu", game.WIDTH / 2 - 40, 290);

            g.drawRect(game.WIDTH / 2 - 100, 350, 200, 64);
            g.drawString("Try Again", game.WIDTH / 2 - 70, 390);

            inputBoxes();
        }


            else if(game.gameState == Game.STATE.Options) {


//            g.setFont(fnt);
////            g.setColor(Color.WHITE);
////            g.drawString("Options", x, y);
            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Seats", x + 40, y);

            // Select game and level in options
            g.setFont(fnt2);
            g.drawString("Left", x, y + lineSpace  );
            g.drawString("Right", x + ColumnSpace, y + lineSpace);

            // draw plane

            // draw seats ... with seat numbers

            // fill seats already booked from

            // click seat to book - click confirm to make booking

            // return seat number


            drawOptBox(g, x, y + lineSpace*2, width, height, 1, false);
            drawOptBox(g, x, y + lineSpace*3, width, height, 3, false);
            drawOptBox(g, x, y + lineSpace*4, width, height, 5, false);
            drawOptBox(g, x, y + lineSpace*5, width, height, 7, false);
            drawOptBox(g, x, y + lineSpace*6, width, height, 9, false);

            drawOptBox(g, x + ColumnSpace , y + lineSpace * 2, width, height, 2, false);
            drawOptBox(g, x + ColumnSpace , y + lineSpace * 3, width, height, 4, false);
            drawOptBox(g, x + ColumnSpace , y + lineSpace * 4, width, height, 6, false);
            drawOptBox(g, x + ColumnSpace , y + lineSpace * 5, width, height, 8, false);
            drawOptBox(g, x + ColumnSpace , y + lineSpace * 6, width, height, 10, false);


//            switch (game.gameLast) {
//                case 1:
//                    // enable option 1
//                    // disable option 2
//                    // disable option 3
//                    drawOptBox(g, x, y + lineSpace*2, optSize, optSize, 1, true);
//                    drawOptBox(g, x, y + lineSpace*3, optSize, optSize, 2, false);
//                    drawOptBox(g, x, y + lineSpace*4, optSize, optSize, 3, false);
//
//
//                    break;
//                case 2:
//                    // disable option 1
//                    // enable option 2
//                    // disable option 3
//                    drawOptBox(g, x, y + lineSpace*2, optSize, optSize, 1, false);
//                    drawOptBox(g, x, y + lineSpace*3, optSize, optSize, 2, true);
//                    drawOptBox(g, x, y + lineSpace*4, optSize, optSize, 3, false);
//
//                    break;
//                case 3:
//                    // disable option 1
//                    // disable option 2
//                    // enable option 3
//
//                    drawOptBox(g, x, y +lineSpace*2, optSize, optSize, 1, false);
//                    drawOptBox(g, x, y + lineSpace*3, optSize, optSize, 2, false);
//                    drawOptBox(g, x, y + lineSpace*4, optSize, optSize, 3, true);
//                    break;
//
//            }
//
//            switch(game.gameLevel) {
//                case 1:
//                    // enable option 1
//                    // disable option 2
//                    // disable option 3
//                    // disable option 4
//
//                    drawOptBox(g, x + ColumnSpace, y + lineSpace * 2, optSize, optSize, 1, true);
//                    drawOptBox(g, x + ColumnSpace, y + lineSpace * 3, optSize, optSize, 2, false);
//                    drawOptBox(g, x + ColumnSpace, y + lineSpace * 4, optSize, optSize, 3, false);
//                    drawOptBox(g, x + ColumnSpace, y + lineSpace * 5, optSize, optSize, 4, false);
//
//                    break;
//                case 2:
//                    // disable option 1
//                    // enable option 2
//                    // disable option 3
//                    // disable option 4
//                    drawOptBox(g, x + ColumnSpace, y + lineSpace * 2, optSize, optSize, 1, false);
//                    drawOptBox(g, x + ColumnSpace, y + lineSpace * 3, optSize, optSize, 2, true);
//                    drawOptBox(g, x + ColumnSpace, y + lineSpace * 4, optSize, optSize, 3, false);
//                    drawOptBox(g, x + ColumnSpace, y + lineSpace * 5, optSize, optSize, 4, false);
//
//                    break;
//                case 3:
//                    // disable option 1
//                    // disable option 2
//                    // enable option 3
//                    // disable option 4
//                    drawOptBox(g, x + ColumnSpace, y + lineSpace * 2, optSize, optSize, 1, false);
//                    drawOptBox(g, x + ColumnSpace, y + lineSpace * 3, optSize, optSize, 2, false);
//                    drawOptBox(g, x + ColumnSpace, y + lineSpace * 4, optSize, optSize, 3, true);
//                    drawOptBox(g, x + ColumnSpace, y + lineSpace * 5, optSize, optSize, 4, false);
//                    break;
//                case 4:
//                    // disable option 1
//                    // disable option 2
//                    // disable option 3
//                    // enable option 4
//
//                    drawOptBox(g, x + ColumnSpace, y + lineSpace * 2, optSize, optSize, 1, false);
//                    drawOptBox(g, x + ColumnSpace, y + lineSpace * 3, optSize, optSize, 2, false);
//                    drawOptBox(g, x + ColumnSpace, y + lineSpace * 4, optSize, optSize, 3, false);
//                    drawOptBox(g, x + ColumnSpace, y + lineSpace * 5, optSize, optSize, 4, true);
//                    break;
//
//            }
           }


            if (game.gameLast == 2) {
                g.drawRect(game.WIDTH / 2 - 120, 550, 200, 64);
                g.fillRect(game.WIDTH / 2 - 120, 550, 200, 64);
            }
            else if (game.gameLast == 3) {
                g.drawRect(game.WIDTH / 2 - 120, 550, 200, 64);
                g.fillRect(game.WIDTH / 2 - 120, 550, 200, 64);
            } else {

            }


            // Menu in Options
            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawRect(game.WIDTH/2 -100, 550, 200, 64);
            g.drawString("Confirm", game.WIDTH/2 -55, 590);
        }

    public void inputBoxes() {

            String first_name;
            first_name = JOptionPane.showInputDialog("First Name");

            String second_name;
            second_name = JOptionPane.showInputDialog("Second Name Initials");

            String full_name;
            full_name = "You are " + first_name + second_name;

            JOptionPane.showMessageDialog(null, full_name);
            System.exit(0);

    }
    public void drawOptBox(Graphics g ,int x, int y, int width, int height, int option, boolean selected) {
        g.setColor(Color.white);
        g.drawRect(x, y, width, height);
        if(selected) g.fillRect(x, y, width, height);
        g.setColor(Color.red);
        if(option > 9) {
            g.drawString("" + option, x + ColumnSpace / 12, y + lineSpace / 2);
        }else
        g.drawString("" + option, x + ColumnSpace / 7, y + lineSpace / 2);


    }
    public void showScores(Graphics g, int x, int y,List<Score> scores){
        for (Score score : scores) {
            y += lineSpace;
            g.drawString(score.getName(), x,y);
            g.drawString(""+score.getScore(),x + ScoreSpace,y);
        }
    }
}
