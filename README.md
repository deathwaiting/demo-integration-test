# A Demo for using integration testing techniques with spring boot

In this demo we show different examples for different techniques for integrations testing using spring boot.
Please note that this is an opinionated approaches, and are by no mean exclusive. These are just ideas and tools I found useful for providing higher quality assurance.

In this test, we assume simple API for monthly expenses. It saves and returns expenses details in addition to converting savings value to USD using external API.
The service will also periodically send reminder emails about a saving target if not reached.

With integration tests, we will test the functionality of this service, while using the following:
- Using spring MockMVC for integration tests
- H2 database as in-memory database during testing.
- Using spring @Sql annotations to load and delete test data.
- Comparing JSON Responses with expected responses saved in files using JSON unit.
- Wiremock for mocking an external API
- Mockito for mocking file retrieval from S3
- Awaitality for time based functionalities like periodic tasks 