import java.awt.*;
import java.util.Random;

public class SmartEnemy extends GameObject{

    private Handler handler;
    Random r;
    private GameObject player;
    int size;

    public SmartEnemy(int x, int y, int size, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        this.size = size;

        r = new Random();

        for(int i = 0; i< handler.object.size(); i ++){
            if(handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
        }

        velX = 2;
        velY = 2;

    }

    public Rectangle getBounds() {
        return new Rectangle((int)x,(int) y, size/2, size/2);
    }

    public void tick() {
        x += velX;
        y += velY;

        float diffX = x - player.getX() - 16;
        float diffY = y - player.getY() - 16;
        float distance = (float) Math.sqrt((x - player.getX())*(x - player.getX()) + (y - player.getY())*(y - player.getY()) );

        velX = ((-1/distance) * diffX);
        velY = ((-1/distance) * diffY);

//        if(y <= 0 || y >= Game.HEIGHT - 52) velY *= -1;
//        if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;

        handler.addObject(new Trail((int)x,(int) y, ID.Trail, Color.green, size/2, size/2, 0.07f, handler));

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
        g.setColor(Color.green);
        g.fillRect((int)x,(int) y, size/2, size/2);
    }



}
