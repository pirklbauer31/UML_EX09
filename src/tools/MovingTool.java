package tools;

import editor.Model;
import editor.UMLConnection;
import editor.UMLRectangle;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MovingTool implements Tool {

    private Model mModel;
    private UMLRectangle mRectangleToMove;

    public MovingTool(Model _model) {
        mModel = _model;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        if (mRectangleToMove != null) {
            Point oldPosition = new Point((int)mRectangleToMove.getCenterX(), (int)mRectangleToMove.getCenterY());

            /*
            mRectangleToMove.x = _e.getX();
            mRectangleToMove.y = _e.getY();
            */
            mRectangleToMove.moveRectangle(_e.getX(), _e.getY());

            Point newPosition = new Point((int)mRectangleToMove.getCenterX(), (int)mRectangleToMove.getCenterY());

            moveConnections(oldPosition, newPosition);

            mModel.updateObservers();
            mRectangleToMove = null;
        }
    }

    @Override
    public void mousePressed(MouseEvent _e) {
        mRectangleToMove = findRectangle(_e.getX(), _e.getY());
    }

    private UMLRectangle findRectangle(int x, int y) {
        for (UMLRectangle rec: mModel.getmRectangles()) {
            if (rec.contains(x,y)) {
                return rec;
            }
        }

        for (UMLRectangle rec: mModel.getmCommentBoxes()) {
            if (rec.contains(x,y)) {
                return rec;
            }
        }

        return null;
    }

    private void moveConnections(Point _oldPosition, Point _newPosition) {
        for (UMLConnection connection: mModel.getmLines()) {
            if(connection.getEndPointA().getX() == _oldPosition.getX() && connection.getEndPointA().getY() == _oldPosition.getY()) {
                connection.setEndPointA(new Point(_newPosition.x, _newPosition.y));
            } else if(connection.getEndPointB().getX() == _oldPosition.getX() && connection.getEndPointB().getY() == _oldPosition.getY()) {
                connection.setEndPointB(new Point(_newPosition.x, _newPosition.y));
            }
        }

        for (UMLConnection connection: mModel.getmCommentLines()) {
            if(connection.getEndPointA().getX() == _oldPosition.getX() && connection.getEndPointA().getY() == _oldPosition.getY()) {
                connection.setEndPointA(new Point(_newPosition.x, _newPosition.y));
            } else if(connection.getEndPointB().getX() == _oldPosition.getX() && connection.getEndPointB().getY() == _oldPosition.getY()) {
                connection.setEndPointB(new Point(_newPosition.x, _newPosition.y));
            }
        }

    }

}
