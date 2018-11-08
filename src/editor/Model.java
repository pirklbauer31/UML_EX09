package editor;

import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Model {

    /**
     * The ArrayList containing the observers
     */
    private ArrayList<View> mObservers = new ArrayList<>();

    private ArrayList<UMLRectangle> mRectangles = new ArrayList<>();
    private ArrayList<RectConnection> mLines = new ArrayList<>();
    private ArrayList<UMLRectangle> mCommentBoxes = new ArrayList<>();
    private ArrayList<CommentConnection> mCommentLines = new ArrayList<>();

    /**
     * Width of the image to draw in the panel
     */
    private int mImageWidth;
    /**
     * Height of the image to draw in the panel
     */
    private int mImageHeight;

    /**
     * Keeps track if mouse event has been triggered
     */
    private boolean mMouseEvent;
    /**
     * X Position of the mouse cursor when clicked
     */
    private int mMousePosX;
    /**
     * Y Position of the mouse cursor when clicked
     */
    private int mMousePosY;
    /**
     * Which mouse button has been clicked on event
     */
    private int mMouseButton;

    private UMLRectangle mRectPosition1;
    private UMLRectangle mRectPosition2;
    private final static float dash1[] = {5};
    private final static BasicStroke dashedStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, dash1, 0.0f);

    /*
    public void checkPosition(int _posX, int _posY) {
        UMLRectangle shapeToFind = null;

        for (UMLRectangle rect: mRectangles) {
            if (rect.contains(_posX, _posY)) {
                shapeToFind = rect;
            }
        }

        for (CommentBox comment: mCommentBoxes) {
            if (comment.contains(_posX, _posY)) {
                shapeToFind = comment;
            }
        }

        if (shapeToFind != null && mRectPosition1 == null) {
            mRectPosition1 = shapeToFind;
        } else if (shapeToFind != null && mRectPosition2 == null && shapeToFind != mRectPosition1) {
            mRectPosition2 = shapeToFind;
            generateLineBetweenRectangles();
        }

    }

    private void generateLineBetweenRectangles() {
        if (mRectPosition1 instanceof CommentBox && mRectPosition2 instanceof CommentBox) {
            System.out.println("Can't connect 2 comment boxes!");
            mRectPosition1 = null;
            mRectPosition2 = null;
        } else {
            if (mRectPosition1 instanceof CommentBox || mRectPosition2 instanceof CommentBox) {
                //mCommentLines.add(line);
                CommentConnection commentLine = new CommentConnection(mRectPosition1, mRectPosition2);
                mCommentLines.add(commentLine);
            } else {
                RectConnection rectLine = new RectConnection(mRectPosition1, mRectPosition2);
                mLines.add(rectLine);
            }

            mRectPosition1 = null;
            mRectPosition2 = null;

            updateObservers();
        }
    }
    */

    public void generateNameTag(int _posX, int _posY, String _name) {
        UMLRectangle shapeToFind = null;
        UMLConnection connectionToFind = null;

        for (UMLRectangle rect: mRectangles) {
            if (rect.contains(_posX, _posY)) {
                shapeToFind = rect;
            }
        }

        Rectangle testRect = new Rectangle(_posX, _posY, 10, 10);
        for (RectConnection connection: mLines) {

            if (connection.getmConnectionLine().intersects(testRect)) {
                connectionToFind = connection;
            }
        }

        for (CommentConnection commConnection: mCommentLines) {
            if (commConnection.getmConnectionLine().intersects(testRect)) {
                connectionToFind = commConnection;
            }
        }

        if (shapeToFind != null) {
            shapeToFind.setmName(_name);
            updateObservers();
        } else if (connectionToFind != null) {
            connectionToFind.setmName(_name);
            updateObservers();
        }
    }

    public void addRectangle(UMLRectangle _rect) {
        mRectangles.add(_rect);
    }

    public void addComment(CommentBox _comment) {
        mCommentBoxes.add(_comment);
    }

    public void addRectConnection(RectConnection _connection) { mLines.add(_connection); }

    public void addCommentConnection(CommentConnection _connection) { mCommentLines.add(_connection); }

    public void addObserver(View _view) {
        mObservers.add(_view);
    }

    public void updateObservers () {
        for (View observer : mObservers) {

            BufferedImage mImage = new BufferedImage(mImageWidth, mImageHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics2D = (Graphics2D) mImage.getGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            graphics2D.setFont(new Font("TimesRoman", Font.PLAIN, 15));

            graphics2D.setColor(Color.white);
            graphics2D.fillRect(0,0, mImageWidth, mImageHeight);
            graphics2D.setColor(Color.black);
            for (UMLRectangle rec:
                 mRectangles) {
                graphics2D.draw(rec);

                String elementName = rec.getmName();
                if (elementName != null && !elementName.isEmpty()) {
                    graphics2D.drawString(elementName, rec.x+10, rec.y+15);
                }
            }

            for (RectConnection rectLine: mLines) {
                graphics2D.draw(rectLine.getmConnectionLine());

                String elementName = rectLine.getmName();
                if (elementName != null && !elementName.isEmpty()) {
                    int x = (rectLine.getEndPointB().x + rectLine.getEndPointA().x)/2;
                    int y = (rectLine.getEndPointB().y + rectLine.getEndPointA().y)/2;
                    graphics2D.drawString(elementName, x, y);
                }
            }

            graphics2D.setStroke(dashedStroke);
            for (UMLRectangle comment: mCommentBoxes) {
                graphics2D.draw(comment);
            }

            for (CommentConnection commentLine: mCommentLines) {
                graphics2D.draw(commentLine.getmConnectionLine());

                String elementName = commentLine.getmName();
                if (elementName != null && !elementName.isEmpty()) {
                    int x = (commentLine.getEndPointB().x + commentLine.getEndPointA().x)/2;
                    int y = (commentLine.getEndPointB().y + commentLine.getEndPointA().y)/2;
                    graphics2D.drawString(elementName, x, y);}
            }

            observer.update(mImage);
        }
    }

    public void setSize(int _width, int _height) {
        mImageWidth = _width;
        mImageHeight = _height;
    }

    //Getters and setters

    public boolean isMouseEvent() {
        return mMouseEvent;
    }

    public void setMouseEvent(boolean _mouseEvent) {
        this.mMouseEvent = _mouseEvent;
    }

    public ArrayList<View> getmObservers() {
        return mObservers;
    }

    public ArrayList<UMLRectangle> getmRectangles() {
        return mRectangles;
    }

    public ArrayList<UMLRectangle> getmCommentBoxes() {
        return mCommentBoxes;
    }

}
