import javax.swing.JFrame;

public class Frame {
    public static void main(String[] args) throws Exception {
        
        int screenWidth = 360;
        int screenHeight = 640;

        JFrame frame = new JFrame("FlappyBird");
        frame.setSize(screenWidth, screenHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FlappyBird flappyBird = new FlappyBird();

        frame.add(flappyBird);
        frame.pack();
        flappyBird.requestFocusInWindow();

        frame.setVisible(true);
    }
}
