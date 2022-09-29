package utils.api;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Services {

    private static final String API_BASE_URL = "https://restful-booker.herokuapp.com";

    public static final String AUTH_URL = API_BASE_URL + "/auth";

    public static class Booking {
        public static final String BOOKINGS_URL = API_BASE_URL + "/booking";

        public static String BOOKING_URL(int bookingId) {
            return BOOKINGS_URL + "/" + bookingId;
        }
    }
}
