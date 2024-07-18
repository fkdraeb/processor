package pt.hip;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import io.quarkiverse.rabbitmqclient.RabbitMQClient;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import com.rabbitmq.client.*;


public class DynamicRabbitMQProcessor implements Processor {

    @Inject
    RabbitMQClient rabbitMQClient;
    private Channel channel;

    @Override
    public void process(Exchange exchange) throws Exception {

        String queueName = exchange.getMessage().getHeader("queue_name").toString();
        String bindingName = exchange.getMessage().getHeader("binding_name").toString();

        Connection connection = rabbitMQClient.connect();
        channel = connection.createChannel();

        AMQP.Queue.DeclareOk declareOk = channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, "amq.direct", bindingName);
        channel.exchangeDeclare("hip.tcp",BuiltinExchangeType.FANOUT.getType(), true);
    }
}
