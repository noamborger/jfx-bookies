package il.ac.hit.jfxbookies.person;

public class Manager extends Librarian{

    public Manager(int id, String name) {
        super(id, name);
    }

    public String getInfo() {
        return this.toString();
    }
}
