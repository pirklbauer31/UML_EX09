package editor;

import java.awt.*;
import java.awt.geom.Line2D;

public abstract class UMLConnection {
    private String mName;

    private Point endPointA;
    private Point endPointB;

    private Line2D mConnectionLine;

    public UMLConnection() {
        endPointA = null;
        endPointB = null;
    }

    public UMLConnection(Point _pointA, Point _pointB) {
        endPointA = _pointA;
        endPointB = _pointB;

        Line2D.Double line = new Line2D.Double(_pointA.getX(), _pointA.getY(), _pointB.getX(), _pointB.getY());
        mConnectionLine = line;
    }

    private void resetLine() {
        Line2D.Double line = new Line2D.Double(endPointA, endPointB);
        mConnectionLine = line;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public Point getEndPointA() {
        return endPointA;
    }

    public void setEndPointA(Point endPointA) {
        this.endPointA = endPointA;
        resetLine();
    }

    public Point getEndPointB() {
        return endPointB;
    }

    public void setEndPointB(Point endPointB) {
        this.endPointB = endPointB;
        resetLine();
    }

    public Line2D getmConnectionLine() {
        return mConnectionLine;
    }

    public void setmConnectionLine(Line2D mConnectionLine) {
        this.mConnectionLine = mConnectionLine;
    }
}
