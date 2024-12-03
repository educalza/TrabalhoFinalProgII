package com;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;

/**
 *
 * @author Jose
 */
public class MenuControle {
    
    @FXML
    private void producaoIncluir() throws IOException{
        App.setRoot("producaoIncluir");
    }
    
    @FXML
    private void producaoListarPorVaca() throws IOException{
        App.setRoot("producaoListarPorVaca");
    }
    
    @FXML
    private void sair(){
        Platform.exit();
        System.exit(0);
    }
    
    @FXML
    private void vacaAlterar() throws IOException{
        App.setRoot("vacaAlterar");
    }
    
    @FXML
    private void vacaIncluir() throws IOException{
        App.setRoot("vacaIncluir");
    }
     
    
}
