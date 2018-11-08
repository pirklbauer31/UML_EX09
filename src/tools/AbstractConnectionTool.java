package tools;

import editor.Model;
import editor.UMLConnection;
import editor.UMLRectangle;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public abstract class AbstractConnectionTool implements Tool {

    private Model mModel;
    private UMLConnection mConnection;

    private UMLRectangle mSource;
    private UMLRectangle mTarget;

    /*
    public AbstractConnectionTool(Model _model) {
        mModel = _model;
        mSource = null;
        mTarget = null;
    }
    */

    @Override
    public void mousePressed(MouseEvent _e) {
        if (mSource == null) {
            mSource = findRectangle(_e.getX(), _e.getY(), getSourceList());
        }
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
         if (mSource != null && mTarget == null) {
            mTarget = findRectangle(_e.getX(), _e.getY(), getTargetList());
        }

        if (mSource != null && mTarget != null) {
            mConnection = createConnection(mSource.getCenterX(), mSource.getCenterY(), mTarget.getCenterX(), mTarget.getCenterY());
            addConnection(mConnection);

            mConnection = null;
            mSource = null;
            mTarget = null;
        } else {
            mSource = null;
            mTarget = null;
        }

    }


    private UMLRectangle findRectangle(int x1, int y1, ArrayList<UMLRectangle> _list) {
        UMLRectangle foundRec = null;

        for (UMLRectangle rec:
             _list) {
            if (rec.contains(x1, y1)) {
                foundRec = rec;
            }
        }

        return foundRec;
    }

    abstract UMLConnection createConnection(double x1, double y1, double x2, double y2);

    abstract ArrayList<UMLRectangle> getSourceList();
    abstract ArrayList<UMLRectangle> getTargetList();

    abstract void addConnection(UMLConnection _connection);

}
