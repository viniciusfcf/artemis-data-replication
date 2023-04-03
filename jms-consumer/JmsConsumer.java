// camel-k: language=java
// camel-k: dependency=camel:camel-quarkus-jms

import org.apache.camel.builder.RouteBuilder;

public class JmsConsumer extends RouteBuilder {
  @Override
  public void configure() throws Exception {

      from("jms:{{jms.destinationType}}:{{jms.destinationName}}")
        .to("log:info");
  }
}
