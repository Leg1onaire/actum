package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true) // Deserialization doesn't fail if received data have properties unspecified in the model.
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public abstract class AbstractModel implements Serializable {

}
