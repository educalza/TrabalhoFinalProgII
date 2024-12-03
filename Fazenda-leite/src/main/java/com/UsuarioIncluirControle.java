package com;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import modelo.Usuario;
import util.Dao;

public class UsuarioIncluirControle {

    @FXML
    private TextField campoLogin;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoSenha;

    private Dao<Usuario> dao;

    @FXML
    private void initialize() {
        dao = new Dao<>(Usuario.class);
    }

    @FXML
    private void gravar() {
        // Validação de campos obrigatórios
        if (campoLogin.getText().isBlank() || campoNome.getText().isBlank() || campoSenha.getText().isBlank()) {
            mostrarErro("Preencha todos os campos obrigatórios.");
            return;
        }

        String login = campoLogin.getText().toLowerCase();
        String nome = campoNome.getText().toUpperCase();

        // Verificar se já existe um usuário com o mesmo login
        Usuario temp = dao.buscarPorChave("login", login);
        if (temp != null) {
            mostrarErro("Já existe um usuário com este login.");
            return;
        }

        // Criar o novo usuário e inserir no banco de dados
        Usuario usuario = new Usuario();
        usuario.setLogin(login);
        usuario.setNome(nome);
        usuario.setSenha(campoSenha.getText());

        dao.inserir(usuario);

        limparCampos();
        mostrarSucesso("Usuário cadastrado com sucesso.");
    }

    @FXML
    private void cancelar() throws IOException {
        // Retorna para a tela anterior ou menu principal
        App.setRoot("menu");
    }

    private void limparCampos() {
        this.campoLogin.setText("");
        this.campoNome.setText("");
        this.campoSenha.setText("");
    }

    private void mostrarSucesso(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.show();
    }

    private void mostrarErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.show();
    }
}