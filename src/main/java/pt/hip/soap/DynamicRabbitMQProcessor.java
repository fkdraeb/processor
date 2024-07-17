package pt.hip.soap;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class DynamicRabbitMQProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        String queueName = exchange.getMessage().getHeader("queue_name").toString();
        String bindingName = exchange.getMessage().getHeader("binding_name").toString();

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        AMQP.Queue.DeclareOk declareOk = channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, "amq.direct", bindingName);

    }
}
