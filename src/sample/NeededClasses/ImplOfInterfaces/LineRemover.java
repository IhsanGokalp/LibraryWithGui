package sample.NeededClasses.ImplOfInterfaces;

import sample.NeededClasses.Interfaces.RemovingTheLine;

import java.io.*;

public class LineRemover implements RemovingTheLine {
    @Override
    public void removeTheLine(String lineContent, String lineFromFile) throws IOException {
        File inputFile = new File(lineFromFile);
        File tempFile = new File("myTempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String lineToRemove = lineContent;
        String currentLine;
        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(lineToRemove)){

                continue;
            }
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        boolean successful = tempFile.renameTo(inputFile);
    }
}
