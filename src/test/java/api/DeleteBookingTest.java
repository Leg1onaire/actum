package api;

import models.BookingResponseModel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.api.ApiTestBase;

import static enums.RequestMethod.DELETE;
import static enums.RequestMethod.POST;
import static models.BookingModel.randomBookingModel;
import static utils.api.RestUtils.sendRequest_assertExpected_responseCode;
import static utils.api.Services.Booking.BOOKINGS_URL;
import static utils.api.Services.Booking.BOOKING_URL;

public final class DeleteBookingTest extends ApiTestBase {

    private Integer bookingId;

    @BeforeClass
    private void preconditionDeleteBookingTest() {
        bookingId = sendRequest_assertExpected_responseCode(
                POST,
                BOOKINGS_URL,
                randomBookingModel(),
                200)
                .as(BookingResponseModel.class).getBookingId();
        System.out.println("BOOKING ID" + bookingId);
    }

    @Test
    public void deleteBookingValidTest() {
        sendRequest_assertExpected_responseCode(
                DELETE,
                BOOKING_URL(bookingId),
                200);
    }
}
