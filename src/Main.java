import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        BookManager bookManager = new BookManager();
        ArrayList<Book> bookList = bookManager.getBooks();
        bookManager.loadFromFile();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("-----------------------------------");
            System.out.println("1. list all books");
            System.out.println("2. add a new book ");
            System.out.println("3. edit book ");
            System.out.println("4. delete a book ");
            System.out.println("5. search books by name ");
            System.out.println("6. sort books descending by price");
            System.out.println(" ");
            System.out.println("0. save & exit");
            System.out.println("-----------------------------------");
            System.out.print("Your option: ");
            int option = sc.nextInt();
            if (option >= 0 && option <= 6) {
                switch (option) {
                    case 1:
                        option1_list(bookManager);

                        break;
                    case 2:
                        option2_add(bookManager);
                        break;
                    case 3:
                        option3_edit(bookManager);
                        break;
                    case 4:
                        option4_delete(bookManager);
                        break;
                    case 5:
                        option5_searchByName(bookManager);
                        break;
                    case 6:
                        option6_sort(bookManager);
                        break;
                    case 0:
                        option0_saveAndExit(bookManager);
                        break;
                }
            } else {
                System.out.println("Invalid option!");
            }

        }
    }

    public static void option1_list(BookManager bm) {
        bm.printBooks(bm.bookList);
    }

    public static void option2_add(BookManager bm) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter book id: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Enter book name: ");
        String name = sc.nextLine();
        System.out.print("Enter book price: ");
        double price = Double.parseDouble(sc.nextLine());
        Book b = new Book(id, name, price);
        bm.add(b);
    }

    public static void option3_edit(BookManager bm) {
        Book book = new Book(1, "String", 13.5);
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter book id: ");
        int id = Integer.parseInt(sc.nextLine());
        if (bm.getBookById(id) == null) {
            System.out.println("Invalid ID!");
        } else {
            System.out.print("Enter book name: ");
            String name = sc.nextLine();
            System.out.print("Enter book price: ");
            double price = Double.parseDouble(sc.nextLine());
            Book b1 = new Book(id, name, price);
            System.out.println("Updated successfully.");
            bm.getBookById(id).setName(name);
            bm.getBookById(id).setPrice(price);
        }
    }


    public static void option4_delete(BookManager bm) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter book id: ");
        int id = sc.nextInt();
        if (bm.getBookById(id) != null) {
            bm.remove(bm.getBookById(id));
        } else {
            System.out.println("Invalid ID!");
        }
    }

    public static void option5_searchByName(BookManager bm) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter keyword: ");
        String keyword = sc.nextLine();
        bm.searchByName(keyword.toLowerCase(Locale.ROOT));
    }

    public static void option6_sort(BookManager bm) {
        bm.sortDescByPrice();

    }

    public static void option0_saveAndExit(BookManager bm) throws FileNotFoundException {
        bm.saveToFile();
        System.out.println("Saving to file...");
        System.out.println("Bye!");
        System.exit(0);
    }


}



