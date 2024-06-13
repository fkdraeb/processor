package pt.hip;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.segment.MSH;
import ca.uhn.hl7v2.model.v25.segment.QPD;
import ca.uhn.hl7v2.parser.PipeParser;
import com.google.gson.JsonObject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import ca.uhn.hl7v2.model.Message;

public class PatientJsonBuilder implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String body = exchange.getIn().getBody(String.class);
        JsonObject patientJson = new JsonObject();

        Message originMessage = hl7ParserFromString(body);
        MSH mshSegment = (MSH) originMessage.get("MSH");
        QPD qpdSegment = (QPD) originMessage.get("QPD");

        String patientId = qpdSegment.getQpd3_UserParametersInsuccessivefields().encode().replaceAll(".*\\^", "");
        patientJson.addProperty("patientId",patientId);

        exchange.getIn().setBody(patientJson.toString());
    }

    public static Message hl7ParserFromString(String exchangeBody) throws HL7Exception {
        PipeParser parser = new PipeParser();
        return parser.parse(exchangeBody);
    }
}
