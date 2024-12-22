package com.acme.demo_integration_test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static net.javacrumbs.jsonunit.spring.JsonUnitResultMatchers.json;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BasicApiTest {

    private static final String REQUEST_1 = """
                {
                  "type": "CREDIT",
                  "txTime": "2024-12-22T22:32:40Z",
                  "description": "Money :)",
                  "value": 100.00
                }
            """;

    private static final String REQUEST_2 = """
                {
                  "type": "CREDIT",
                  "txTime": "2023-10-22T22:32:40Z",
                  "description": "OLD Money :)",
                  "value": 300.00
                }
            """;

    /**
     * Note the "${json-unit.ignore}". JsonUnit library supports setting this string for any field in the json to ignore
     * it during the comparison. This is usually needed for id's and creation time fields.
     * */
    private static final String EXPECTED_RESPONSE = """
                [
                    {
                       "id": "${json-unit.ignore}",
                       "creationTime" : "${json-unit.ignore}",
                      "type": "CREDIT",
                      "txTime": "2024-12-22T22:32:40Z",
                      "description": "Money :)",
                      "value": 00.00
                    },
                    {
                       "id": "${json-unit.ignore}",
                       "creationTime" : "${json-unit.ignore}",
                      "type": "CREDIT",
                      "txTime": "2023-10-22T22:32:40Z",
                      "description": "OLD Money :)",
                      "value": 300.00
                    }
                ]
            """;

    @Autowired
    MockMvc mock;

    /**
     * A simple test case where we just add data to an empty database and read it back using the API's.
     * Due to its simplicity, it is useful to make sure all parts are working fine; mappers, generated SQL queries, business logic, etc.
     * If the expected request/response are small enough, you may just use text blocks instead of saving them into files.
     * */
    @Test
    void simpleHappyScenario() throws Exception {
        mock.perform(post("/tx").content(REQUEST_1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(json().isEqualTo(EXPECTED_RESPONSE));
    }
}
