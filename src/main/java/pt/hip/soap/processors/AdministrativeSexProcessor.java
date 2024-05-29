package pt.hip.soap.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import pt.hip.soap.HL7ServiceImpl;

public class AdministrativeSexProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String exchangeMessage = exchange.getIn().getBody(String.class);
        String response = new HL7ServiceImpl().getPatientAdministrativeSex(exchangeMessage);

        exchange.getMessage().setHeader("Route-Name", "PatientAdministrativeSex");
        exchange.getMessage().setBody(response);
    }
}
