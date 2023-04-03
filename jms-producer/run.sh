oc project artemis-a
oc delete configmap jms-sink-config
oc create configmap jms-sink-config --from-file configs/application.properties
kamel run --config configmap:jms-sink-config -d mvn:org.amqphub.quarkus:quarkus-qpid-jms --dev JmsProducer.java