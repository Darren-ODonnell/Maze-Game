
import java.awt.*;
import java.util.Random;

public class Player extends GameObject {

    Random r = new Random();
    Handler handler;
    HUD hud;
    Game game;
    int size;

    public Player(float x, float y, int size, ID id,  Handler handler, HUD hud, Game game) {
        super(x, y, id);
        this.handler = handler;
        this.hud = hud;
        this.size = size;
        this.game = game;


    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, size, size);
    }

    public void tick() {
        x += velX ;
        y += velY ;

        x = Game.clamp(x, 0, Game.WIDTH - 48);
        y = Game.clamp(y, 0, Game.HEIGHT - 70);

        handler.addObject(new Trail((int) x, (int) y, ID.Trail, Color.white, 10, 10, 0.09f, handler));

        collision();

    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.BasicEnemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.HEALTH -= 2;
                }
            }
            if (tempObject.getId() == ID.FastEnemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.HEALTH -= 1;
                }
            }
            if (tempObject.getId() == ID.BigEnemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.HEALTH -= 3;
                }
            }
            if (tempObject.getId() == ID.SmartEnemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.HEALTH -= 100;
                }
            }
            if (tempObject.getId() == ID.EnemyBoss) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.HEALTH -= 10;
                }
            }
            if (tempObject.getId() == ID.HealthBoost) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.HEALTH += 5;
                }
            }
            if (tempObject.getId() == ID.BoundaryVertical) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.HEALTH -= 100;
                }
            }
            if (tempObject.getId() == ID.BoundaryHorizontal) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.HEALTH -= 100;
                }
            }
            if (tempObject.getId() == ID.Box) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.HEALTH -= 50;
                    velX -= 0;
                    velY = 0;
                }
            }
            if (tempObject.getId() == ID.Finish) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    game.gameState = Game.STATE.Finish;
                }
            }

        }
    }


    public void render (Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.white);
        g.fillRect((int) x, (int) y, size, size);

    }
}
