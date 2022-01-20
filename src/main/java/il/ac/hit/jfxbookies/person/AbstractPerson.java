package il.ac.hit.jfxbookies.person;

import com.j256.ormlite.field.DatabaseField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractPerson {
    @DatabaseField(id = true)
    protected String id;
    @Setter
    @DatabaseField()
    protected String name;

    public abstract void printInfo();

}

