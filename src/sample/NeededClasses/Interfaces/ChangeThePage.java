package sample.NeededClasses.Interfaces;

import javafx.event.ActionEvent;
import java.io.IOException;

public interface ChangeThePage {
    void changeThePageTo(String path, ActionEvent e) throws IOException;
}
