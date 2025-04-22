import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.plaf.DimensionUIResource;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {

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
    int birdWidth = 35;
    int birdHeight = 25;

    // Adding pipes
    int pipeX = screenWidth; //starting location of pipe
    int pipeY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512; 

    int pipeVelocityX = -4;   // it will be used to move pipes left but simulation will look like bird is moving to right

    List<Pipe> pipes;    // store pipe details

    // game logic
    Bird bird;
    int birdVelocityY = -30;  // its minus as it goes towards zero at upper part 
    int gravityY = 2;  // bird will go down as gravity pull 

    // game loop
    Timer gameLoop;

    // pipe Adding Timer
    Timer pipeTimer;
    
    // gameOver flag
    boolean gameOver = false;   // if its true then gameover, bird touch pipe or bird fall down to  ground
    
    // Total Score
    double score = 0;

    FlappyBird(){
        setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
        
        setFocusable(true);
        addKeyListener(this);

        //get All images 
        backgroundImage = new ImageIcon(getClass().getClassLoader().getResource("resources/images/flappybirdbg.png")).getImage();
        birdImage = new ImageIcon(getClass().getClassLoader().getResource("resources/images/flappybird.png")).getImage();
        topPipeImage = new ImageIcon(getClass().getClassLoader().getResource("resources/images/toppipe.png")).getImage();
        bottomPipeImage = new ImageIcon(getClass().getClassLoader().getResource("resources/images/bottompipe.png")).getImage();

        bird = new Bird(birdX, birdY, birdWidth, birdHeight, birdImage);

        pipes = new ArrayList<>();

        //placePipes timer

        pipeTimer = new Timer(1700, new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e){
            placePipes();
           } 
        });
        pipeTimer.start();

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

        // Adding pipe from list
        for(int i=0;i<pipes.size();i++){
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.pipeImage, pipe.pipeX, pipe.pipeY, pipe.pipeWidth, pipe.pipeHeight, null);
        }

        // Adding score
        if(gameOver){
            g.setColor(Color.MAGENTA);
            g.setFont(new Font("Arial", Font.BOLD, 25));
            g.drawString("GameOver! Your Score: "+String.valueOf((int)score),20, 320);
        }else{
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 32));
            g.drawString(String.valueOf((int)score),10, 35);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        moveBird();
        movePipes();
        repaint(); // it calls paintComponent(Graphics g) to draw new image 
        if(gameOver){
            pipeTimer.stop();
            gameLoop.stop();
        }
    }


    // move the bird
    public void moveBird(){
        bird.birdY+= birdVelocityY;
        birdVelocityY+= gravityY; // adding gravity

        if(bird.birdY<0 || bird.birdY>screenHeight){
         gameOver=true;
        }

    }

    // move the pipes
    public void movePipes(){

        for(int i=0;i<pipes.size();i++){
            
            Pipe pipe = pipes.get(i);
            pipe.pipeX+=pipeVelocityX;

            if(!pipe.isPassed && bird.birdX > pipe.pipeX + pipe.pipeWidth){
                pipe.isPassed = true;
                score+=0.5;
            }

            if(birdCollision(bird,pipe)){
                gameOver=true;
            }
        }

    }


    @Override
    public void keyPressed(KeyEvent e) {
       if(e.getKeyCode()==KeyEvent.VK_SPACE){
        birdVelocityY=-9;
       }
    }

    @Override
    public void keyTyped(KeyEvent e) {}  // not using this

    @Override
    public void keyReleased(KeyEvent e) {} // not using this


    // Add pipes in the screen
    public void placePipes(){

        int openingSpace = screenHeight/4; // min space between pipe space through which bird can pass
      
      // creating randomY of top pipe 
      int topPipeRandomY = (int)(pipeY - pipeHeight/5 - Math.random()*(pipeHeight/2)); 
      Pipe topPipe = new Pipe(pipeX, topPipeRandomY, pipeWidth, pipeHeight, topPipeImage, false);
      pipes.add(topPipe);

      int bottomPipeY = pipeHeight + openingSpace + topPipeRandomY;
      Pipe bottomPipe = new Pipe(pipeX, bottomPipeY, pipeWidth, pipeHeight, bottomPipeImage, false);
      pipes.add(bottomPipe);
    }
  

    public boolean birdCollision(Bird bird, Pipe pipe){    // bird collide with pipe
       return bird.birdX < pipe.pipeX + pipe.pipeWidth &&
              bird.birdX + bird.birdWidth > pipe.pipeX &&
              bird.birdY < pipe.pipeY + pipe.pipeHeight &&
              bird.birdY + bird.birdHeight > pipe.pipeY;
              
    }     

}
