package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AuthModel extends AbstractModel {

    @JsonProperty("username")
    private final String USERNAME = "admin";
    @JsonProperty("password")
    private final String PASSWORD = "password123";
}
