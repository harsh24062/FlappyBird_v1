import java.awt.Image;

public class Pipe {

    int pipeX; 
    int pipeY;
    int pipeWidth;
    int pipeHeight;
    Image pipeImage;
    boolean isPassed;     // used to keep the track that bird have passed the pipe or not

    public Pipe(int pipeX, int pipeY, int pipeWidth, int pipeHeight, Image pipeImage, boolean isPassed) {
        this.pipeX = pipeX;
        this.pipeY = pipeY;
        this.pipeWidth = pipeWidth;
        this.pipeHeight = pipeHeight;
        this.pipeImage = pipeImage;
        this.isPassed = isPassed;
    }

}
