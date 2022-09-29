package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static helpers.RandomDataGenerator.randomAlphaString;
import static helpers.RandomDataGenerator.randomIntFromTo;
import static models.BookingDatesModel.randomBookingDatesModel;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingModel extends BookingsModel{

    @JsonProperty("firstname")
    public String firstName;
    @JsonProperty("lastname")
    public String lastName;
    @JsonProperty("totalprice")
    public Integer totalPrice;
    @JsonProperty("depositpaid")
    public Boolean depositPaid;
    @JsonProperty("bookingdates")
    public BookingDatesModel bookingDates;
    @JsonProperty("additionalneeds")
    public String additionalNeeds;

    public BookingModel(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static BookingModel randomBookingModel(){
        return new BookingModel(
                randomAlphaString(10),
                randomAlphaString(10),
                randomIntFromTo(0, 100),
                true,
                randomBookingDatesModel(),
                randomAlphaString(10)
        );
    }

    public static BookingModel randomLightBookingModel(){
        return new BookingModel(
                randomAlphaString(10),
                randomAlphaString(10)
        );
    }
}
