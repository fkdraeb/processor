package pt.hip.soap;

import jakarta.annotation.Resource;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceContext;
import jakarta.xml.ws.handler.MessageContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebService(endpointInterface = "pt.hip.soap.SoapService")
public class SoapServiceImpl implements SoapService {

    private String jdbcUrl = "jdbc:mysql://mysql:3306/HIP";
    private String dbUsername = "root";
    private String dbPassword = "password";

    @Resource
    private WebServiceContext wsctx;

    @Override
    public String acceptMessage(String soapMessage) {

        MessageContext mctx = wsctx.getMessageContext();

        // Get detail from request headers
        Map<String, List<String>> http_headers = (Map<String, List<String>>) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
        List<String> userList = http_headers.get("Username");
        List<String> passList = http_headers.get("Password");

        String username = "";
        String password = "";

        if (userList != null) {
            // Get username
            username = userList.get(0);
        }

        if (passList != null) {
            // Get password
            password = passList.get(0);
        }

        // Should validate username and password with database
        if ("pis".equals(username) && "pis".equals(password)) {
            return "Hello World JAX-WS - Valid User!";
        } else {
            return "Unknown User!";
        }

        // Uncomment to save the message to the database
        // try {
        //     saveMessageToDatabase(soapMessage);
        // } catch (SQLException e) {
        //     throw new RuntimeException(e);
        // }
        //
        // return "The message was acknowledge";
    }

    private void saveMessageToDatabase(String message) throws SQLException {
        String sql = "INSERT INTO EVENTS_MESSAGES (EVENT_IN) VALUES (?)";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, message);
            statement.executeUpdate();
        }
    }
}
