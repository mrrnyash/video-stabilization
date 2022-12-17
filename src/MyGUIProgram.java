import java.awt.*;


public class MyGUIProgram extends Frame {


    MyGUIProgram() {
        Button b = new Button("Click!");
        b.setBounds(30, 100, 80, 30);
        add(b);
        TextField t = new TextField();
        t.setBounds(10, 50, 150, 30);
        add(t);
        setSize(800, 600);
        setTitle("Example");
        setLayout(null);
        setVisible(true);

    }
}
