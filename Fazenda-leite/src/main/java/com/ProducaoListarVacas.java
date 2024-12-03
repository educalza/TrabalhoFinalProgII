package com;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Vaca;
import util.Dao;

public class ProducaoListarVacas {

    @FXML
    private ComboBox<Vaca> comboVacas; // ComboBox para seleção de vacas

    @FXML
    private TableView<Vaca> tabelaVacas; // Tabela para exibir vacas

    @FXML
    private TableColumn<Vaca, String> colunaBrinco;
    @FXML
    private TableColumn<Vaca, String> colunaNome;
    @FXML
    private TableColumn<Vaca, String> colunaRaca;

    private Dao<Vaca> daoVaca; // DAO para acessar o banco de dados
    private ObservableList<Vaca> vacasListadas; // Lista observável de vacas

    @FXML
    private void initialize() {
        // Inicializa o DAO
        daoVaca = new Dao<>(Vaca.class);

        // Busca todas as vacas cadastradas
        List<Vaca> vacasCadastradas = daoVaca.listarTodos();

        // Converte para ObservableList
        vacasListadas = FXCollections.observableArrayList(vacasCadastradas);

        // Configura o ComboBox
        comboVacas.setItems(vacasListadas);
        comboVacas.setOnAction(event -> exibirVacaSelecionada());

        // Configura a Tabela
        configurarTabela();
        tabelaVacas.setItems(vacasListadas); // Preenche a tabela com todas as vacas
    }

    private void configurarTabela() {
        // Configura colunas da tabela
        colunaBrinco.setCellValueFactory(new PropertyValueFactory<>("brinco"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaRaca.setCellValueFactory(new PropertyValueFactory<>("raca"));
    }

    private void exibirVacaSelecionada() {
        // Obtém a vaca selecionada no ComboBox
        Vaca selecionada = comboVacas.getValue();

        // Se não houver seleção, ignora
        if (selecionada == null) {
            return;
        }

        // Atualiza a tabela para exibir apenas a vaca selecionada
        tabelaVacas.setItems(FXCollections.observableArrayList(selecionada));
    }
}
