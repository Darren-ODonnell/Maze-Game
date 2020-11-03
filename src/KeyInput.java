import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    private boolean[] keyDown = new boolean[4];

    public KeyInput(Handler handler){
        this.handler = handler;

        keyDown[0]=false; //w
        keyDown[1]=false; //a
        keyDown[2]=false; //s
        keyDown[3]=false; //d

    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        int speed = 5;

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);


            if (tempObject.getId() == ID.Player) {

//                if (key == KeyEvent.VK_W)  keyDown[0] = true;
//                if (key == KeyEvent.VK_A)  keyDown[1] = true;
//                if (key == KeyEvent.VK_S)  keyDown[2] = true;
//                if (key == KeyEvent.VK_D)  keyDown[3] = true;
//                update(keyDown, tempObject);

                if (key == KeyEvent.VK_W)  tempObject.setVelY(-speed);// keyDown[0]=true; }
                if (key == KeyEvent.VK_A)  tempObject.setVelX(-speed);// keyDown[1]=true; }
                if (key == KeyEvent.VK_S)  tempObject.setVelY(+speed);// keyDown[2]=true; }
                if (key == KeyEvent.VK_D)  tempObject.setVelX(+speed);// keyDown[3]=true; }

            }

        }
        if(key == KeyEvent.VK_ESCAPE) System.exit(1);
    }

    public void update(boolean[] keyDown, GameObject p) {

        final int UP    = 0;
        final int LEFT  = 1;
        final int DOWN  = 2;
        final int RIGHT = 3;

        if(keyDown[UP])
            p.setVelY(p.getVelY() - 5);
        else
            p.setVelY(0);

        if(keyDown[LEFT])
            p.setVelX(p.getVelX() - 5);
        else
            p.setVelX(0);

        if(keyDown[DOWN])
            p.setVelY(p.getVelY() + 5);
        else
            p.setVelY(0);

        if(keyDown[RIGHT])
            p.setVelX(p.getVelX() + 5);
        else
            p.setVelX(0);

    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        int speed = 0;

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {

                if (key == KeyEvent.VK_W) tempObject.setVelY(speed);//keyDown[0]=false;
                if (key == KeyEvent.VK_A) tempObject.setVelX(speed);//keyDown[1]=false;
                if (key == KeyEvent.VK_S) tempObject.setVelY(speed);//keyDown[2]=false;
                if (key == KeyEvent.VK_D) tempObject.setVelX(speed);//keyDown[3]=false;

            }

        }
    }
}

