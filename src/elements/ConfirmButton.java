package elements;

import javax.swing.*;
import java.awt.*;

public class ConfirmButton extends JButton {
    public ConfirmButton() {
        this.setText("Подтвердить");
        this.setBackground(Color.GREEN);
        this.setVerticalAlignment(this.BOTTOM);
        this.setHorizontalAlignment(this.RIGHT);
    }

}
