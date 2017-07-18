package la.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import la.jpa.model.UserModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(nullable = true)
    private String name;

    @Column(nullable = false)
    private UserType type;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    public enum UserType {
        USER("user"), ADMIN("admin");
        private String value;

        private UserType(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public static UserType byValue(String val) {
            for (UserType value : values()) {
                if (value.value.equalsIgnoreCase(val)) {
                    return value;
                }
            }
            return null;
        }
    }

    public User(UserModel model) {
        //        this.email = model.getEmail();
        this.type = model.getType();
        //TODO PASSWORD ENCODING
        this.password = model.getPassword();
    }
}
