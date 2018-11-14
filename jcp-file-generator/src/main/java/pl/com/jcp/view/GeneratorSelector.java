package pl.com.jcp.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GeneratorSelector extends AnchorPane {

    private static final String FXML_NAME = "/MainUIGenerator.fxml";
    private FXMLLoader loader;
    private AnchorPane mainPane;
    private VBox verticalContent;
    private Button selectDirectory;
    private Label directoryName;
    private File directoryToSaveFiles;

    public GeneratorSelector() {
        this.loader = new FXMLLoader(getClass().getResource(FXML_NAME));
        try {
            this.mainPane = this.loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.verticalContent = (VBox) getByNodeKey("verticalContent");
        this.selectDirectory = (Button) getByNodeKey("selectDirectory");
        this.directoryName = (Label) getByNodeKey("directoryName");
        initializeDirectorySelector();
        this.getChildren().add(mainPane);
    }

    private void initializeDirectorySelector() {
        this.getSelectDirectory().setOnAction(event -> {
            DirectoryChooser chooser = new DirectoryChooser();
            setDirectoryToSaveFiles(chooser.showDialog(App.getApp().getPrimaryScene().getWindow()));
            if(getDirectoryToSaveFiles() != null){
                getDirectoryName().setText("Folder name: " + getDirectoryToSaveFiles().getName());
            } else {
                getDirectoryName().setText("");
            }
        });
    }

    public void addAbstractGenerator(GeneratorAbstract generator){
        this.getVerticalContent().getChildren().add(generator);
    }

    public File getDirectoryToSaveFiles() {
        return directoryToSaveFiles;
    }

    public void setDirectoryToSaveFiles(File directoryToSaveFiles) {
        this.directoryToSaveFiles = directoryToSaveFiles;
    }

    private Object getByNodeKey(String key){
        return this.getLoader().getNamespace().get(key);
    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public AnchorPane getMainPane() {
        return mainPane;
    }

    public void setMainPane(AnchorPane mainPane) {
        this.mainPane = mainPane;
    }

    public VBox getVerticalContent() {
        return verticalContent;
    }

    public void setVerticalContent(VBox verticalContent) {
        this.verticalContent = verticalContent;
    }

    public Button getSelectDirectory() {
        return selectDirectory;
    }

    public void setSelectDirectory(Button selectDirectory) {
        this.selectDirectory = selectDirectory;
    }

    public Label getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(Label directoryName) {
        this.directoryName = directoryName;
    }
}
