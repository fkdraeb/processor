package pt.hip.soap.response;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Response")
public class Response {
    private GenericResponse genericResponse;

    @XmlElement(name = "GenericResponse")
    public GenericResponse getGenericResponse() {
        return genericResponse;
    }

    public void setGenericResponse(GenericResponse genericResponse) {
        this.genericResponse = genericResponse;
    }
}
