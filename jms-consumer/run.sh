oc project artemis-b
oc delete configmap jms-source-config
oc create configmap jms-source-config --from-file configs/application.properties
kamel run --config configmap:jms-source-config -d mvn:org.amqphub.quarkus:quarkus-qpid-jms JmsConsumer.java
