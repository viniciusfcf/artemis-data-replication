// camel-k: language=java
// camel-k: dependency=camel:camel-quarkus-activemq
// camel-k: dependency=camel:camel-quarkus-timer
// camel-k: dependency=mvn:com.github.javafaker:javafaker:1.0.2

import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;


import com.github.javafaker.Faker;

public class JmsProducer extends RouteBuilder {

  private final AtomicInteger counter = new AtomicInteger();

  @Override
  public void configure() throws Exception {

      from("timer:personGenerator")
        .bean(this, "generateFakePerson()")
        .marshal().json()
        .log("sending Person: ${body}")
        .to("activemq:{{jms.destinationType}}:{{jms.destinationName}}");

  }

  public Person generateFakePerson() {
    Faker faker = new Faker(new Locale("pt-BR"));
    Person p = new Person();
    p.id = counter.getAndIncrement();
    p.name = faker.name().fullName();

    return p;
  }

  class Person {
    public Integer id;
    public String name;
  }
}
