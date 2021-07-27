package com.example.vehicles.api.v1.controller;

import com.example.vehicles.api.VersionPath;
import io.restassured.RestAssured;
import org.junit.Before;

public abstract class BaseControllerV1IT extends CoreControllerIT {

    @Before
    public final void setUpApiAccess() {
        RestAssured.basePath = VersionPath.PATH_V1;
    }
}
