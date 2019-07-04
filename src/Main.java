import javax.swing.*;
import static javax.swing.SwingUtilities.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException
    {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        invokeLater(new Runnable(){
            @Override
            public void run()
            {
                MainForm mainForm = new MainForm();
            }
        });

    }
    }

