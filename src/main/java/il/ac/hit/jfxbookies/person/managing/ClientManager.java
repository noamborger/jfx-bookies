package il.ac.hit.jfxbookies.person.managing;

import il.ac.hit.jfxbookies.JdbcDriverSetup;
import il.ac.hit.jfxbookies.person.AbstractPerson;
import il.ac.hit.jfxbookies.person.Client;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class ClientManager {
    public Client getClientById(int id) throws SQLException {
        return JdbcDriverSetup.getDao(Client.class)
                .queryBuilder()
                .where()
                .eq("id", id)
                .or()
                .eq("phone", id)
                .queryForFirst();
    }


    //other functions
    public void addClient(Client client) {
        try {
            JdbcDriverSetup.getDao(Client.class).create(client);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteClient(String id) throws SQLException {
        JdbcDriverSetup.getDao(Client.class).deleteById(id);
    }

    public void updateClient(Client client) throws SQLException {
        JdbcDriverSetup.getDao(Client.class)
                .update(client);
    }
}
