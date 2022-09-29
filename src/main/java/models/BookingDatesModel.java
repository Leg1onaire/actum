package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static helpers.RandomDataGenerator.convertDate;
import static helpers.RandomDataGenerator.randomDate;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDatesModel extends AbstractModel {

    @JsonProperty("checkin")
    public String checkIn;
    @JsonProperty("checkout")
    public String checkOut;

    public static BookingDatesModel randomBookingDatesModel() {
        return new BookingDatesModel(
                convertDate(randomDate("2020-10-10", "2020-10-20")),
                convertDate(randomDate("2020-10-20", "2020-10-30"))
        );
    }
}
