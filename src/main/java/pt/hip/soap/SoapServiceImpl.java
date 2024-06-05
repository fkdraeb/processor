package pt.hip.soap;

import jakarta.jws.WebService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebService(endpointInterface = "pt.hip.soap.SoapService")
public class SoapServiceImpl implements SoapService {

    private String jdbcUrl = "jdbc:mysql://mysql:3306/HIP";
    private String username = "root";
    private String password = "password";

    @Override
    public String acceptMessage(String soapMessage) {

        /**TODO Here should be created the logic to create a new event
         * Should validate the fields on the message
         * Should validate the subscription is active
         * More ...
         * Should return is the event was created successfully, if not should be in the response info
         */

        try {
            saveMessageToDatabase(soapMessage);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "The message was acknowledge";
    }

    private void saveMessageToDatabase(String message) throws SQLException {
        String sql = "INSERT INTO EVENTS_MESSAGES (EVENT_IN) VALUES (?)";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, message);
            statement.executeUpdate();
        }
    }

}
