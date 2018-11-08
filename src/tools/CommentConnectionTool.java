package tools;

import editor.*;

import java.awt.*;
import java.util.ArrayList;

public class CommentConnectionTool extends AbstractConnectionTool {

    private Model mModel;

    public CommentConnectionTool(Model _model) {
        mModel = _model;
    }

    @Override
    UMLConnection createConnection(double x1, double y1, double x2, double y2) {
        return new CommentConnection(new Point((int)x1, (int)y1), new Point((int)x2, (int)y2));
    }

    @Override
    ArrayList<UMLRectangle> getSourceList() {
        return mModel.getmCommentBoxes();
    }

    @Override
    ArrayList<UMLRectangle> getTargetList() {
        return mModel.getmRectangles();
    }

    @Override
    void addConnection(UMLConnection _connection) {
        if (_connection instanceof CommentConnection) {
            CommentConnection connection = new CommentConnection(_connection.getEndPointA(), _connection.getEndPointB());
            mModel.addCommentConnection(connection);
            mModel.updateObservers();
        }
    }
}
