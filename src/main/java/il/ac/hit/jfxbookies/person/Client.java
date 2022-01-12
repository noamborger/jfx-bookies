package il.ac.hit.jfxbookies.person;

import lombok.Data;

@Data
public class Client extends AbstractPerson{
    private String phone;
    private String address;
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
