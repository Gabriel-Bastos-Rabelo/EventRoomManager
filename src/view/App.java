package view;

import java.time.DayOfWeek;
import java.time.LocalDate;

import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MenuInterface().setVisible(true);
            }
        });
    }


}
