package helpers;

import lombok.extern.java.Log;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.apache.commons.lang3.time.DateUtils.addDays;

@Log
public class RandomDataGenerator {

    private static final Random RANDOM = new Random();

    /**
     * Returns random alphabetic string of specified length.
     *
     * @param length length of the output string
     * @return String in specified range
     */
    public static String randomAlphaString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    public static String randomEmail(){
        return randomAlphaString(10) + "@gg.wp";
    }

    /**
     * Generates random date in a range [from; to]
     *
     * @param from in format "yyyy-MM-dd"
     * @param to   in format "yyyy-MM-dd"
     * @return Date
     */
    public static Date randomDate(String from, String to) {

        var dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDate = null;
        Date toDate = null;

        try {
            fromDate = dateFormat.parse(from);
        } catch (ParseException e) {
            log.info("Incorrect date format: " + from);
            e.printStackTrace();
        }

        try {
            toDate = dateFormat.parse(to);
        } catch (ParseException e) {
            log.info("Incorrect date format: " + to);
            e.printStackTrace();
        }

        var difference = toDate.getTime() - fromDate.getTime();
        if (difference > 0) {
            var days = (int) TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
            return addDays(fromDate, randomIntUpTo(days));
        } else {
            log.info("Incorrect date range.");
            return null;
        }
    }

    /**
     * Return date in "yyyy-MM-dd" format
     *
     * @param inputDate date to convert
     * @return String date in "yyyy-MM-dd" format
     */
    public static String convertDate(Date inputDate) {
        return new SimpleDateFormat("yyyy-MM-dd").format(inputDate);
    }

    /**
     * Returns random integer between the boundaries.
     *
     * @param bottom from
     * @param top    to
     * @return random int
     */
    public static Integer randomIntFromTo(int bottom, int top) {
        return ThreadLocalRandom.current().nextInt(bottom, top);
    }

    /**
     * Returns random integer between 0 and specified (positive) int.
     *
     * @param top (positive) int
     * @return random int
     */
    public static int randomIntUpTo(int top) {
        return randomIntFromTo(0, top);
    }

    /**
     * @param enumClass to get random value
     * @param ignoreValues excluded values from filtration
     * @return random Enum constant
     */
    @SafeVarargs
    public static <E extends Enum<?>> E getRandomEnum(Class<E> enumClass, E... ignoreValues) {
        E randomEnum;
        do {
            randomEnum = enumClass.getEnumConstants()[RANDOM.nextInt(enumClass.getEnumConstants().length)];
        } while (asList(ignoreValues).contains(randomEnum));

        return randomEnum;
    }

    public static Integer randomFiveDigits() {
        return randomIntFromTo(10000, 99999);
    }

    /**
     * @return random phone number in format (###) ####-###
     */
    public static String randomPhoneNumber() {
        return String.format("(%s) %s-%s", randomNumeric(3), randomNumeric(4), randomNumeric(3));
    }
}
