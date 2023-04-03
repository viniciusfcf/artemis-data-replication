// camel-k: language=java
// camel-k: dependency=camel:camel-quarkus-jms
// camel-k: dependency=camel:camel-quarkus-timer property=period=1000
// camel-k: dependency=mvn:com.github.javafaker:javafaker:1.0.2

import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import com.github.javafaker.Faker;

public class JmsProducer extends RouteBuilder {

  @Override
  public void configure() throws Exception {

      from("timer:{{period}}")
        .bean(this, "generateFakePerson()")
        .to("log:info")
        .to("jms:{{jms.destinationType}}:{{jms.destinationName}}");

  }

  public String generateFakePerson() {
    Faker faker = new Faker();

    return faker.name().fullName() + " lives on " + faker.address().streetAddress();
  }
}
