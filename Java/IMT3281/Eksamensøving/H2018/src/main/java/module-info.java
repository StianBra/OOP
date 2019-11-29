module no.ntnu.imt3281.H2018 {
    requires javafx.controls;
    requires javafx.fxml;

    opens no.ntnu.imt3281.H2018 to javafx.fxml;
    exports no.ntnu.imt3281.H2018;
}