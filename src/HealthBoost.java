import java.awt.*;
import java.util.Random;

public class HealthBoost extends GameObject{

    Random r = new Random();
    private Handler handler;

    public HealthBoost(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        velX = 10;
        velY = 10;

    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        if(y <= 0 || y >= Game.HEIGHT - 52) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;

        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.YELLOW, 16, 16, 0.07f, handler));

        collision();

    }

    private void collision(){
        for(int i =0; i < handler.object.size(); i++){

            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.HealthBoost){
                if(getBounds().intersects(tempObject.getBounds())){
                    //collision code
                }

            }

        }

    }

    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect((int)x,(int) y, 16, 16);
    }



}
