package com.swingplus.function;

import java.awt.*;

/**
 * Used to provide graphics as lambda functions
 */
public interface GraphicFunction {

    /**
     * Example usage:
     * <pre>
     *     JPanel panel = new JPanel().applyGraphics(graphics2D -> {
     *             graphics2D.setColor(Color.CYAN);
     *             graphics2D.fillRect(10, 10, 100, 100);
     *         });
     * </pre>
     */
    void apply(Graphics2D graphics2D);
}
