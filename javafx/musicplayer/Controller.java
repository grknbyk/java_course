package musicplayer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Controller implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private Button playButton, pauseButton, resetButton, nextMusic, preMusic;
    @FXML
    private Slider volSlider;
    @FXML
    private ComboBox<String> speedCtrl;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label songName;

    private File directory;
    private File[] files;

    private ArrayList<File> songs;

    private Media media;
    private MediaPlayer mediaPlayer;
    private int songNumber = 0;
    private int[] speeds = { 25, 50, 75, 100, 125, 150 };
    private Timer timer;
    private TimerTask task;
    private boolean running;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        songs = new ArrayList<>();
        directory = new File("musics");

        files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                songs.add(file);
                System.out.println(file);
            }
        }

        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        songName.setText(songs.get(songNumber).getName());

        for (int speed : speeds) {
            speedCtrl.getItems().addAll("%" + String.valueOf(speed));
        }

        speedCtrl.setOnAction(this::changeSpeed);
        volSlider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                mediaPlayer.setVolume(volSlider.getValue() * 0.01);
                
            }
            
        });
        
    }
    
    public void playMusic() {
        beginTimer();
        changeSpeed(null);
        mediaPlayer.setVolume(volSlider.getValue() * 0.01);
        mediaPlayer.play();
    }
    
    public void pauseMusic() {
        cancelTimer();
        mediaPlayer.pause();
    }

    public void resetMusic() {
        progressBar.setProgress(0);
        mediaPlayer.seek(Duration.seconds(0));
    }

    public void nextMusic() {
        if (songNumber < songs.size() - 1) {

            // next song number
            songNumber++;

        } else {
            songNumber = 0;
        }

        if (running) {
            cancelTimer();
        }

        // stops current song
        mediaPlayer.stop();

        // changes label
        songName.setText(songs.get(songNumber).getName());

        // creates new media using next song
        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        playMusic();
    }

    public void preMusic() {
        if (songNumber > 0) {

            // next song number
            songNumber--;
        } else {
            songNumber = songs.size() - 1;
        }

        if (running) {
            cancelTimer();
        }

        // stops current song
        mediaPlayer.stop();

        // changes label
        songName.setText(songs.get(songNumber).getName());

        // creates new media using next song
        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        playMusic();
    }

    public void changeSpeed(ActionEvent event) {
        if (speedCtrl.getValue() == null) {
            mediaPlayer.setRate(1);
        } else {
            mediaPlayer.setRate(Integer.parseInt(speedCtrl.getValue().substring(1)) * 0.01);
        }
    }

    public void beginTimer() {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                running = true;
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = mediaPlayer.getTotalDuration().toSeconds();
                System.out.println(current / end);
                progressBar.setProgress(current / end);

                if (current / end == 1) {
                    cancelTimer();
                }
            }
        };

        timer.schedule(task, 0, 1000);
    }

    public void cancelTimer() {
        running = false;
        timer.cancel();
    }
}
