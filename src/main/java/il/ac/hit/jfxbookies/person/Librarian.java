package il.ac.hit.jfxbookies.person;

public class Librarian extends AbstractPerson{
    private User user;

    public Librarian(int id, String name) {
        super(id, name);
    }

    public Librarian() {
        super();
    }

    @Override
    public AbstractPerson printInfo(int id) {
        return null;
    }
}
