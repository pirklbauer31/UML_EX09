package tools;

import editor.*;

import java.awt.*;
import java.awt.event.MouseEvent;

public class NamingTool implements Tool {

    private Model mModel;

    public NamingTool(Model _model) {
        mModel = _model;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        String elementName = mModel.getmObservers().get(0).getmNameElementTextField().getText();
        generateNameTag(_e.getX(), _e.getY(), elementName);
    }

    @Override
    public void mousePressed(MouseEvent _e) {

    }

    private void generateNameTag(int _posX, int _posY, String _name) {
        UMLRectangle shapeToFind = null;
        UMLConnection connectionToFind = null;

        for (UMLRectangle rect: mModel.getmRectangles()) {
            if (rect.contains(_posX, _posY)) {
                shapeToFind = rect;
            }
        }

        Rectangle testRect = new Rectangle(_posX, _posY, 10, 10);
        for (RectConnection connection: mModel.getmLines()) {

            if (connection.getmConnectionLine().intersects(testRect)) {
                connectionToFind = connection;
            }
        }

        for (CommentConnection commConnection: mModel.getmCommentLines()) {
            if (commConnection.getmConnectionLine().intersects(testRect)) {
                connectionToFind = commConnection;
            }
        }

        if (shapeToFind != null) {
            shapeToFind.setmName(_name);
            mModel.updateObservers();
        } else if (connectionToFind != null) {
            connectionToFind.setmName(_name);
            mModel.updateObservers();
        }
    }
}
