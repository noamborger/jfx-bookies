module il.ac.hit.jfxbookies {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires lombok;
    requires ormlite.jdbc;
    requires commons.dbcp2;
    requires java.sql;

    opens il.ac.hit.jfxbookies to javafx.fxml;
    exports il.ac.hit.jfxbookies;
    exports il.ac.hit.jfxbookies.library.book to ormlite.jdbc;
    opens il.ac.hit.jfxbookies.library.book to ormlite.jdbc;

}