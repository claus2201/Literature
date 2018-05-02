package Literature;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

public class PlayGame {

    public static void main(String[] args)
    {
        Game frame = new Game();
        frame.setTitle("Lirerature");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,200);
        frame.setVisible(true);

    }


}

