package la.jpa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import la.jpa.domain.User.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private Long id;
    private String email;
    private String password;
    private UserType type;
}
