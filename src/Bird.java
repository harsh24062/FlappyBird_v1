import java.awt.Image;

public class Bird {

    int birdX;      //bird x position
    int birdY;      //bird y position
    int birdWidth;  //bird Width on screen
    int birdHeight; //bird Height on screen
    Image birdImage;

    public Bird(int birdX, int birdY, int birdWidth, int birdHeight, Image birdImage){
     this.birdX = birdX;
     this.birdY = birdY;
     this.birdWidth = birdWidth;
     this.birdHeight = birdHeight;
     this.birdImage = birdImage;
    }

}
