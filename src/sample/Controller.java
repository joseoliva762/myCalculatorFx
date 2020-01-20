package sample;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import sample.Model.Calculator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private TextField enterOperation;
    @FXML private TextField resultOfOperation;
    @FXML private ImageView Accenture;
    Calculator myCalculator = new Calculator();
    String result = new String();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        enterOperation.addEventFilter(KeyEvent.ANY, handlerLetters);
        this.result = String.valueOf(0.0);
    }

    public void onExitButtonClicked(MouseEvent event){
        Platform.exit();
        System.exit(0);
    }
    public void onCalculateButtonClicked(MouseEvent event){
        //resultOfOperation.setText(enterOperation.getText());
        resultOfOperation.setText(myCalculator.enterFullOperation(enterOperation.getText()));
    }
    public void onAccentureButtonClicked(MouseEvent event){
        goToURL("https://portal.accenture.com/");
    }
    public void goToURL(String URL){
        if (java.awt.Desktop.isDesktopSupported()) {
            java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
            if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
                try {
                    java.net.URI uri = new java.net.URI(URL);
                    desktop.browse(uri);
                } catch (URISyntaxException | IOException ex) {
                    //Logger.getLogger(desktop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }



   EventHandler<KeyEvent> handlerLetters = new EventHandler<KeyEvent>() {
        private boolean willConsume = false;
       @Override
       public void handle(KeyEvent event) {
            Object template = event.getSource();
            if(willConsume){
                event.consume();
            }
            String template_t = event.getCode().toString();
            if(template_t.matches("[a-zA-Z]") && event.getCode() != KeyCode.BACK_SPACE && event.getCode() != KeyCode.SHIFT
                                                    && event.getCode() != KeyCode.SPACE){
                if(event.getEventType() == KeyEvent.KEY_PRESSED){
                    willConsume  = true;
                }else if(event.getEventType() == KeyEvent.KEY_RELEASED){
                    willConsume =false;
                }
           }
       }
   };
}
