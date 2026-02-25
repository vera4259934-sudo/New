import java.awt.*;

public class Ch {

    Image _image;
    int x,y, health;
    Ch(String s, int _x, int _y )
    {
        x= _x;
        y= _y;
        _image = Toolkit.getDefaultToolkit().createImage(s);
    }
}