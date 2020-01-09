package sample.NeededClasses.ImplOfInterfaces;

import sample.NeededClasses.Admin;
import sample.NeededClasses.Book;
import sample.NeededClasses.ImplOfInterfaces.Student.BookSaver;
import sample.NeededClasses.Interfaces.GetTheObject;
import sample.NeededClasses.Interfaces.Student.SaveBooksOfStudent;
import sample.NeededClasses.Student;

import java.util.ArrayList;

public class ObjectFormReturner implements GetTheObject {

    private int phoneEndIdx;
    private int pageEndIdx;

    @Override
    public Student getStudentFromString(String studentInProgress) {
        int nameBeginIdx = studentInProgress.indexOf('\'')+1;
        int nameEndIdx = studentInProgress.indexOf('\'',nameBeginIdx);
        String name = studentInProgress.substring(nameBeginIdx,nameEndIdx);

        int surnameBeginIdx = studentInProgress.indexOf('\'',nameEndIdx+1)+1;
        int surnameEndIdx = studentInProgress.indexOf('\'',surnameBeginIdx);
        String surname = studentInProgress.substring(surnameBeginIdx,surnameEndIdx);

        int phoneNoBeginIdx = studentInProgress.indexOf('\'',surnameEndIdx+1)+1;
        int phoneNoEndIdx = studentInProgress.indexOf('\'',phoneNoBeginIdx);
        String phoneNo = studentInProgress.substring(phoneNoBeginIdx,phoneNoEndIdx);
        phoneEndIdx = phoneNoEndIdx;
        return new Student(name,surname,phoneNo);
    }

    @Override
    public Book getBookFromString(String bookInProgress) {
        int bookNameBeginIdx = bookInProgress.indexOf('\'') + 1;
        int bookNameEndIdx = bookInProgress.indexOf('\'', bookNameBeginIdx);
        String bookName = bookInProgress.substring(bookNameBeginIdx,bookNameEndIdx);

        int authorNameBeginIdx = bookInProgress.indexOf('\'',bookNameEndIdx+1) + 1;
        int authorNameEndIdx = bookInProgress.indexOf('\'', authorNameBeginIdx);
        String author = bookInProgress.substring(authorNameBeginIdx,authorNameEndIdx);

        int pageBeginIdx = bookInProgress.indexOf('\'',authorNameEndIdx+1) + 1;
        int pageEndIdx = bookInProgress.indexOf('\'', pageBeginIdx);
        String page = bookInProgress.substring(pageBeginIdx,pageEndIdx);
        this.pageEndIdx = pageEndIdx;
        return new Book(bookName, author, page);
    }

    @Override
    public int getPhoneEndIdx() {
        return phoneEndIdx;
    }

    @Override
    public int getPageEndIdx() {
        return pageEndIdx;
    }

    @Override
    public Admin getAdminFromString(String line) {
        int bookNameBeginIdx = line.indexOf('\'') + 1;
        int bookNameEndIdx = line.indexOf('\'', bookNameBeginIdx);
        String bookName = line.substring(bookNameBeginIdx,bookNameEndIdx);

        int authorNameBeginIdx = line.indexOf('\'',bookNameEndIdx+1) + 1;
        int authorNameEndIdx = line.indexOf('\'', authorNameBeginIdx);
        String author = line.substring(authorNameBeginIdx,authorNameEndIdx);
        return new Admin(bookName,author);
    }

    public Student getStudentWithBooks(String line) {
        Student std = getStudentFromString(line);
        SaveBooksOfStudent booksOfStudent = new BookSaver();
        String booksInStr = booksOfStudent.getBooksOfStudentAsStr(line);
        System.out.println(booksInStr);
        ArrayList<Book> books = booksOfStudent.getBooksFromStr(booksInStr);
        for (Book book : books) {
            std.addBook(book);
        }
        return std;
    }
}
