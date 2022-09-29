package utils.api;

import enums.RequestMethod;
import io.restassured.response.Response;
import models.AbstractModel;

import javax.annotation.Nullable;

import java.util.Map;

public abstract class RestUtils extends ApiUtils {

    /**
     * Base method, overload this one
     */
    public static <M> Response sendRequest(RequestMethod method,
                                           String specificResourceUri,
                                           @Nullable Map<String, ?> queryParams,
                                           @Nullable M payload,
                                           @Nullable Map<String, String> headers) {

        return ApiUtils.sendRequest(method, specificResourceUri, queryParams, payload, headers);
    }

    /**
     * Base method, overload this one
     */
    public static <M> Response sendRequest_assertExpected_responseCode(RequestMethod method,
                                                                       String specificResourceUri,
                                                                       @Nullable Map<String, ?> queryParams,
                                                                       @Nullable M payload,
                                                                       @Nullable Map<String, String> headers,
                                                                       int expectedStatusCode) {
        return ApiUtils.sendRequest_assertExpected_responseCode(method, specificResourceUri, queryParams, payload, headers, expectedStatusCode);
    }

    public static <M> Response sendRequest_assertExpected_responseCode(RequestMethod method,
                                                                       String specificResourceUri,
                                                                       @Nullable M payload,
                                                                       int expectedStatusCode) {
        return sendRequest_assertExpected_responseCode(method, specificResourceUri, null, payload, null, expectedStatusCode);
    }

    public static Response sendRequest_assertExpected_responseCode(RequestMethod method,
                                                                   String specificResourceUri,
                                                                   int expectedStatusCode) {
        return sendRequest_assertExpected_responseCode(method, specificResourceUri, null, null, null, expectedStatusCode);
    }

    /**
     * Base method, overload this one
     */
    public static <M, A> Response sendRequest_assertExpected_responseCode_schema(RequestMethod method,
                                                                                 String specificResourceUri,
                                                                                 @Nullable Map<String, ?> queryParams,
                                                                                 @Nullable M payload,
                                                                                 @Nullable Map<String, String> headers,
                                                                                 int expectedStatusCode,
                                                                                 Class<A> expectedResponseModel) {
        return ApiUtils.sendRequest_assertExpected_responseCode_schema(method, specificResourceUri, queryParams, payload, headers, expectedStatusCode, expectedResponseModel);
    }

    public static <A> Response sendRequest_assertExpected_responseCode_schema(RequestMethod method,
                                                                              String specificResourceUri,
                                                                              int expectedStatusCode,
                                                                              Class<A> expectedResponseModel) {
        return sendRequest_assertExpected_responseCode_schema(method, specificResourceUri, null, null, null, expectedStatusCode, expectedResponseModel);
    }

    public static <M, A> Response sendRequest_assertExpected_responseCode_schema(RequestMethod method,
                                                                                 String specificResourceUri,
                                                                                 M payload,
                                                                                 int expectedStatusCode,
                                                                                 Class<A> expectedResponseModel) {
        return sendRequest_assertExpected_responseCode_schema(method, specificResourceUri, null, payload, null, expectedStatusCode, expectedResponseModel);
    }
}
