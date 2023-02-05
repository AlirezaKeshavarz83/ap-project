import javax.swing.*;
import java.awt.*;

public class GUI{
    private static final int width = 500, height = 400, fontSize = 18;
    private static final Font font = new Font("Monospaced", Font.PLAIN, fontSize);
    private static JFrame frame;


    public static void init(){
        frame = new JFrame();
        frame.setSize(width, height);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public static void clear() {
        frame.getContentPane().removeAll();
        frame.repaint();
    }
    public static void showWindow(Window window){
        clear();
        frame.add(BorderLayout.CENTER, new JScrollPane(window));
        frame.setVisible(true);
    }
    public static JButton newButton(String s, String t) {
        JButton button = new JButton(s);
        button.setFont(getFont());
        button.addActionListener(e -> UI.newLine(t));
        return button;
    }
    public static JFrame getFrame(){
        return frame;
    }

    public static Font getFont() {
        return font;
    }
}
