package sample.NeededClasses.ImplOfInterfaces;

import sample.NeededClasses.Interfaces.UpdateTheData;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DataUpdater implements UpdateTheData {
    @Override
    public void addLinetoSpecificData(String lineToAdd, String path) throws IOException {
        FileWriter myWriter = new FileWriter(path, true);
        BufferedWriter bfWriter = new BufferedWriter(myWriter);
        bfWriter.write(lineToAdd);
        bfWriter.newLine();
        bfWriter.close();
    }
}
