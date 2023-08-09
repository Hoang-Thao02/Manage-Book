import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class BookManager {
    // TODO: your code here
    // attribute books
    ArrayList<Book> bookList;

    public BookManager() {
        // TODO: your code here
        this.bookList = new ArrayList<Book>();
    }

    public ArrayList<Book> getBooks() {
        // TODO: your code here
        return bookList;
    }

    /**
     * update this.books by reading books from file books.txt
     */
    public void loadFromFile() throws Exception {
        Scanner sc = new Scanner(new File("books.txt"));
        System.out.println("Loading books...");
        while (sc.hasNext()) {
            String line = sc.nextLine();
            int id = Integer.parseInt(line.substring(0, 6).trim());
            String name = line.substring(6, 51).trim();
            double price = Double.parseDouble(line.substring(51).trim());
            this.bookList.add(new Book(id, name, price));
            // TODO: your code here
        }
    }

    /**
     * print books (one/line) in required format
     */
    public void printBooks(ArrayList<Book> bookList) {
        // TODO: your code here
        if (bookList.size() == 0) {
            System.out.println("(empty)");
        } else {
            System.out.println(String.format("%-5s %-45s %-10s", "ID", "Name", "Price"));
            for (Book book : this.bookList) {
                System.out.println(book.toString());
            }
        }
    }

    /**
     * if book.id is not duplicated, add book to this.books
     * return whether added or not
     */
    public boolean add(Book book) {
        // TODO: your code here
        if (getBookById(book.getId()) == null) {
            bookList.add(book);
            System.out.println("Added successfully.");
            return true;
        } else {
            System.out.println("Duplicated ID!");
        }
        return false;
    }


    /**
     * return book specified by id, null if not found
     */


    public Book getBookById(int id) {
        // TODO: your code here
        for (int i = 0; i < bookList.size(); i++) {
            Book book = bookList.get(i);
            if (book.id == id) {
                return book;
            }


        }
        return null;
    }

    /**
     * remove book from this.books
     */
    public void remove(Book book) {
        if (book != null) {
            bookList.remove(book);
            System.out.println("Deleted successfully.");
        } else {
            System.out.println("Invalid ID!");
        }
        // TODO: your code here
    }

    public void sortDescByPrice() {
        // TODO: your code
        for (int i = 0; i < bookList.size(); i++) {
            for (int j = i + 1; j < bookList.size(); j++) {
                if (bookList.get(j).price > bookList.get(i).price) {
                    Book moreExpensive = bookList.get(j);
                    Book cheaper = bookList.get(i);
                    bookList.set(j, cheaper);
                    bookList.set(i, moreExpensive);
                }
            }
        }
        if (bookList.isEmpty()) {
            System.out.println("(empty)");
        } else {
            System.out.println("After sorting: ");
            System.out.println(String.format("%-5s %-45s %-10s", "ID", "Name", "Price"));
            for (int i = 0; i < bookList.size(); i++) {
                System.out.println(bookList.get(i));

            }


        }


    }

    /**
     * return all books having name contains keyword (case in-sensitive)
     */
    public ArrayList<Book> searchByName(String keyword) {
        ArrayList<Book> matches = new ArrayList<>();
        for (Book book : bookList) {
            String nameOfBook = book.getName();
            if (nameOfBook.toLowerCase(Locale.ROOT).contains(keyword.toLowerCase(Locale.ROOT))) {
                matches.add(book);
            }
        }
        if (matches.isEmpty()) {
            System.out.println("(empty)");
        } else {
            System.out.println(String.format("%-5s %-45s %-10s", "ID", "Name", "Price"));
            for (Book book : matches) {
                System.out.println(book.toString());
            }
        }
        return matches;
    }


    /**
     * write this.books to file books.txt in required format
     */
    public void saveToFile() throws FileNotFoundException {
        // TODO: your code here
        PrintWriter writer = new PrintWriter("books.txt");
        for (Book book : bookList) {
            writer.println(book);
        }
        writer.close();

    }


}

