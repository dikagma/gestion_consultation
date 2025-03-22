module com.cmdb.gestionconsultation {
    requires javafx.fxml;
    requires java.sql;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.controls;




    opens com.cmdb.gestionconsultation.controllers to javafx.fxml;
    opens com.cmdb.gestionconsultation.entities to javafx.base;
    exports com.cmdb.gestionconsultation;
}