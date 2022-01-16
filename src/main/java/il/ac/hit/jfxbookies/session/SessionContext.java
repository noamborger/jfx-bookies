package il.ac.hit.jfxbookies.session;

import il.ac.hit.jfxbookies.person.User;
import lombok.Data;

@Data
public final class SessionContext {
    private User currentUser;

    private SessionContext() {

    }

    private static class InstanceHolder {
        public static final SessionContext INSTANCE = new SessionContext();
    }

    public static SessionContext getInstance() {
        return InstanceHolder.INSTANCE;
    }
}
