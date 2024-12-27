package com;

import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import modelo.Usuario;
import util.Dao;

public class UsuarioListar {
    
    @FXML
    private TableView<Usuario> tabelaUsuarios;
    
    @FXML
    private TableColumn<Usuario, String> colLogin;
    
    @FXML
    private TableColumn<Usuario, String> colNome;
    
    @FXML
    private TableColumn<Usuario, String> colSenha;
    
    private Dao<Usuario> daoUsuario;
    private ObservableList<Usuario> listaUsuario;
    
    @FXML
    private void cancelar() throws IOException{
        App.setRoot("Menu");
    }
    
    @FXML
    private void initialize(){
        daoUsuario = new Dao<>(Usuario.class);
    }
    

}
