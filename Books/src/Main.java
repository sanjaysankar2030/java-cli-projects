public class Main {

    public static void main(String[] args) {
        Book b1=new Book("1984","George Orwell");
        Book b2=new Book("Atomic Habits","Justin Gatehja");
        Library lib=new Library();
        lib.addBook(b1);
        lib.addBook(b2);
        b1.chechout();
        lib.displayAll();

    }
}
