package api;

import models.BookingModel;
import models.BookingResponseModel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.api.ApiTestBase;

import static enums.RequestMethod.POST;
import static enums.RequestMethod.PUT;
import static models.BookingModel.randomBookingModel;
import static utils.api.RestUtils.sendRequest_assertExpected_responseCode;
import static utils.api.RestUtils.sendRequest_assertExpected_responseCode_schema;
import static utils.api.Services.Booking.BOOKINGS_URL;
import static utils.api.Services.Booking.BOOKING_URL;

public final class PutBookingTest extends ApiTestBase {

    private int bookingId;

    @BeforeClass
    private void preconditionPutBookingTest() {
        var payload = sendRequest_assertExpected_responseCode(
                POST,
                BOOKINGS_URL,
                randomBookingModel(),
                200)
                .as(BookingResponseModel.class);
        bookingId = payload.getBookingId();
    }

    @Test
    public void putBookingValidTest() {
        sendRequest_assertExpected_responseCode_schema(
                PUT,
                BOOKING_URL(bookingId),
                randomBookingModel(),
                200,
                BookingModel.class);
    }

    @Test
    public void putBookingInvalidTotalPriceTest() {
        var payload = randomBookingModel();
        payload.setTotalPrice(-4);

        sendRequest_assertExpected_responseCode(
                PUT,
                BOOKING_URL(bookingId),
                payload,
                400);
    }
}
