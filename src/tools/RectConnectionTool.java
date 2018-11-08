package tools;

import editor.Model;
import editor.RectConnection;
import editor.UMLConnection;
import editor.UMLRectangle;

import java.awt.*;
import java.util.ArrayList;

public class RectConnectionTool extends AbstractConnectionTool {

    private Model mModel;

    public RectConnectionTool(Model _model) {
        mModel = _model;
    }

    @Override
    UMLConnection createConnection(double x1, double y1, double x2, double y2) {
        return new RectConnection(new Point((int)x1, (int)y1), new Point((int)x2, (int)y2));
    }

    @Override
    ArrayList<UMLRectangle> getSourceList() {
        return mModel.getmRectangles();
    }

    @Override
    ArrayList<UMLRectangle> getTargetList() {
        return mModel.getmRectangles();
    }

    @Override
    void addConnection(UMLConnection _connection) {
        if (_connection instanceof RectConnection) {
            RectConnection rect = new RectConnection(_connection.getEndPointA(), _connection.getEndPointB());
            mModel.addRectConnection(rect);
            mModel.updateObservers();
        }

    }
}
