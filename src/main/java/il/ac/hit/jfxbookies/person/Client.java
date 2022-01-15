package il.ac.hit.jfxbookies.person;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@DatabaseTable
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Client extends AbstractPerson{
    @DatabaseField
    private String phone;
    @DatabaseField
    private String address;
    @DatabaseField(unique = true)
    private String email;


    @Override
    public void printInfo() {

    }


    //other functions
    public void addClient(Client client){

    }
    public void deleteClient(String id){

    }
}
