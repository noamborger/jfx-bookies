package il.ac.hit.jfxbookies.person;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;

@AllArgsConstructor
@Data
@NoArgsConstructor
@DatabaseTable(tableName = "users")
@Builder
public class User {
    @DatabaseField(columnName = "username", id = true)
    private String userName;
    @DatabaseField
    private String password;
    @DatabaseField(dataType = DataType.ENUM_STRING)
    private UserType userType;

    public static User buildUser(String username, String password) {
        return User.builder()
                .password(DigestUtils.sha512Hex(password))
                .userName(username)
                .build();
    }

    public enum UserType {
        MANAGER, LIBRARIAN
    }
}
