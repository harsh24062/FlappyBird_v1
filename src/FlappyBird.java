import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.plaf.DimensionUIResource;

public class FlappyBird extends JPanel implements ActionListener {

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
    int birdVelocityY = -6;  // its minus as it goes towards zero at upper part 
    int gravityY = 2;  // bird will go down as gravity pull 

    // game loop
    Timer gameLoop;

    FlappyBird(){
        setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
        setBackground(Color.BLUE);

        //get All images 
        backgroundImage = new ImageIcon(getClass().getClassLoader().getResource("resources/images/flappybirdbg.png")).getImage();
        birdImage = new ImageIcon(getClass().getClassLoader().getResource("resources/images/flappybird.png")).getImage();
        topPipeImage = new ImageIcon(getClass().getClassLoader().getResource("resources/images/toppipe.png")).getImage();
        bottomPipeImage = new ImageIcon(getClass().getClassLoader().getResource("resources/images/bottompipe.png")).getImage();

        bird = new Bird(birdX, birdY, birdWidth, birdHeight, birdImage);

        //game timer
        gameLoop = new Timer(1000/60, this); // 60 frame per second
        //start the timer
        gameLoop.start();
    }

  
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Adding background image
        g.drawImage(backgroundImage, 0, 0, screenWidth, screenHeight, null);

        //Adding bird image
        g.drawImage(bird.birdImage, bird.birdX, bird.birdY, bird.birdWidth, bird.birdHeight, null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        moveBird();
        repaint(); // it calls paintComponent(Graphics g) to draw new image 
    }


    // move the bird
    public void moveBird(){
        bird.birdY+= birdVelocityY;
        birdVelocityY+= gravityY; // adding gravity

        if(bird.birdY<0){
          bird.birdY = Math.max(bird.birdY, 0); // bird must not get out from screen
        }else if(bird.birdY>screenHeight){
        bird.birdY = Math.min(bird.birdY, screenHeight);
        }

    }
  

}
