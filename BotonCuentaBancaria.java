import java.awt.*;
import javax.swing.border.*;
import javax.swing.*;

public class BotonCuentaBancaria extends JButton {
    public BotonCuentaBancaria (String b){
        setText(b);
        setPreferredSize(new Dimension(125, 40));
        setBorder(BorderFactory.createCompoundBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, false),null));
    }
}