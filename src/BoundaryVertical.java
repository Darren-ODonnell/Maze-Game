import java.awt.*;
import java.util.Random;

public class BoundaryVertical extends GameObject{

    Random r = new Random();
    private Handler handler;
    int size;

    public BoundaryVertical(int x, int y, int size, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        velX = 0;
        velY = 5;
        this.size = size;


    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, size, size*3);
    }

    public void tick() {
        x += velX;
        y += velY;

        if(y <= 0 || y >= Game.HEIGHT - 52) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;


//        collision();

    }

    private void collision(){
        for(int i =0; i < handler.object.size(); i++){

            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.BoundaryVertical){
                if(getBounds().intersects(tempObject.getBounds())){
                    //collision code
                }

            }

        }

    }

    public void render(Graphics g) {
        g.setColor(Color.PINK);
        g.fillRect((int)x,(int) y, size /2, size*2);
    }



}
