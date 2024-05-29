package pt.hip.old.soap.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import pt.hip.old.soap.HL7ServiceImpl;

public class PatientIdProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String exchangeMessage = exchange.getIn().getBody(String.class);
        String response = new HL7ServiceImpl().getPatientId(exchangeMessage);

        exchange.getMessage().setHeader("Route-Name", "PatientId");
        exchange.getMessage().setBody(response);
    }
}
