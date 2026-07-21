@Configuration: marks a class as a source of bean definitions for the Spring container.
@EnableAutoConfiguration: lets Spring Boot infer sensible defaults from the classpath and configuration.
@ComponentScan: finds Spring components under the current package tree so they can be registered as beans.

Property override chain: defaults < application.properties < environment variables < command-line arguments.
If application.properties sets server.port=8080 and the CLI passes --server.port=8081, the CLI wins because it is the last property source in the chain.
