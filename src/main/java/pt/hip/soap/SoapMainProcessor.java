package pt.hip.soap;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import pt.hip.soap.response.ErrorResponse;
import pt.hip.soap.response.GenericResponse;
import pt.hip.soap.response.Response;

import java.io.StringWriter;

public class SoapMainProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        /*String exchangeMessage = exchange.getIn().getBody(String.class);
        String isSoapMessageAccepted = new SoapServiceImpl().acceptMessage(exchangeMessage);

        //TODO Create all subscription and queue flow

        //exchange.getMessage().setBody(isSoapMessageAccepted);
        String customXmlResponse = "<response><message>Custom XML Response</message></response>";
        exchange.getMessage().setBody(customXmlResponse);


        exchange.getMessage().setHeader(Exchange.CONTENT_TYPE, "application/xml");*/
        String exchangeMessage = exchange.getIn().getBody(String.class);
        String isSoapMessageAccepted = new SoapServiceImpl().acceptMessage(exchangeMessage);

        // Create the response object
        Response response = new Response();
        GenericResponse genericResponse = new GenericResponse();
        ErrorResponse error = new ErrorResponse();

        // Set values (empty for now)
        error.setErrorID("");
        error.setErrorMessage("");
        genericResponse.setError(error);
        genericResponse.setResponse("");
        genericResponse.setSearch("");
        genericResponse.setApplicationID("");
        response.setGenericResponse(genericResponse);

        // Marshal the response object to XML
        JAXBContext jaxbContext = JAXBContext.newInstance(Response.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter sw = new StringWriter();
        marshaller.marshal(response, sw);

        String customXmlResponse = sw.toString();
        exchange.getMessage().setBody(customXmlResponse);
        exchange.getMessage().setHeader(Exchange.CONTENT_TYPE, "application/xml");
    }
}
