package utils.api;

import com.github.dzieciou.testing.curl.CurlRestAssuredConfigFactory;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.java.Log;
import models.AbstractModel;
import org.apache.commons.io.output.WriterOutputStream;
import org.testng.internal.Utils;
import enums.RequestMethod;

import javax.annotation.Nullable;
import java.io.PrintStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static org.testng.Assert.fail;

@Log
public abstract class ApiUtils {

    private static final String CONTENT_TYPE_JSON = "application/json";
    private static String token;

    private static final ThreadLocal<StringWriter> requestWriter = new ThreadLocal<>();
    private static final ThreadLocal<StringWriter> responseWriter = new ThreadLocal<>();
    private static final ThreadLocal<PrintStream> requestCapture = new ThreadLocal<>();
    private static final ThreadLocal<PrintStream> responseCapture = new ThreadLocal<>();

    public static void setToken(String token) {
        ApiUtils.token = token;
    }

    public static <M> Response sendRequest(RequestMethod method,
                                           String specificResourceUri,
                                           @Nullable Map<String, ?> queryParams,
                                           @Nullable M payload,
                                           @Nullable Map<String, String> headers) {

        setRequestAndResponseStreamWriters();

        RequestSpecification basicRequestSpec = getBasicRequestSpec(headers);
        if (queryParams != null) {
            basicRequestSpec = basicRequestSpec.queryParams(queryParams);
        }
        basicRequestSpec = basicRequestSpec.body(isNull(payload) ? "" : payload);

        Response response;
        switch (method) {
            case GET:
                response = basicRequestSpec.get(specificResourceUri);
                break;
            case PUT:
                response = basicRequestSpec.put(specificResourceUri);
                break;
            case POST:
                response = basicRequestSpec.post(specificResourceUri);
                break;
            case DELETE:
                response = basicRequestSpec.delete(specificResourceUri);
                break;
            case PATCH:
                response = basicRequestSpec.patch(specificResourceUri);
                break;
            case HEAD:
                response = basicRequestSpec.head(specificResourceUri);
                break;
            case OPTIONS:
                response = basicRequestSpec.options(specificResourceUri);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + method);
        }

        log.info(getRequestAndResponse());

        return response;
    }

    public static <M, A> Response sendRequest_assertExpected_responseCode_schema(RequestMethod method,
                                                                                 String specificResourceUri,
                                                                                 @Nullable Map<String, ?> queryParams,
                                                                                 @Nullable M payload,
                                                                                 @Nullable Map<String, String> headers,
                                                                                 int expectedStatusCode,
                                                                                 Class<A> expectedResponseModel) {
        assertExpectedComponentTypeOfModel(expectedResponseModel);

        Response response = sendRequest_assertExpected_responseCode(method, specificResourceUri, queryParams, payload, headers, expectedStatusCode);
        try {
            response.as(expectedResponseModel);
        } catch (Exception e) {
            fail(format(
                    "Is expected that response matches the model [%s], but some inconsistencies were found. Check the following exception message:\n\n" +
                            "EXCEPTION MESSAGE:\n%s\n" +
                            "REQUEST:\n%s\n" +
                            "RESPONSE:\n%s\n" +
                            "EXCEPTION STACKTRACE:\n%s\n",
                    expectedResponseModel.getSimpleName(),
                    e.getLocalizedMessage(),
                    getRequest(),
                    getResponse(),
                    Arrays.toString(e.getStackTrace())));
        }
        return response;
    }

    public static <M> Response sendRequest_assertExpected_responseCode(RequestMethod method,
                                                                       String specificResourceUri,
                                                                       @Nullable Map<String, ?> queryParams,
                                                                       @Nullable M payload,
                                                                       @Nullable Map<String, String> headers,
                                                                       int expectedStatusCode) {

        Response response = sendRequest(method, specificResourceUri, queryParams, payload, headers);

        int actualStatusCode = response.getStatusCode();
        if (actualStatusCode != expectedStatusCode) {
            fail((format(
                    "Is expected that status [%s] is returned, but actual status is [%s] \n\n" +
                            "REQUEST:\n%s\n" +
                            "RESPONSE:\n%s\n",
                    expectedStatusCode, actualStatusCode, getRequest(), getResponse())));
        }

        return response;
    }

    public static String getRequestAndResponse() {
        return getRequest() + getResponse();
    }

    private static <A> void assertExpectedComponentTypeOfModel(Class<A> expectedResponseModel) {
        try {
            (expectedResponseModel.isArray() ? expectedResponseModel.getComponentType() : expectedResponseModel).asSubclass(AbstractModel.class);
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    format("Is expected, that parameter 'expectedResponseModel' is the subclass or array of subclasses of the AbstractModel.class but it is " +
                            "not. Check if your model class [%s] for mapping of the response extends the AbstractModel.class.", expectedResponseModel.getSimpleName()));
        }
    }

    private static String getRequest() {
        return format("REQUEST:%n%n%s%n%n", requestWriter.get().toString());
    }

    private static String getResponse() {
        String response = responseWriter.get().toString();
        List<String> responseRows = Arrays.asList(response.split("\n"));
        boolean isShortened = false;
        if (responseRows.size() > 100) {
            isShortened = true;
            response = Utils.join(responseRows.subList(0, 100), System.lineSeparator());
        }

        return format("RESPONSE%s:%n%n%s%n%n", isShortened ? " (...first 100 rows...)" : "", response);
    }

    private static void setRequestAndResponseStreamWriters() {
        requestWriter.set(new StringWriter());
        requestCapture.set(getPrintStream(requestWriter.get()));
        responseWriter.set(new StringWriter());
        responseCapture.set(getPrintStream(responseWriter.get()));
    }

    private static RequestSpecification getBasicRequestSpec(Map<String, String> headers) {
        RequestSpecification requestSpecification = given()
                .filter(new RequestLoggingFilter(requestCapture.get()))
                .filter(new ResponseLoggingFilter(responseCapture.get()))
                .contentType(CONTENT_TYPE_JSON)
                .accept(CONTENT_TYPE_JSON)
                .cookie("token", token)
                .log().all();


        requestSpecification.config(CurlRestAssuredConfigFactory.updateConfig(RestAssured.config()));


        return headers == null ? requestSpecification : requestSpecification.headers(headers);
    }

    private static PrintStream getPrintStream(StringWriter writer) {
        return new PrintStream(new WriterOutputStream(writer, StandardCharsets.UTF_8), true);
    }
}

