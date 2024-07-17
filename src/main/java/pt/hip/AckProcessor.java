package pt.hip;

import ca.uhn.hl7v2.model.v25.message.ACK;
import ca.uhn.hl7v2.parser.PipeParser;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.UUID;

public class AckProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        ACK ack = new ACK();
        ack.initQuickstart("ACK", "A19", "D");
        ack.getMSH().getMsh10_MessageControlID().setValue(String.valueOf(UUID.randomUUID()));

        ack.getMSA().getAcknowledgmentCode().setValue("AA");
        ack.getMSA().getMsa2_MessageControlID().setValue(String.valueOf(UUID.randomUUID()));

        PipeParser parser = new PipeParser();
        String ackMessage = parser.encode(ack);

        exchange.getIn().setBody(ackMessage);
    }
}
