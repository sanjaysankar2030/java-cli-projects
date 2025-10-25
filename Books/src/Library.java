import java.util.ArrayList;
import java.util.List;

public class Library {
    List<Book> books = new ArrayList<>();

    public void addBook(Book b){
        books.add(b);
    }

    public void displayAll(){
        for(Book book : books){
            book.display();  // ✅ call method of the Book object
        }
    }
}
