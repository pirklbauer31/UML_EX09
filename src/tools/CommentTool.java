package tools;

import editor.CommentBox;
import editor.Model;

import java.awt.event.MouseEvent;

public class CommentTool implements Tool {

    private Model mModel;

    public CommentTool(Model _model) {
        mModel = _model;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        CommentBox comment = new CommentBox();
        comment.setLocation(_e.getX(), _e.getY());
        comment.setSize(80, 60);

        mModel.addComment(comment);

        mModel.updateObservers();
    }

    @Override
    public void mousePressed(MouseEvent _e) {

    }
}
