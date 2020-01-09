package sample.NeededClasses.Interfaces;

import sample.NeededClasses.Admin;
import sample.NeededClasses.Book;
import sample.NeededClasses.Student;

public interface GetTheObject {
    Student getStudentFromString(String studentInStr);
    Book getBookFromString(String bookInStr);
    int getPhoneEndIdx();
    int getPageEndIdx();
    Admin getAdminFromString(String line);
    Student getStudentWithBooks(String line);
}
