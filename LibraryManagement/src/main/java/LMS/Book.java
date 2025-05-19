package LMS;



import java.util.ArrayList;
import java.util.List;  

import jakarta.persistence.*;

@Entity
@Table(name="books")
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "author_id") 
    private Author author;

    @ManyToMany(mappedBy = "books")
    private List<Member> members = new ArrayList<>(); 

    
    public Book() {}

    public Book(String name, int quantity, Author author) {
        this.name = name;
        this.quantity = quantity;
        this.author = author;
    }

    
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Member> getMembers() {  
        return members;
    }

    public void setMembers(List<Member> members) {  
        this.members = members;
    }
}