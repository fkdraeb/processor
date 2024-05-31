package pt.hip.soap;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class SoapMainProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String exchangeMessage = exchange.getIn().getBody(String.class);
        String isSoapMessageAccepted = new SoapServiceImpl().acceptMessage(exchangeMessage);

        //TODO Create all subscription and queue flow

        //exchange.getMessage().setBody(isSoapMessageAccepted);
        String customXmlResponse = "<response><message>Custom XML Response</message></response>";
        exchange.getMessage().setBody(customXmlResponse);

        // Set the custom XML as the response
        //exchange.getOut().setBody(customXmlResponse);
        exchange.getMessage().setHeader(Exchange.CONTENT_TYPE, "application/xml");
    }
}
