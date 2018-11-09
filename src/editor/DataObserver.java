package editor;

import java.awt.image.BufferedImage;

public interface DataObserver {
    /**
     * Used to update observers from model
     * @param _data
     */
    void update(BufferedImage _data, BufferedImage _overviewData);
}
