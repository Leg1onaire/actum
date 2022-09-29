package api;

import enums.RequestMethod;
import models.BookingsModel;
import org.testng.annotations.Test;

import static utils.api.RestUtils.sendRequest_assertExpected_responseCode_schema;
import static utils.api.Services.Booking.BOOKINGS_URL;

public final class GetBookingIdsTest {

    @Test
    public void getBookingIdsTest() {
        sendRequest_assertExpected_responseCode_schema(
                RequestMethod.GET,
                BOOKINGS_URL,
                200,
                BookingsModel[].class);
    }
}
