package ru.list.rb.s;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable { // При запуске, для сцены выполнится метод initialize
    private Network network;
    private static Logger logger = LogManager.getRootLogger();

    @FXML
    TextField msgField;

    @FXML
    TextArea mainArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.info("Инициализация сети...");
        network = new Network((args -> {
            mainArea.appendText((String) args[0]);
        })); // В идеале надо подключаться по нажатию кнопки, а не старта программы
    }

    public void sendMessageAction(ActionEvent actionEvent) {
        network.sendMessage(msgField.getText()); // Отправляем то что написано в msgField
        msgField.clear(); // Очищаем сообщение
        msgField.requestFocus(); // Переключаем фокус
    }

    public void exitAction(ActionEvent actionEvent) {
        network.close();
        Platform.exit();
        logger.info("Выход из программы");
    }
}
