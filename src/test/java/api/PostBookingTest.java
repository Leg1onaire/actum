package api;

import models.BookingResponseModel;
import org.testng.annotations.Test;

import static enums.RequestMethod.POST;
import static models.BookingModel.randomBookingModel;
import static utils.api.RestUtils.sendRequest_assertExpected_responseCode;
import static utils.api.RestUtils.sendRequest_assertExpected_responseCode_schema;
import static utils.api.Services.Booking.BOOKINGS_URL;

public final class PostBookingTest {

    @Test
    public void postBookingValidTest() {
        sendRequest_assertExpected_responseCode_schema(
                POST,
                BOOKINGS_URL,
                randomBookingModel(),
                200,
                BookingResponseModel.class);
    }

    @Test
    public void postBookingInvalidTotalPriceTest() {
        var payload = randomBookingModel();
        payload.setTotalPrice(-4);

        sendRequest_assertExpected_responseCode(
                POST,
                BOOKINGS_URL,
                payload,
                400);
    }
}
