// camel-k: language=java
// camel-k: dependency=camel:camel-quarkus-activemq

import org.apache.camel.builder.RouteBuilder;

public class JmsConsumer extends RouteBuilder {
  @Override
  public void configure() throws Exception {

      from("activemq:{{jms.destinationType}}:{{jms.destinationName}}")
        .log("Retrieving person ${body}");
  }
}
