package com.acme.demo_integration_test;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static com.acme.demo_integration_test.Utils.getResource;
import static net.javacrumbs.jsonunit.spring.JsonUnitResultMatchers.json;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UsingSqlScriptTest {

    @Autowired
    MockMvc mock;


    /**
     * @ Sql is a spring test annotation that is used to run a sql script before or after a test.
     * It can be used both on method level or class level. In case of class leve, it will be applied to every test method.
     *
     * In this test, we are checking if our API filters are working. So, we add both data that will fit the filters criteria,
     * and other data that don't.
     * If our filters works correctly, we should get exactly the data in range created by the filters. For example, we are filtering
     * by transaction time, we add 4 transactions , on for each year from 2021 till 2024, because our filters are from 2022-01-01 to
     * 2023-12-31, we got only two transactions in the result.
     *
     * The same Test data can be reused across multiple test. But be careful when doing this. If a test data script is changed
     * for a new test, it can affect the results of the older tests. In such case following the open-close principle is better,
     * and we should just copy the script and extend it for the new test.
     * */
    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts= "/sql/sample-data-1.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts= "/sql/erase-test-data.sql")
    void usingSampleData() throws Exception {
        //here we read the expected json response from a file. This is usually useful if the expected response is too
        // large to fit in our test class, or making the code harder to read.
        // having the response in a json file also allow use to make use of IDE linting features, making handling the json easier.
        var expectedResponse = getResource("json/responses/expected-tx-1.json");

        mock.perform(get("/tx?from={0}&to={1}", "2022-01-01", "2023-12-31")
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(json().isEqualTo(expectedResponse));
    }
}
