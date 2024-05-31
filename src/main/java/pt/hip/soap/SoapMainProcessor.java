package pt.hip.soap;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
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

        Response response = new Response();
        GenericResponse genericResponse = new GenericResponse();
        ErrorResponse error = new ErrorResponse();

        String exchangeMessage = exchange.getIn().getBody(String.class);

        //TODO Create all subscription and queue flow
        //String isSoapMessageAccepted = new SoapServiceImpl().acceptMessage(exchangeMessage);

        error.setErrorID("N/A");
        error.setErrorMessage("N/A");
        genericResponse.setError(error);
        genericResponse.setResponse("OK");
        genericResponse.setSearch("N/A");
        genericResponse.setApplicationID("123456");
        response.setGenericResponse(genericResponse);

        String xmlResponse = responseBuilder(response);

        exchange.getMessage().setBody(xmlResponse);
        exchange.getMessage().setHeader(Exchange.CONTENT_TYPE, "application/xml");
    }

    private String responseBuilder(Response response) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Response.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter sw = new StringWriter();
        marshaller.marshal(response, sw);

        return sw.toString();
    }
}
