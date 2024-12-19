package com;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import modelo.Vaca;
import util.Dao;

public class VacaCadastroController {

    @FXML private TextField txtVacaBrinco;
    @FXML private TextField txtVacaNome;
    @FXML private TextField txtVacaRaca;
    
    private final Dao<Vaca> vacaDao = new Dao<>(Vaca.class);    
    
    @FXML
    public void salvarVaca(){
        String brinco = txtVacaBrinco.getText();
        String nome = txtVacaNome.getText();
        String raca = txtVacaRaca.getText();
        
        if(brinco == null || nome == null || raca == null){
            mostrarAlerta(Alert.AlertType.WARNING, "Aviso", "Preencha todos os campos");
            return;
        }
        
        try{
            Vaca vacaExistente = vacaDao.buscarPorChave("brinco", brinco);
            if(vacaExistente != null){
                mostrarAlerta(Alert.AlertType.WARNING, "Aviso", "Vaca já existente.");
                return;
            }
            
            Vaca vaca = new Vaca(brinco, nome, raca);
            vacaDao.inserir(vaca);
            limparCamposVaca();
            
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Vaca cadastrada.");
        } catch(Exception e){
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Erro desconhecido " + e.getMessage());
        }
    }
    
    @FXML
    private void limparCamposVaca(){
        txtVacaBrinco.clear();
        txtVacaNome.clear();
        txtVacaRaca.clear();
    }
    
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem){
            Alert alert = new Alert(tipo);
            alert.setTitle(titulo);
            alert.setHeaderText(null);
            alert.setContentText(mensagem);
            alert.showAndWait();
    }
}