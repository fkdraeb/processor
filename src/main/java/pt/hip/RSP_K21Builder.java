package pt.hip;

import ca.uhn.hl7v2.model.v25.message.RSP_K21;
import ca.uhn.hl7v2.model.v25.segment.PID;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RSP_K21Builder implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String queryResultString = exchange.getIn().getBody(String.class);

        Map<String, String> queryResultMap = parseQueryResult(queryResultString);

        RSP_K21 responseMessage = new RSP_K21();
        responseMessage.initQuickstart("RSP", "K21", "D");
        responseMessage.getMSA().getAcknowledgmentCode().setValue("AA");
        responseMessage.getMSA().getMsa2_MessageControlID().setValue(exchange.getIn().getHeader("originalMshId").toString());

        if (queryResultMap.isEmpty()) {
            responseMessage.getMSA().getTextMessage().setValue("Utente não encontrado");
        } else {
            PID pid = responseMessage.getQUERY_RESPONSE().getPID();
            pid.getPid3_PatientIdentifierList(0).getIDNumber().setValue(queryResultMap.get("patient_id"));
            pid.getPid3_PatientIdentifierList(0).getCx4_AssigningAuthority().getHd1_NamespaceID().setValue("HOS");
            pid.getPid5_PatientName(0).getGivenName().setValue(queryResultMap.get("patient_full_name"));
            pid.getPid7_DateTimeOfBirth().getTime().setValue(dateTimeConverterToHL7Timestamp(queryResultMap.get("date_of_birth")));
            pid.getPid8_AdministrativeSex().setValue(queryResultMap.get("gender"));
            pid.getPid11_PatientAddress(0).getStreetAddress().getStreetOrMailingAddress().setValue(queryResultMap.get("patient_address"));
        }
        exchange.getIn().setBody(responseMessage.encode());
    }

    public static Map<String, String> parseQueryResult(String queryResultString) {
        Map<String, String> resultMap = new HashMap<>();

        Pattern pattern = Pattern.compile("\\{([^{}]+)\\}");
        Matcher matcher = pattern.matcher(queryResultString);

        while (matcher.find()) {
            String[] keyValuePairs = matcher.group(1).split(", ");
            for (String pair : keyValuePairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length == 2) {
                    String key = keyValue[0];
                    String value = keyValue[1];
                    resultMap.put(key, value);
                }
            }
        }

        return resultMap;
    }

    private String dateTimeConverterToHL7Timestamp(String datetime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = dateFormat.parse(datetime);
        } catch (java.text.ParseException e) {
            System.err.println("Invalid date format");
            return "0";
        }
        SimpleDateFormat hl7DateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        return hl7DateFormat.format(date);
    }
}
