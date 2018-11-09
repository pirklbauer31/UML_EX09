package editor;

import java.awt.*;
import java.util.ArrayList;

public class UMLRectangle extends Rectangle {
    private String mName;

    private ArrayList<UMLConnection> mConnections;

    public UMLRectangle() {
        super();
        mConnections = new ArrayList<>();
    }

    public void moveRectangle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void addConnection(UMLConnection _connection) { mConnections.add(_connection); }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public ArrayList<UMLConnection> getmConnections() {
        return mConnections;
    }
}
