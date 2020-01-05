package sample.NeededClasses.ImplOfInterfaces.Admin;

import edu.duke.FileResource;
import sample.NeededClasses.Admin;
import sample.NeededClasses.ImplOfInterfaces.ObjectFormReturner;
import sample.NeededClasses.Interfaces.Admin.IsAdminValid;
import sample.NeededClasses.Interfaces.GetTheObject;

public class AdminValidator implements IsAdminValid {
    @Override
    public boolean adminValidation(String name, String pass) {
        FileResource fr = new FileResource("/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/AdminsData.txt");
        GetTheObject objectReturner = new ObjectFormReturner();
        for (String line: fr.lines()) {
            if (!line.equals("") && !line.equals(" ")){
                Admin currAdmin = objectReturner.getAdminFromString(line);
                if (currAdmin.getAdminName().equals(name))
                    return false;
            }
        }
        return true;
    }
}
