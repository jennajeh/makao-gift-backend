package kr.megaptera.makaobank.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Entity
@Table(name = "PERSON")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String name;
    private String password;
    private Long amount;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private static final Long INITIAL_AMOUNT = 50_000L;

    public User() {
    }

    public User(Long id, String username, String name, String password, Long amount) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.amount = amount;
    }

    public static User fake() {
        return new User(1L, "Jenna1234!", "전제나", "Asdf1234!", 50_000L);
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Long getAmount() {
        return amount;
    }

    public void setInitialAmount() {
        this.amount = INITIAL_AMOUNT;
    }

    public void changePassword(String password, PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    public boolean authenticate(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, this.password);
    }
}
