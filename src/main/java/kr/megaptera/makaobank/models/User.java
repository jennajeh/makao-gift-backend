package kr.megaptera.makaobank.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.megaptera.makaobank.dtos.UserCreateDto;
import kr.megaptera.makaobank.dtos.UserDto;
import kr.megaptera.makaobank.exceptions.NotEnoughMoney;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Entity
@Table(name = "person")
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

    private static final Long INITIAL_AMOUNT = 5_000_000L;

    public User() {
    }

    public User(String name, String username) {
        this.name = name;
        this.username = username;
    }

    public User(Long id, String username, String name, String password, Long amount) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.amount = amount;
    }

    public static User fake() {
        return new User(1L, "test1", "전제나", "Test123!", 50_000L);
    }

    public Long id() {
        return id;
    }

    public String username() {
        return username;
    }

    public String name() {
        return name;
    }

    public String password() {
        return password;
    }

    public Long amount() {
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

    public UserDto toUserDto() {
        return new UserDto(id, username, amount);
    }

    public UserCreateDto toCreateDto() {
        return new UserCreateDto(id, username, name);
    }

    public void order(Product product, Integer quantity) {
        Long totalPrice = product.price() * quantity;

        if (totalPrice > this.amount) {
            throw new NotEnoughMoney();
        }

        this.amount -= product.price() * quantity;
    }
}
