package pt.hip.soap;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class SoapMainProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String exchangeMessage = exchange.getIn().getBody(String.class);
        String isSoapMessageAccepted = new SoapServiceImpl().acceptMessage(exchangeMessage);

        //TODO Create all subscription and queue flow

        exchange.getMessage().setBody(isSoapMessageAccepted);
    }
}
