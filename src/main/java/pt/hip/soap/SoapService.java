package pt.hip.soap;

import jakarta.jws.WebService;

@WebService
public interface SoapService {

    boolean acceptMessage (String soapMessage);
}
