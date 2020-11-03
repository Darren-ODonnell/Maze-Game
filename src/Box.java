import java.awt.*;
import java.util.Random;

public class Box extends GameObject{

    Random r = new Random();
    private Handler handler;
    int size = 0;

    public Box(int x, int y, int size, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        this.size = size;

        velX = 0;
        velY = 0;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, size, size);
    }

    public void tick() {
//        x += velX;
//        y += velY;

//        if(y <= 0 || y >= Game.HEIGHT - 52) velY *= -1;
//        if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;

//        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.red, 16, 16, 0.07f, handler));

//        collision();

    }

    private void collision(){
//        for(int i =0; i < handler.object.size(); i++){
//
//            GameObject tempObject = handler.object.get(i);
//            //boolean inRange = inRange();
//            if(tempObject.getId() == ID.Box){
//
//                if(getBounds().intersects(tempObject.getBounds())){
//                    //collision code
//                }
//
//            }
//
//        }

    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x,(int) y, size, size);
    }


}
