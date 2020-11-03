import java.awt.*;
import java.util.Random;

public class BigEnemy extends GameObject{

    Random r = new Random();
    private Handler handler;

    public BigEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        velX = 1;
        velY = 1;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x,(int) y, 48, 48);
    }

    public void tick() {
        x += velX;
        y += velY;

        if(y <= 0 || y >= Game.HEIGHT - 86) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH - 65) velX *= -1;

        handler.addObject(new Trail((int)x,(int) y, ID.Trail, Color.BLUE, 48, 48, 0.07f, handler));

        collision();

    }

    private void collision(){
        for(int i =0; i < handler.object.size(); i++){

            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.BigEnemy){
                if(getBounds().intersects(tempObject.getBounds())){
                    //collision code
                }
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect((int)x,(int) y, 48, 48);
    }



}
