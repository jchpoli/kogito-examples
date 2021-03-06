/**
 *  Copyright 2020 Red Hat, Inc. and/or its affiliates.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.kie.pmml.kogito.quarkus.example;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class DMNRegressionTest {
    
    @Test
    public void testEvaluateRegressionDMN() {
        String inputData = "{\"fld1\":3.0, \"fld2\":2.0, \"fld3\":\"y\"}";
        given()
                .contentType(ContentType.JSON)
                .body(inputData)
                .when()
                .post("/TestRegressionDMN")
                .then()
                .statusCode(200)
                .body("RegressionModelBKM", is("function RegressionModelBKM( fld1, fld2, fld3 )"))
                .body("fld3", is("y"))
                .body("fld2", is(Float.valueOf("2")))
                .body("fld1", is(Float.valueOf("3")))
                .body("Decision", is(Float.valueOf("52.5")));
    }
}
