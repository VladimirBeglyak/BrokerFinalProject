package by.broker.http.entity;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Admin {

    private Long id;
    private String email;
    private String password;
    private Role role;

}
