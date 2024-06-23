package com.swingplus.components;

import lombok.NoArgsConstructor;

import java.awt.*;

/**
 * Provides an enhanced JFrame easy to use Swing-based GUIs.
 * This class serves as an extension to the standard javax.swing.JFrame, offering additional
 * functionality and ease of use in GUI development. It encapsulates the underlying JFrame panel,
 * which can be retrieved using the 'getSWING_FRAME()' method for further manipulation.
 *
 * @implNote The SwingPlus JFrame acts as a facade to the javax.swing.JFrame, streamlining
 * certain operations and providing a more intuitive interface for Swing GUI development.
 */
@NoArgsConstructor
public class JFrame {

    private final javax.swing.JFrame SWING_FRAME = new javax.swing.JFrame();

    private JPanel contentPane = null;

    public JFrame(String title) {
        setTitle(title);
    }

    public JFrame setContentPane(JPanel contentPane) {
        this.contentPane = contentPane;

        SWING_FRAME.setContentPane(contentPane.SWING_PANEL);
        SWING_FRAME.getContentPane().revalidate();

        return this;
    }

    public JFrame setTitle(String title) {
        SWING_FRAME.setTitle(title);

        return this;
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    public javax.swing.JFrame getSWING_FRAME(){
        return SWING_FRAME;
    }

    {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        SWING_FRAME.setSize((int) dimension.getWidth() / 2, (int) dimension.getHeight() / 2);

        SWING_FRAME.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        SWING_FRAME.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        SWING_FRAME.setLocationRelativeTo(null);

        SWING_FRAME.setVisible(true);
    }
}
