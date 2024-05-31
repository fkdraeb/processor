package pt.hip.soap;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;

@WebService(targetNamespace = "http://ws.connectors.connect.mirth.com/", name = "SoapService")
public interface SoapService {

    @WebMethod(action = "http://ws.connectors.connect.mirth.com/DefaultAcceptMessage/acceptMessageRequest")
    @RequestWrapper(localName = "acceptMessage", targetNamespace = "http://ws.connectors.connect.mirth.com/", className = "com.connectors.ws.generated.AcceptMessage")
    @ResponseWrapper(localName = "acceptMessageResponse", targetNamespace = "http://ws.connectors.connect.mirth.com/", className = "com.connectors.ws.generated.AcceptMessageResponse")
    @WebResult(name = "return", targetNamespace = "")
    String acceptMessage (@WebParam(name = "parameters", targetNamespace = "")
                                  String soapMessage);
}
