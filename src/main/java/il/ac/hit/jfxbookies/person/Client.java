package il.ac.hit.jfxbookies.person;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import il.ac.hit.jfxbookies.JdbcDriverSetup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.SQLException;

@Data
@DatabaseTable
@AllArgsConstructor
@NoArgsConstructor
public class Client extends AbstractPerson{
    @DatabaseField
    private String phone;
    @DatabaseField
    private String address;
    @DatabaseField(unique = true)
    private String email;


    @Builder
    public Client(String id, String name, String email, String phone, String address){
        super(id, name);
        this.email=email;
        this.phone=phone;
        this.address=address;
    }


    @Override
    public void printInfo() {

    }


    //other functions
    public void addClient(){
        try {
            JdbcDriverSetup.getDao(Client.class).create(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public void deleteClient(String id){

    }
}
