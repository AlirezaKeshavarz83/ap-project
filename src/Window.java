import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class Window extends JPanel{
    JFrame frame;
    private Vector<TextField> texts = new Vector<>();
    Window(){
        this.frame = GUI.getFrame();
        this.setLayout(new GridLayout(20, 1, 10, 5));
    }
    public Vector<TextField> getTexts(){
        return this.texts;
    }
    public void addButton(String s, String t){
        this.add(GUI.newButton(s, t));
    }
    public void addText(String s){
        var label = new JLabel();
        label.setText(s);
        this.add(label);
    }
    public void addInputField(){
        TextField text = new TextField("");
        text.setFont(GUI.getFont());
        //this.setLayout(new GridLayout(4, 2, 10, 5));
        this.add(text);
        texts.add(text);
    }
    public void addEnterButton(){

        JButton button = new JButton("enter");
        button.setFont(GUI.getFont());

        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                for(var text : texts){
                    if(text.getText().isBlank()){
                        return;
                    }
                }
                for(var text : texts){
                    UI.newLine(text.getText());
                }
            }
        });
        this.add(button);
    }
}
