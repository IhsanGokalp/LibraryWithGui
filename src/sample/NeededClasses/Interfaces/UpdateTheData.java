package sample.NeededClasses.Interfaces;

import java.io.IOException;

public interface UpdateTheData {
    void addLinetoSpecificData(String lineToAdd, String path) throws IOException;
}
