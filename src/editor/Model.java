package editor;


import java.awt.*;
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

    private final static float dash1[] = {5};
    private final static BasicStroke dashedStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, dash1, 0.0f);

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

            observer.update(createMainImage(), createOverviewImage());
        }
    }

    private BufferedImage createMainImage() {
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

            for (Rectangle compartment: rec.getmCompartments()) {
                graphics2D.draw(compartment);
            }

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

        return mImage;
    }

    private BufferedImage createOverviewImage() {
        BufferedImage mImage = new BufferedImage(mImageWidth, mImageHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = (Graphics2D) mImage.getGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.setFont(new Font("TimesRoman", Font.PLAIN, 15));

        graphics2D.setColor(Color.lightGray);
        graphics2D.fillRect(mObservers.get(0).getmOverViewPanel().getX(),mObservers.get(0).getmOverViewPanel().getY(), 100, mImageHeight);

        graphics2D.setColor(Color.black);
        for (UMLRectangle rec:
                mRectangles) {

            UMLRectangle smallerRect = rec.getMiniatureVersion(10);
            graphics2D.draw(smallerRect);

            for (Rectangle compartment: smallerRect.getmCompartments()) {
                graphics2D.draw(compartment);
            }
        }

        for (RectConnection rectLine: mLines) {

            Point newPointA = new Point((int)(rectLine.getEndPointA().getX()/10), (int)(rectLine.getEndPointA().getY()/10));
            Point newPointB = new Point((int)(rectLine.getEndPointB().getX()/10), (int)(rectLine.getEndPointB().getY()/10));
            RectConnection smallerConnection = new RectConnection(newPointA, newPointB);

            graphics2D.draw(smallerConnection.getmConnectionLine());
        }

        graphics2D.setStroke(dashedStroke);
        for (UMLRectangle comment: mCommentBoxes) {
            UMLRectangle smallerComment = comment.getMiniatureVersion(10);
            graphics2D.draw(smallerComment);
        }

        for (CommentConnection commentLine: mCommentLines) {
            Point newPointA = new Point((int)(commentLine.getEndPointA().getX()/10), (int)(commentLine.getEndPointA().getY()/10));
            Point newPointB = new Point((int)(commentLine.getEndPointB().getX()/10), (int)(commentLine.getEndPointB().getY()/10));
            CommentConnection smallerConnection = new CommentConnection(newPointA, newPointB);

            graphics2D.draw(smallerConnection.getmConnectionLine());
        }

        return mImage;
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

    public ArrayList<RectConnection> getmLines() {
        return mLines;
    }

    public ArrayList<CommentConnection> getmCommentLines() {
        return mCommentLines;
    }
}
