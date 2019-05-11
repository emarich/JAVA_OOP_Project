package View;

import Requests.Request;
import Requests.RequestType;
import UserObject.User;
import ViewContollers.RequestsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RequestsStage extends FlowPane {
    //View controller
    private RequestsController requestsController;

    private User office;
    private List<Request> requestList = new ArrayList<>();

    //New stage
    private Stage stage = new Stage();

    private VBox vBox = new VBox();

    private EventHandler<ActionEvent> myHandler = event -> System.out.println("Button succesfully clicked");


    /*private Label usernameLabel = new Label("From:");
    private Text usernameTxt = new Text();
    private Label requestLabel = new Label("Request:");
    private Text requestTxt = new Text();
    private Label cadasterLabel = new Label("Property:");
    private Text cadasterTxt = new Text();*/

    private List<Button> buttonList = new ArrayList<>();

    private Button sendBtn;
    private Button acceptBtn;

    public RequestsStage(User office) throws Exception {
        this.office = office;
        requestList = office.getRequests();

        requestsController = new RequestsController();

        setScene();

        sceneEvents();

        stage.show();
    }

    private void setScene() {
        stage.setTitle("Requests");
        stage.setWidth(440);
        stage.setHeight(400);
        stage.setScene(new Scene(this, stage.getWidth(), stage.getHeight()));

        if (requestList != null) {
            for (Request r : requestList) {
                System.out.println(r.getNumber());
                /*HBox hBox1 = new HBox();
                HBox hBox2 = new HBox();
                HBox hBox3 = new HBox();
                HBox hBox4 = new HBox();*/

                vBox.getChildren().addAll(new HBox(new Label("From: "), new Text(r.getRequestingUser().getUsername())),
                        new HBox(new Label("Request: "), new Text(r.getRequestType().toString())),
                        new HBox(new Label("Object: "), new Text(r.getCadasterObject().toString())));
                Button sendBtn = new Button("Send to geodesy");
                sendBtn.setOnAction(myHandler);
                Button acceptBtn = new Button("Accept");
                acceptBtn.setOnAction(myHandler);
                vBox.getChildren().addAll(new HBox(sendBtn, acceptBtn));
                if (r.getRequestType().equals(RequestType.BUILD) ||
                        r.getRequestType().equals(RequestType.DEMOLITION) ||
                        r.getRequestType().equals(RequestType.TERRAIN)) {
                    acceptBtn.setDisable(true);
                    //buttonList.add(new Button("Send to geodesy"));
                    //Button sendBtn = new Button("Send to geodesy");
                } else {
                    sendBtn.setDisable(true);
                    //buttonList.add(new Button("Send to geodesy"));
                }
                Line line = new Line();
                line.autosize();
                line.prefWidth(stage.getWidth());
                line.setStartX(120);
                line.setEndX(320);
                vBox.getChildren().addAll(line);
            }
        }

        this.getChildren().addAll(vBox);
        this.setAlignment(Pos.CENTER);
        this.setOrientation(Orientation.VERTICAL);
        this.setVgap(20);

        stage.show();

    }

    public void sceneEvents () {

    }
}

