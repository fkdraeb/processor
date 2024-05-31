package pt.hip.soap;

import jakarta.jws.WebService;

@WebService(endpointInterface = "pt.hip.soap.SoapService")
public class SoapServiceImpl implements SoapService {

    @Override
    public String acceptMessage(String soapMessage) {

        /**TODO Here should be created the logic to create a new event
         * Should validate the fields on the message
         * Should validate the subscription is ative
         * More ...
         * Should return is the event was created succefully, if not should be in the response info
         */



        return "The message was acknowledge";
    }



}
