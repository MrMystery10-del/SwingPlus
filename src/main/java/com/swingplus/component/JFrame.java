package com.swingplus.component;

import com.swingplus.component.adapter.MouseHover;
import com.swingplus.model.HoverEffect;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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

    public JFrame hide(boolean hide) {
        SWING_FRAME.setVisible(!hide);

        return this;
    }

    public JFrame whenHover(HoverEffect<JFrame> hoverEffect) {
        SWING_FRAME.addMouseListener(new MouseHover<>(this, hoverEffect));

        return this;
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

    public javax.swing.JFrame getSWING_FRAME() {
        return SWING_FRAME;
    }

    {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        SWING_FRAME.setSize((int) dimension.getWidth() / 2, (int) dimension.getHeight() / 2);

        SWING_FRAME.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        SWING_FRAME.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        SWING_FRAME.setLocationRelativeTo(null);

        SWING_FRAME.addComponentListener(new ResizeConstrains());

        SWING_FRAME.setVisible(true);
    }

    private class ResizeConstrains extends ComponentAdapter {

        private int widthBeforeResize, heightBeforeResize;

        @Override
        public void componentResized(ComponentEvent event) {
            if (contentPane == null) {
                return;
            }

            Dimension defaultRatioSize = contentPane.getDimension();
            double forcedAspectRatio = defaultRatioSize.getWidth() / defaultRatioSize.height;

            int currentWidth = SWING_FRAME.getWidth();
            int currentHeight = SWING_FRAME.getHeight();

            if (!isBelowMinimumBoundsThenStayAtMinimum(currentWidth, currentHeight, defaultRatioSize)) {
                correctAspectRatioAfterResize(currentWidth, currentHeight, forcedAspectRatio);
            }
        }

        private boolean isBelowMinimumBoundsThenStayAtMinimum(int currentWidth, int currentHeight, Dimension defaultRatioSize) {
            if (currentWidth + 1 < defaultRatioSize.getWidth() / 2 || currentHeight + 1 < defaultRatioSize.getHeight() / 2) {
                SWING_FRAME.setSize((int) (defaultRatioSize.getWidth() / 2), (int) (defaultRatioSize.getHeight() / 2));
                return true;
            }
            return false;
        }

        private void correctAspectRatioAfterResize(int currentWidth, int currentHeight, double forcedAspectRatio) {
            double currentAspectRatio = currentAspectRatio();

            if (currentAspectRatio != forcedAspectRatio) {
                if (currentAspectRatio > forcedAspectRatio) {
                    if (currentWidth > widthBeforeResize) {
                        resizeRelativeToWidth(forcedAspectRatio, currentWidth);
                    } else {
                        resizeRelativeToHeight(forcedAspectRatio, currentHeight);
                    }
                } else {
                    if (currentHeight > heightBeforeResize) {
                        resizeRelativeToHeight(forcedAspectRatio, currentHeight);
                    } else {
                        resizeRelativeToWidth(forcedAspectRatio, currentWidth);
                    }
                }
            }

            widthBeforeResize = currentWidth;
            heightBeforeResize = currentHeight;
        }

        private void resizeRelativeToWidth(double forcedAspectRatio, int width) {
            SWING_FRAME.setSize(width, (int) (width / forcedAspectRatio));
        }

        private void resizeRelativeToHeight(double forcedAspectRatio, int height) {
            SWING_FRAME.setSize((int) (height * forcedAspectRatio), height);
        }

        private double currentAspectRatio() {
            return (double) SWING_FRAME.getWidth() / SWING_FRAME.getHeight();
        }
    }
}
