package il.ac.hit.jfxbookies.person;

import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class AbstractPerson {
    protected String id;
    @Setter
    protected String name;

    public abstract void printInfo();

}

