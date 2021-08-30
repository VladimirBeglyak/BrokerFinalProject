package by.broker.http.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Client {
    private Long id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private LocalDate birthday;
    private String passportId;
    private String password;
    private String email;
    private Role role;
    private Stock stock;
}
