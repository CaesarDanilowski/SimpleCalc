import javax.swing.*;
import java.awt.*;
import com.cezarydanilowski.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("SimpleCalc");
            frame.setSize(200,300);
            frame.setResizable(false);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new PrimaryPanel());
        });
    }
}
