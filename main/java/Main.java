import domain.MarioException;
import ui.MainMenu;

import java.io.IOException;
import java.sql.*;

public class Main {

    // her laver vi sortering af ordre liste

    public static void main(String[] args) throws MarioException, IOException {
        MainMenu mainMenu = new MainMenu();
        mainMenu.mainMenuLoop();
    }

}
