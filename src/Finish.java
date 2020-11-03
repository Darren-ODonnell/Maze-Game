import java.awt.*;
import java.util.Random;

public class Finish extends GameObject {

    Random r = new Random();
    private Handler handler;
    int size;


    public Finish(int x, int y, int size, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        velX = 0;
        velY = 0;
        this.size = size;

    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, size*2, size*3);
    }

    public void tick() {
        x += velX;
        y += velY;

        if(y <= 0 || y >= Game.HEIGHT - 52) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;

        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.YELLOW, 15, 30, 0.07f, handler));

        collision();

    }

    private void collision(){
        for(int i =0; i < handler.object.size(); i++){

            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Finish){
                if(getBounds().intersects(tempObject.getBounds())){
                    //gameState == Game.STATE.Finish;
                    //collision code
                }

            }

        }

    }

    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect((int)x,(int) y, size*2, size*3);
    }



}
