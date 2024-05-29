package pt.hip.soap;

import jakarta.jws.WebService;

@WebService
public interface SoapService {

    String acceptMessage (String soapMessage);
}
