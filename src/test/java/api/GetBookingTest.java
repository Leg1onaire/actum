package api;

import models.BookingModel;
import models.BookingsModel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static enums.RequestMethod.GET;
import static models.BookingsModel.getRandomBookingId;
import static utils.api.RestUtils.sendRequest_assertExpected_responseCode;
import static utils.api.RestUtils.sendRequest_assertExpected_responseCode_schema;
import static utils.api.Services.Booking.BOOKINGS_URL;
import static utils.api.Services.Booking.BOOKING_URL;

public final class GetBookingTest {

    private int bookingId;

    @DataProvider
    private Object[][] invalidIds() {
        return new Object[][]{
                {0},
                {-4}
        };
    }

    @BeforeClass
    private void preconditionGetBookingTest() {
        bookingId = getRandomBookingId(sendRequest_assertExpected_responseCode(
                GET,
                BOOKINGS_URL,
                200).as(BookingsModel[].class));
    }

    @Test
    public void getBookingValidTest() {
        sendRequest_assertExpected_responseCode_schema(
                GET,
                BOOKING_URL(bookingId),
                200,
                BookingModel.class);
    }

    @Test(dataProvider = "invalidIds")
    public void getBookingInvalidIdTest(Integer bookingId) {
        sendRequest_assertExpected_responseCode(
                GET,
                BOOKING_URL(bookingId),
                404);
    }
}
