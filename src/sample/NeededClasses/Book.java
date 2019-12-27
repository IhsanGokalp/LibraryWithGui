package sample.NeededClasses;


public class Book {
    private String name;
    private String author;
    private String noOfPages;

    public Book(String name, String author, String noOfPages) {
        this.name = name;
        this.author = author;
        this.noOfPages = noOfPages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNoOfPages() {
        return noOfPages;
    }

    public void setNoOfPages(String noOfPages) {
        this.noOfPages = noOfPages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", noOfPages='" + noOfPages + '\'' +
                '}';
    }
}
