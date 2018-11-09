package editor;

import java.awt.*;
import java.util.ArrayList;

public class UMLRectangle extends Rectangle {
    private String mName;

    private ArrayList<UMLConnection> mConnections;
    private ArrayList<Rectangle> mCompartments;

    public UMLRectangle() {
        super();
        mConnections = new ArrayList<>();
        mCompartments = new ArrayList<>();
    }

    public void moveRectangle(int x, int y) {
        this.x = x;
        this.y = y;

        for(Rectangle rec: mCompartments) {
            rec.x = x;
            rec.y = y+this.height;
        }
    }

    public void addCompartment() {
        Rectangle rec = new Rectangle(this.x, this.y + this.height, this.width, this.height);
        mCompartments.add(rec);
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

    public ArrayList<Rectangle> getmCompartments() {
        return mCompartments;
    }
}
