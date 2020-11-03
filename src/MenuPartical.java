import java.awt.*;
import java.util.Random;

public class MenuPartical extends GameObject{

    Random r = new Random();
    private Handler handler;


    private Color col;

    int dir = 0;

    public MenuPartical(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;
        dir = r.nextInt(2);

        velX = (r.nextInt(7 - -7) + -7);
        velY = (r.nextInt(7 - -7) + -7);

        if(velX == 0) velX = 1;
        if(velY == 0) velY = 1;

        col = new Color( r.nextInt(255), r.nextInt(255), r.nextInt(255));

    }

    public Rectangle getBounds() {
        return new Rectangle((int)x,(int) y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        if(y <= 0 || y >= Game.HEIGHT - 52) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;

        handler.addObject(new Trail((int)x,(int) y, ID.Trail, col, 16, 16, 0.02f, handler));
        collision();
    }

    private void collision(){
        for(int i = 0; i < handler.object.size(); i++ ){

            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.BasicEnemy){
                if(getBounds().intersects(tempObject.getBounds())){
                    //collision code
                }
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(col);
        g.fillRect((int)x,(int) y, 16, 16);
    }
}
