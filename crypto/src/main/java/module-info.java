module com.csc3402.lab.crypto {
    requires javafx.controls;
    requires java.datatransfer;
    requires java.desktop;
    //lab 5 RMI
    requires java.rmi;
    requires javafx.graphics;
    requires java.sql;
    //lab 5 RMI
    exports adv_prog.lab5 to java.rmi, javafx.graphics;
    opens adv_prog.lab5 to java.rmi, javafx.graphics;
    //lab 6 RMI
    exports adv_prog.lab6 to java.rmi, javafx.graphics;
    opens adv_prog.lab6 to java.rmi, javafx.graphics;

    opens com.csc3402.lab.crypto to javafx.fxml;
    exports com.csc3402.lab.crypto;
    exports adv_prog;
    opens adv_prog to javafx.fxml;
    exports adv_prog.chat_server to java.rmi, javafx.graphics;
    opens adv_prog.chat_server to java.rmi, javafx.graphics;
}