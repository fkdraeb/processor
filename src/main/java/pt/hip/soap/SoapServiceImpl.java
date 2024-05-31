package pt.hip.soap;

import jakarta.jws.WebService;

//@WebService(endpointInterface = "pt.hip.soap.SoapService")
@WebService(endpointInterface = "com.connectors.ws.generated.SoapService",
        targetNamespace = "http://ws.connectors.connect.mirth.com/",
        serviceName = "SoapServiceImpl")
public class SoapServiceImpl implements SoapService {

    /*@Override
    public String acceptMessage(String soapMessage) {
        //TODO Here should be created the logic to accept the message



        return "The message was acknowledge";
    }*/

    @Override
    public String acceptMessage(String parameters) {
        // Implement your business logic here
        // You can replace this with the desired response structure
        return "<Response>" +
                "<GenericResponse>" +
                "<Error>" +
                "<ErrorID>200</ErrorID>" +
                "<ErrorMessage></ErrorMessage>" +
                "</Error>" +
                "<Response>OK</Response>" +
                "<Search></Search>" +
                "<ApplicationID>1234</ApplicationID>" +
                "</GenericResponse>" +
                "</Response>";
    }



}
