package sample.NeededClasses.ImplOfInterfaces;

import sample.NeededClasses.Interfaces.GetTheLastLine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LastLineReturner implements GetTheLastLine {
    @Override
    public String getLastLine(String path) throws IOException {
        String sCurrentLine;

        BufferedReader br = new BufferedReader(new FileReader(path));
        String lastLine = "";

        while ((sCurrentLine = br.readLine()) != null)
        {
            System.out.println(sCurrentLine);
            lastLine = sCurrentLine;
        }

        return lastLine;
    }
}
