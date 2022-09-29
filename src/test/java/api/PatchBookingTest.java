package api;

import models.BookingResponseModel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.api.ApiTestBase;

import static enums.RequestMethod.PATCH;
import static enums.RequestMethod.POST;
import static models.BookingModel.randomBookingModel;
import static models.BookingModel.randomLightBookingModel;
import static utils.api.RestUtils.sendRequest_assertExpected_responseCode;
import static utils.api.Services.Booking.BOOKINGS_URL;
import static utils.api.Services.Booking.BOOKING_URL;

public final class PatchBookingTest extends ApiTestBase {

    private Integer bookingId;

    @BeforeClass
    private void preconditionPatchBookingTest() {
        bookingId = sendRequest_assertExpected_responseCode(
                POST,
                BOOKINGS_URL,
                randomBookingModel(),
                200)
                .as(BookingResponseModel.class).getBookingId();
    }

    @Test
    public void patchBookingValidTest() {
        sendRequest_assertExpected_responseCode(
                PATCH,
                BOOKING_URL(bookingId),
                randomLightBookingModel(),
                200);
    }
}
