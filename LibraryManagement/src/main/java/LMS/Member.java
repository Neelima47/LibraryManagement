package LMS;



import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "members") // Keeping table name plural but class name singular
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String dept; 

    @ManyToMany
    @JoinTable(
        name = "member_books",  // Standardized join table name
        joinColumns = @JoinColumn(name = "member_id"),  // Corrected column name
        inverseJoinColumns = @JoinColumn(name = "book_id") 
    )
    private List<Book> books = new ArrayList<>();

    // Constructors
    public Member() {}

    public Member(String name, String dept) {
        this.name = name;
        this.dept = dept;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}