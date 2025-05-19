package LMS;



import jakarta.persistence.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sessionPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        
        // Create Authors
        Author author1 = new Author("J.K. Rowling");
        Author author2 = new Author("George Orwell");
        em.persist(author1);
        em.persist(author2);

        // Create Books
        Book book1 = new Book("Harry Potter", 5, author1);
        Book book2 = new Book("1984", 3, author2);
        Book book3 = new Book("Animal Farm", 2, author2);
        em.persist(book1);
        em.persist(book2);
        em.persist(book3);

        // Create Members
        Member member1 = new Member("Alice", "Computer Science");
        Member member2 = new Member("Bob", "Mechanical");
        em.persist(member1);
        em.persist(member2);

        // Borrow Books
        borrowBook(member1, book1);
        borrowBook(member2, book2);
        borrowBook(member1, book3);

        // Return Books (Simulate keeping them for 16 days)
        returnBook(member1, book1, 16);
        returnBook(member2, book2, 10); // Returned on time

        tx.commit();
        em.close();
        emf.close();
    }

    public static void borrowBook(Member member, Book book) {
        if (book.getQuantity() > 0) {
            book.setQuantity(book.getQuantity() - 1);
            member.getBooks().add(book);
            System.out.println(member.getName() + " borrowed " + book.getName());
        } else {
            System.out.println(book.getName() + " is out of stock!");
        }
    }

    public static void returnBook(Member member, Book book, int daysKept) {
        member.getBooks().remove(book);
        book.setQuantity(book.getQuantity() + 1);

        if (daysKept > 14) {
            long fine = (daysKept - 14) * 10;
            System.out.println(member.getName() + " returned " + book.getName() + " late. Fine: ₹" + fine);
        } else {
            System.out.println(member.getName() + " returned " + book.getName() + " on time.");
        }
    }
}