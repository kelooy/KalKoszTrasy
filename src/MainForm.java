import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame {

    private JButton dalejButton;
    private JButton wyjdzButton;
    private JPanel mainPanel;
    private JPanel logoPanel;
    private JLabel logoLabel;

    public MainForm()
    {
        add(mainPanel);
        logoLabel.setIcon(new ImageIcon(getClass().getResource("logo.png")));
        validate();

        setTitle("Okno startowe - KalKoszTrasy");
        setSize(700,430);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        wyjdzButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });

        dalejButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
                NewForm newForm = new NewForm();
            }
        });
    }
}
