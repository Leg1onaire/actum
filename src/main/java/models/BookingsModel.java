package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static java.util.Arrays.stream;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingsModel extends AbstractModel {

    @JsonProperty("bookingid")
    private Integer bookingId;

    public static Integer getRandomBookingId(BookingsModel[] list) {
        return stream(list)
                .findFirst().get().getBookingId();
    }
}
