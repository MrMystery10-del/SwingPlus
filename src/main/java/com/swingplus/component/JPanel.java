package com.swingplus.component;


import com.swingplus.component.adapter.MouseHover;
import com.swingplus.function.GraphicFunction;
import com.swingplus.function.GraphicScaleFunction;
import com.swingplus.function.ModificationFunction;
import com.swingplus.model.HoverEffect;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Provides an enhanced JPanel for resizable Swing-based GUIs while using a null layout.
 * <p>
 * All components can be added to this JPanel in a default 1920x1080 virtual ratio.
 * When added with a given bounds, all components will get resized accordingly.
 * <p>
 * This class serves as an extension to the standard javax.swing.JPanel, offering additional
 * functionality and ease of use in GUI development. It encapsulates the underlying Swing panel,
 * which can be retrieved using the 'getSWING_PANEL()' method for further manipulation.
 *
 * @implNote The SwingPlus JPanel acts as a facade to the javax.swing.JPanel, streamlining
 * certain operations and providing a more intuitive interface for Swing GUI development.
 */
public class JPanel {

    protected final javax.swing.JPanel SWING_PANEL = new SwingPanel();

    private Dimension ratio = new Dimension(1920, 1080);
    private GraphicScaleFunction graphicScaleFunction = (graphics2D, scaleX, scaleY) -> {};
    private HoverEffect<JPanel> hoverEffect;

    /**
     * This method can be used to draw graphics on the JPanel.
     * The graphics will not resize with the application.
     * For resizable graphics use 'applyGraphics(GraphicScaleFunction graphicScaleFunction)'
     */
    public JPanel applyGraphics(GraphicFunction graphicFunction) {
        this.graphicScaleFunction = ((graphics2D, scaleX, scaleY) -> graphicFunction.apply(graphics2D));

        return this;
    }

    /**
     * This method can be used to draw graphics on the JPanel.
     * All graphics can resize with the application.
     * For resizable graphics use 'applyGraphics(GraphicScaleFunction graphicScaleFunction)'
     */
    public JPanel applyGraphics(GraphicScaleFunction graphicScaleFunction) {
        this.graphicScaleFunction = graphicScaleFunction;

        return this;
    }

    public JPanel add(Component component) {
        SWING_PANEL.add(component);

        return this;
    }

    public JPanel whenHover(HoverEffect<JPanel> hoverEffect) {
        SWING_PANEL.addMouseListener(new MouseHover<>(this, hoverEffect));

        return this;
    }

    /**
     * Sets a new virtual ratio to the JPanel.
     * All components will resize, according to the ratio.
     * When the ratio is, for example, 1920x1080,
     * you can place all components in this virtual ratio using the bounds of the components.
     * Please do not set the ratio like this: '16:9', instead use the full dimension to have enough space for components.
     * Used for inner sections using JPanels.
     */
    public JPanel setDimension(Dimension ratio) {
        this.ratio = ratio;

        return this;
    }

    public Dimension getDimension() {
        return ratio;
    }

    @Deprecated
    public javax.swing.JPanel getSWING_PANEL() {
        return SWING_PANEL;
    }

    {
        SWING_PANEL.setLayout(null);
        SWING_PANEL.addComponentListener(new ResizeManager());
    }

    private class SwingPanel extends javax.swing.JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

            Dimension newSize = SWING_PANEL.getSize();
            double scaleX = newSize.getWidth() / ratio.getWidth();
            double scaleY = newSize.getHeight() / ratio.getHeight();

            graphicScaleFunction.apply(g2d, scaleX, scaleY);
        }
    }

    private class ResizeManager extends ComponentAdapter {

        @Override
        public void componentResized(ComponentEvent event) {
            super.componentResized(event);

            Dimension newSize = SWING_PANEL.getSize();
            double scaleX = newSize.getWidth() / ratio.getWidth();
            double scaleY = newSize.getHeight() / ratio.getHeight();

            for (int index = 0; index < SWING_PANEL.getComponentCount(); index++) {
                Component component = SWING_PANEL.getComponents()[index];
                Rectangle bounds = component.getBounds();

                int x = (int) Math.round(bounds.getX() * scaleX);
                int y = (int) Math.round(bounds.getY() * scaleY);
                int width = (int) Math.round(bounds.getWidth() * scaleX);
                int height = (int) Math.round(bounds.getHeight() * scaleY);

                component.setBounds(x, y, width, height);

                Font font = component.getFont();
                if (font != null) {
                    float size = font.getSize() * (float) scaleX;
                    component.setFont(font.deriveFont(size));
                }
            }
        }
    }
}
