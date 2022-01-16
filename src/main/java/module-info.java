module il.ac.hit.jfxbookies {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires lombok;
    requires ormlite.jdbc;
    requires commons.dbcp2;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires org.apache.commons.codec;
    requires java.sql;

    opens il.ac.hit.jfxbookies to javafx.fxml;
    exports il.ac.hit.jfxbookies;
    exports il.ac.hit.jfxbookies.library.book to ormlite.jdbc, javafx.base;
    opens il.ac.hit.jfxbookies.library.book to ormlite.jdbc, javafx.base;
    exports il.ac.hit.jfxbookies.library.managing to ormlite.jdbc;
    opens il.ac.hit.jfxbookies.library.managing to ormlite.jdbc;
    exports il.ac.hit.jfxbookies.person to ormlite.jdbc;
    opens il.ac.hit.jfxbookies.person to ormlite.jdbc;
    exports il.ac.hit.jfxbookies.view;
    opens il.ac.hit.jfxbookies.view to javafx.fxml;

    exports il.ac.hit.jfxbookies.startup to com.fasterxml.jackson.databind;
    opens il.ac.hit.jfxbookies.startup to com.fasterxml.jackson.databind;

}