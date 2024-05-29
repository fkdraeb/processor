package pt.hip.old.soap;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v25.segment.MSH;
import ca.uhn.hl7v2.model.v25.segment.PID;
import ca.uhn.hl7v2.parser.PipeParser;
import jakarta.jws.WebService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//@WebService(endpointInterface = "pt.hip.soap.HL7Service")
public class HL7ServiceImpl implements HL7Service {

    /**
     * The methods that use HL7 lib throw jakarta.xml.bind.PropertyException
     * if HL7Exception is added to the method signature.
     * Using try/catch makes the behaviour work ok
     */

    @Override
    public String getPatientId(String exchange) {
        PID pidSegment = null;

        try {
            pidSegment = (PID) hl7ParserFromString(exchange).get("PID");
        } catch (HL7Exception e) {
            System.err.println("Error occurred while parsing HL7 message: " + e.getMessage());
            return "Error: Unable to parse HL7 message";
        }

        return "The patient ID of the current message is " + pidSegment.getPatientIdentifierList(0).getIDNumber().getValue();
    }

    @Override
    public String getPatientAdministrativeSex(String exchange) {
        PID pidSegment = null;

        try {
            pidSegment = (PID) hl7ParserFromString(exchange).get("PID");
        } catch (HL7Exception e) {
            System.err.println("Error occurred while parsing HL7 message: " + e.getMessage());
            return "Error: Unable to parse HL7 message";
        }

        return "The administrative sex of the patient is: " + pidSegment.getAdministrativeSex().getValue();
    }

    @Override
    public String getMSHDate(String exchange) {
        String dateString = "";
        MSH mshSegment = null;

        try {
            mshSegment = (MSH) hl7ParserFromString(exchange).get("MSH");
            String timeStamp = String.valueOf(mshSegment.getDateTimeOfMessage()).substring(3, 17);

            SimpleDateFormat tsFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = tsFormat.parse(timeStamp);
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            dateString = outputFormat.format(date);
        } catch (HL7Exception e) {
            System.err.println("Error occurred while parsing HL7 message: " + e.getMessage());
            return "Error: Unable to parse HL7 message";

        } catch (ParseException e) {
            System.err.println("Error occurred while parsing date/time from HL7 message: " + e.getMessage());
            return "Error: Unable to parse date/time from HL7 message";
        }

        return "The date of the message is " + dateString;
    }

    public static Message hl7ParserFromString(String exchangeBody) throws HL7Exception {
        PipeParser parser = new PipeParser();
        return parser.parse(exchangeBody);
    }

}
