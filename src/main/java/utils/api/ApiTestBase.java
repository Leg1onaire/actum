package utils.api;

import enums.RequestMethod;
import models.AuthModel;
import models.TokenModel;
import org.testng.annotations.BeforeTest;

import static utils.api.ApiUtils.setToken;
import static utils.api.RestUtils.sendRequest_assertExpected_responseCode;
import static utils.api.Services.AUTH_URL;

public abstract class ApiTestBase {

    @BeforeTest
    public void authorize() {
        setToken(
                sendRequest_assertExpected_responseCode(
                        RequestMethod.POST,
                        AUTH_URL,
                        new AuthModel(),
                        200).as(TokenModel.class).getToken());
    }
}
