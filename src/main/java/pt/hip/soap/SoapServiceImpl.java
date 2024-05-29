package pt.hip.soap;

import jakarta.jws.WebService;


@WebService(endpointInterface = "pt.hip.soap.SoapService")
public class SoapServiceImpl implements SoapService {

    @Override
    public boolean acceptMessage(String soapMessage) {
        //TODO Here should be created the logic to accept the message

        return true;
    }



}
