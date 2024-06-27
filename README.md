# SwingPlus
Provides an enhanced resizable Swing-based GUIs while using a null layout.
SwingPlus acts as a facade to the javax.swing, streamlining
certain operations and providing a more intuitive interface for Swing GUI development

SwingPlus exist current at a very early state to represent the idea for possible future development.
It is orientated at NimbusUI.

### Gradle
Since this library is currently only available on GitHub packages rather than on the Maven-Central, 
you have to add some additional configuration to implement this library in your project.

Keep in mind that `GITHUB_USERNAME` and `GITHUB_TOKEN` are secured environment variables.
To declare environment variables on Windows, you have to use the command `setx <variable-name> <value>` in cmd.
If you don't know how to generate a GitHub-Token, view this:
[Generate tokens](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/managing-your-personal-access-tokens#creating-a-fine-grained-personal-access-token)
```kotlin
repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/MrMystery10-del/SwingPlus")
        credentials {
            username = System.getenv("GITHUB_USERNAME")
            password = System.getenv("GITHUB_TOKEN")
        }
    }
}

dependencies {
    implementation("com.swingplus:swingplus:0.0.1")
}
```

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
