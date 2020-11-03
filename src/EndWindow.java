import javax.swing.*;
import java.awt.*;


public class EndWindow extends Canvas {

    private static final long serialVersionUID = -240840600533728354L;

    public EndWindow(int width, int height, String title, EndGame endGame){
        JFrame frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(endGame);
        frame.setVisible(true);
       // endGame.start(score);

    }
}
