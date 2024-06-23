# SwingPlus
Provides an enhanced resizable Swing-based GUIs while using a null layout.
SwingPlus acts as a facade to the javax.swing, streamlining
certain operations and providing a more intuitive interface for Swing GUI development

SwingPlus exist current at a very early state to represent the idea for possible future development.
It is orientated at NimbusUI.

## Gradle

## Example usage

This method is a full working Swing-GUI without the need to any additional coding.
The GUI is fully extended and a cyan cube is being drawn on the screen:
```java
public static void main(String[] args) {
        JFrame frame = new JFrame("""
                SwingPlus? Really?
                Do you think this could change the learning curve of students,
                so they dont need to fight witch layouts and a weird pack() method?
                """
        );
        
        JPanel panel = new JPanel().applyGraphics(graphics2D -> {
            graphics2D.setColor(Color.CYAN);
            graphics2D.fillRect(10, 10, 100, 100);
        });

        frame.setContentPane(panel);
    }
```

If you want to make the rectangle also resizable you can use the 'GraphicScaleFunction':
```java
JPanel panel = new JPanel().applyGraphics((graphics2D, scaleX, scaleY) -> {
            graphics2D.setColor(Color.CYAN);
            graphics2D.fillRect(10, 10, (int) (100 * scaleX), (int) (100 * scaleY));
        });
```

Normal javax.swing components can also be used inside of SwingPlus and will get resized in their size aswell as their font!
More SwingPlus components will be implemented in future, so the use of default javax.swing components will be minimized.
```java
JPanel panel = new JPanel();

javax.swing.JButton button = new JButton("Click on me if you like SwingPlus :P");
button.setBounds(10, 100, 800, 200);
button.setFont(new Font("Arial", Font.BOLD, 36));

panel.add(button);
```

Incase you want to modify the underlaying swing component, read the documentation of the facade classes.
For JPanel you can use the method 'javax.swing.JPanel getSWING_PANEL()'
