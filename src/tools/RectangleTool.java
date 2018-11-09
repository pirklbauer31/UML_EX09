package tools;

import editor.Model;
import editor.UMLRectangle;

import java.awt.event.MouseEvent;

public class RectangleTool implements Tool {

    private Model mModel;

    public RectangleTool(Model _model) {
        mModel = _model;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        UMLRectangle rec = new UMLRectangle();
        rec.setLocation(_e.getX(), _e.getY());
        rec.setSize(80, 60);
        rec.addCompartment();

        mModel.addRectangle(rec);

        mModel.updateObservers();
    }

    @Override
    public void mousePressed(MouseEvent _e) {

    }
}
