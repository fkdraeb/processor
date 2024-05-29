package pt.hip.soap;

import jakarta.jws.WebService;

import static pt.hip.soap.SoapMainProcessor.createGenericResponse;


@WebService(endpointInterface = "pt.hip.soap.SoapService")
public class SoapServiceImpl implements SoapService {

    @Override
    public String acceptMessage(String soapMessage) {
        //TODO Here should be created the logic to accept the message

        //return "tesssssssssste";
        return createGenericResponse("200", "", "1234", "OK");
    }



}
