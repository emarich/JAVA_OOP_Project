package View;

import Requests.Request;
import Requests.RequestType;
import UserObject.User;
import UserObject.UserType;
import ViewContollers.RequestsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
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

    private User user;
    private List<Request> requestList;

    //New stage
    private Stage stage = new Stage();

    private VBox vBoxMain = new VBox();

    private ScrollPane scrollPane = new ScrollPane();

    private EventHandler<ActionEvent> geodesyHandler = event -> System.out.println("Button succesfully clicked");
    private EventHandler<ActionEvent> acceptHandler = event -> System.out.println("Button succesfully clicked");


    public RequestsStage(User user) throws Exception {
        this.user = user;
        requestList = user.getRequests();

        requestsController = new RequestsController();

        setScene();

        sceneEvents();

        stage.show();
    }

    private void setScene() {
        stage.setTitle("Requests");
        stage.setWidth(550);
        stage.setHeight(550);
        stage.setScene(new Scene(this, stage.getWidth(), stage.getHeight()));
        vBoxMain.setSpacing(20);
        vBoxMain.maxHeight(500);
        vBoxMain.maxWidth(500);

        scrollPane.setContent(vBoxMain);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setPannable(true);
        scrollPane.setPrefSize(500, 500);


        if (requestList != null) {
            for (Request r : requestList) {
                VBox vBox = new VBox();
                vBox.setSpacing(5);

                System.out.println(r.getNumber());
                /*HBox hBox1 = new HBox();
                HBox hBox2 = new HBox();
                HBox hBox3 = new HBox();
                HBox hBox4 = new HBox();*/

                vBox.getChildren().addAll(new HBox(new Label("From: "), new Text(r.getRequestingUser().getUsername())),
                        new HBox(new Label("Request: "), new Text(r.getRequestType().toString())),
                        new HBox(new Label("Object: "), new Text(r.getCadasterObject().toString())));
                if (user.getUserType().equals(UserType.OFFICE)) {
                    Button sendBtn = new Button("Send to geodesy");
                    sendBtn.setOnAction(geodesyHandler);
                    Button acceptBtn = new Button("Accept");
                    acceptBtn.setOnAction(acceptHandler);
                    //Region region = new Region();
                    vBox.getChildren().addAll(new HBox(sendBtn,  new Region(), acceptBtn));
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
                } else {
                    final ProgressIndicator pi = new ProgressIndicator();
                    pi.setPrefSize(30, 30);
                    vBox.getChildren().add(pi);
                    if (r.getAccepted()) {
                        pi.setProgress(1.0);
                    } else {
                        pi.setProgress(-1.0);
                    }
                }
                Separator separator = new Separator();
                separator.setMaxWidth(500);
                vBox.getChildren().addAll(separator);
                vBoxMain.getChildren().add(vBox);
            }
        }

        this.getChildren().addAll(vBoxMain, scrollPane);
        this.setAlignment(Pos.CENTER);
        this.setOrientation(Orientation.VERTICAL);
        this.setVgap(20);

    }

    public void sceneEvents () {

    }
}

