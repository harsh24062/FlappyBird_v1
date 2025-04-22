import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

public class FlappyBird extends JPanel {

    int screenWidth = 360;
    int screenHeight = 640;

    // images
    Image backgroundImage;
    Image birdImage;
    Image topPipeImage;
    Image bottomPipeImage;

    //bird position
    int birdX = screenWidth/8;
    int birdY = screenHeight/2;
    int birdWidth = 40;
    int birdHeight = 30;

    // game logic
    Bird bird;

    FlappyBird(){
        setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
        setBackground(Color.BLUE);

        //get All images 
        backgroundImage = new ImageIcon(getClass().getClassLoader().getResource("resources/images/flappybirdbg.png")).getImage();
        birdImage = new ImageIcon(getClass().getClassLoader().getResource("resources/images/flappybird.png")).getImage();
        topPipeImage = new ImageIcon(getClass().getClassLoader().getResource("resources/images/toppipe.png")).getImage();
        bottomPipeImage = new ImageIcon(getClass().getClassLoader().getResource("resources/images/bottompipe.png")).getImage();

        bird = new Bird(birdX, birdY, birdWidth, birdHeight, birdImage);
    }

  
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Adding background image
        g.drawImage(backgroundImage, 0, 0, screenWidth, screenHeight, null);

        //Adding bird image
        g.drawImage(bird.birdImage, bird.birdX, bird.birdY, bird.birdWidth, bird.birdHeight, null);
    }
  

}
