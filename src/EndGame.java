import java.awt.*;
import java.awt.image.BufferStrategy;

public class EndGame extends Canvas {
    public EndGame(int score) {

        // stop running game

        // create canvaa
        //
        // create window
        new EndWindow(500, 200, "Game Over!", this);

        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();


        g.fillRect(0, 0, 500, 200);
        g.setColor(Color.BLUE);

        g.drawString("Score: " + score, 15, 64);

    }

    public void start(int score){

//        BufferStrategy bs = this.getBufferStrategy();
//        if (bs == null){
//            this.createBufferStrategy(3);
//            return;
//        }
//        Graphics g = bs.getDrawGraphics();
//
//
//        g.fillRect(0, 0, 500, 200);
//        g.setColor(Color.BLUE);
//
//        g.drawString("Score: " + score, 15, 64);
        // g.drawString("Level: " + level, 15, 80);

        // cretae graphics

        // write end game output


    }
}
