import java.awt.*;
import java.util.Random;

public class BossBullet extends GameObject{

    Random r = new Random();
    private Handler handler;


    public BossBullet(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        velX = (r.nextInt(5 - -5) + -5) ;
        velY = 5;

    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        //if(y <= 0 || y >= Game.HEIGHT - 52) velY *= -1;
        //if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;

        if(y >= Game.HEIGHT) handler.removeObject(this);

        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.red, 16, 16, 0.05f, handler));

        collision();

    }

    private void collision(){
        for(int i =0; i < handler.object.size(); i++){

            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.BasicEnemy){
                if(getBounds().intersects(tempObject.getBounds())){
                    //collision code
                }

            }

        }

    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x,(int) y, 16, 16);
    }



}
