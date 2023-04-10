package webviewer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

public class Controller implements Initializable {

    @FXML
    private WebView webView;

    @FXML
    private TextField urlField;

    private String homePage;
    private WebEngine engine;
    private WebHistory history;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        engine = webView.getEngine();
        homePage = "www.google.com";
        urlField.setText(homePage);
        loadPage();


        
    }

    public void loadPage() {
        engine.load("http://" + urlField.getText());
    }

    public void refreshPage() {
        engine.reload();
    }

    public void zoomIn() {
        webView.setZoom(webView.getZoom() + 0.1);
    }

    public void zoomOut() {
        webView.setZoom(webView.getZoom() - 0.1);
    }

    public void zoomDefault() {
        webView.setZoom(1);
    }

    public void displayHistory() {
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        
        for (WebHistory.Entry entry : entries) {
            System.out.println(entry.getUrl() + " -- " + entry.getLastVisitedDate());
        }
    }
    
    public void nextPage() {
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        history.go(1);
        urlField.setText(entries.get(history.getCurrentIndex()).getUrl());
    }
    
    public void previousPage() {
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        history.go(-1);
        urlField.setText(entries.get(history.getCurrentIndex()).getUrl());
    }

    public void executeJS(){
        engine.executeScript("window.location = \"https://www.youtube.com\";");
    }
}