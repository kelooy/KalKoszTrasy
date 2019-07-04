import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;

public class NewForm extends JFrame{

    private JPanel newPanel;
    private JButton wyjdzButton;
    private JButton wyczyscButton;
    private JButton zapiszButton;
    private JButton obliczButton;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JScrollPane scrollPane;
    private JTextArea textArea1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private Double tF1, tF2, tF4, tF5, fullCost, fullCostPerPassenger, fullCostPerPerson, costOfKilometer, fuelToRefuel;
    private int tF3;
    private Double alpha; // empiryczny wspolczynnik wzrostu spalania proporcjonalny do wzrostu liczby 80-kilogramowych pasazerow.
    private JLabel dateLabel;

    public NewForm()
    {
        add(newPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Okno główne - KalKoszTrasy");
        setSize(900,240);
        textArea1.setEditable(false);
        textArea1.setLineWrap(true);
        textArea1.setText("W puste pola wprowadź ręcznie rządane parametry i zatwierdź przyciskiem <OBLICZ>"
                + "\n" + "Separatorem dziesiętnym jest znak kropki.");
        dateLabel.setText(new Date().toString());


        setVisible(true);

        wyjdzButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        obliczButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
            tF1 = Double.parseDouble(textField1.getText());
            tF2 = Double.parseDouble(textField2.getText());
            tF3 = Integer.parseInt(textField3.getText());
            tF4 = Double.parseDouble(textField4.getText());
            tF5 = Double.parseDouble(textField5.getText());

            if(checkBox1.isSelected()) tF1 = 2 * tF1;
            fullCost = (Math.floor((tF1 * tF2 * tF4)) / 100) + tF5;

            if(checkBox2.isSelected())
            {
                alpha = 1 + 0.072 * tF3;
                if(tF3==0) alpha = 1.0;
                tF2 = tF2 * alpha;
                fullCost = Math.floor((tF1 * tF2 * tF4) / 100) + tF5;
            }

            fuelToRefuel = Math.floor((tF1 * tF2 )) / 100;
            if(tF3==0) fullCostPerPassenger = 0.0;
            else fullCostPerPassenger = fullCost / tF3;
            fullCostPerPerson = fullCost / (tF3+1);
            if(tF1==0) costOfKilometer = 0.0;
            else costOfKilometer = Math.floor(tF1 * tF2 * tF4) / tF1;

            textArea1.setText(
                    "KalKoszTrasy - zapis danych - " + new Date().toString() + "\n\n"+
                    "WPROWADZONE DANE: \n" +

                    "Dystans:  "+tF1+" km.\n"+"Spalanie:  "+tF2+" l. na 100km.\n"+
                    "Pasażerowie:  "+tF3+".\n"+ "Cena Paliwa:  "+tF4+" zł/l.\n"+ "Dodatkowe opłaty:  "+tF5+" zł.\n\n"+

                    "OBLICZONE DANE: \n"+

                    "Całkowity koszt przejazdu trasy wynosi: " + fullCost + " zł.\n" +
                    "Koszt całkowity w przeliczeniu na jednego pasażera to: " + fullCostPerPassenger + " zł. " + "/ na osobę to: " + fullCostPerPerson + " zł.\n"+
                    "Koszt przejazdu jednego kilometra (bez kosztów dodatkowych) wynosi: " + costOfKilometer + " gr. \n"+
                    "Ilosc paliwa potrzebnego do przebycia trasy to minimum: " + fuelToRefuel + " litrów.");
            }
        });

        wyczyscButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText("");
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                textField5.setText("");
            }
        });

        zapiszButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JFileChooser fileChooser = new JFileChooser();
                if(fileChooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
                {
                    File file = fileChooser.getSelectedFile();
                    try {
                        PrintWriter printWriter = new PrintWriter(file);
                        Scanner scanner = new Scanner(textArea1.getText());
                        while (scanner.hasNext()) printWriter.println(scanner.nextLine() + "\n");
                        printWriter.close();

                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                        System.err.println("Nie znaleziono pliku!");
                    }
                }
            }
        });
    }
}
