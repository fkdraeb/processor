package pt.hip.soap;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import pt.hip.soap.response.GenericResponse;
import pt.hip.soap.response.Response;
import pt.hip.soap.response.Error;


import java.io.StringWriter;

public class SoapMainProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String responseMessage = null;
        String exchangeMessage = exchange.getIn().getBody(String.class);
        boolean isSoapMessageAccepted = new SoapServiceImpl().acceptMessage(exchangeMessage);

        //TODO Create all subscription and queue flow


        if (isSoapMessageAccepted) {
            responseMessage = createGenericResponse("200", null, "1234", "OK");
        } else {
            responseMessage = createGenericResponse("400", null, "1234", "NOK");
        }

        exchange.getMessage().setBody(responseMessage);
    }

    public static String createGenericResponse(String errorId, String errorMessage, String applicationId, String responseCode) {
        Response response = new Response();
        GenericResponse genericResponse = new GenericResponse();

        Error error = new Error();
        if (errorId != null) {
            error.setErrorId(errorId);
        }
        if (errorMessage != null) {
            error.setErrorMessage(errorMessage);
        }
        genericResponse.setError(error);

        if (responseCode != null) {
            genericResponse.setResponse(responseCode);
        }
        if (applicationId != null) {
            genericResponse.setApplicationId(applicationId);
        }

        response.setGenericResponse(genericResponse);

        try {
            JAXBContext context = JAXBContext.newInstance(Response.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter sw = new StringWriter();
            marshaller.marshal(response, sw);

            return sw.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
