package pt.hip;

import jakarta.jws.WebService;
import java.text.ParseException;

@WebService
public interface HL7Service {
    String getPatientId(String exchange);
    String getPatientAdministrativeSex(String exchange);
    String getMSHDate(String exchange) throws ParseException;
}
