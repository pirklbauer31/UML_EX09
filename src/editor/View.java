package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class View implements DataObserver {

    /**
     * The Drawing Panel to paint images in.
     */
    private DrawingPanel mPanel = new DrawingPanel();

    private DrawingPanel mOverViewPanel = new DrawingPanel();

    private JTextField mNameElementTextField = new JTextField();

    public View (Controller _controller) {
        JFrame mFrame = new JFrame();
        mFrame.setSize(1000,800);
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //mFrame.addWindowListener(_controller);

        JButton mDrawRectButton = new JButton();
        mDrawRectButton.setText("Rectangle");
        mDrawRectButton.setActionCommand("r");
        mDrawRectButton.addActionListener(_controller);

        JButton mDrawCommentBoxButton = new JButton();
        mDrawCommentBoxButton.setText("Comment");
        mDrawCommentBoxButton.setActionCommand("comment");
        mDrawCommentBoxButton.addActionListener(_controller);

        JButton mDrawConnectionButton = new JButton();
        mDrawConnectionButton.setText("Line");
        mDrawConnectionButton.setActionCommand("c");
        mDrawConnectionButton.addActionListener(_controller);

        JButton mDrawCommentConnectionButton = new JButton();
        mDrawCommentConnectionButton.setText("CommentLine");
        mDrawCommentConnectionButton.setActionCommand("cl");
        mDrawCommentConnectionButton.addActionListener(_controller);

        JButton mNameElementButton = new JButton();
        mNameElementButton.setText("Name");
        mNameElementButton.setActionCommand("name");
        mNameElementButton.addActionListener(_controller);

        JTextField nameTextField = new JTextField("Test", 12);
        setmNameElementTextField(nameTextField);
      //  mNameElementTextField.setText("Test");
      //  mNameElementTextField.addActionListener(_controller);

        JButton mMoveRectangleButton = new JButton();
        mMoveRectangleButton.setText("Move");
        mMoveRectangleButton.setActionCommand("move");
        mMoveRectangleButton.addActionListener(_controller);

        mPanel.setBackground(Color.white);
        mPanel.addComponentListener(_controller);
        mPanel.addMouseListener(_controller);

        mOverViewPanel.setBackground(Color.lightGray);

        JPanel actionPanel = new JPanel();
        actionPanel.setBackground(Color.lightGray);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Color.black);

        mPanel.setPreferredSize(new Dimension(900, 800));
        mOverViewPanel.setPreferredSize(new Dimension(100, 800));

        mFrame.setLayout(new BorderLayout());
        mFrame.add(contentPanel, BorderLayout.CENTER);
        contentPanel.add(mPanel, BorderLayout.CENTER);
        contentPanel.add(mOverViewPanel, BorderLayout.EAST);
        mFrame.add(actionPanel, BorderLayout.SOUTH);
        actionPanel.add(mDrawRectButton);
        actionPanel.add(mDrawCommentBoxButton);
        actionPanel.add(mDrawConnectionButton);
        actionPanel.add(mDrawCommentConnectionButton);
        actionPanel.add(mNameElementButton);
        actionPanel.add(mNameElementTextField);
        actionPanel.add(mMoveRectangleButton);

        mFrame.setVisible(true);
    }

    @Override
    public void update(BufferedImage _data, BufferedImage _overviewData) {
        mPanel.setmImage(_data);
        mPanel.repaint();

        mOverViewPanel.setmImage(_overviewData);
        mOverViewPanel.repaint();
    }

    public DrawingPanel getmPanel() {
        return mPanel;
    }

    public void setmPanel(DrawingPanel _mPanel) {
        this.mPanel = mPanel;
    }

    public JTextField getmNameElementTextField() {
        return mNameElementTextField;
    }

    public void setmNameElementTextField(JTextField mNameElementTextField) {
        this.mNameElementTextField = mNameElementTextField;
    }

    public DrawingPanel getmOverViewPanel() {
        return mOverViewPanel;
    }
}
