package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawingPanel extends JPanel {

    private BufferedImage mImage;

    public DrawingPanel() {

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if(mImage != null) {
            g.drawImage(mImage, 0,0, null);
        }
    }

    public BufferedImage getmImage() {
        return mImage;
    }

    public void setmImage(BufferedImage mImage) {
        this.mImage = mImage;
    }
}
