package View;

import Requests.Request;
import Requests.RequestType;
import UserObject.User;
import UserObject.UserType;
import ViewContollers.RequestsController;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
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

    /*private EventHandler<ActionEvent> geodesyHandler = event -> System.out.println("Button succesfully clicked");
    private EventHandler<ActionEvent> acceptHandler;
    private EventHandler<ActionEvent> rejectHandler = event -> System.out.println("Button succesfully clicked");*/

    class RequestButtonHandler implements EventHandler<ActionEvent> {
        private final String btnId;
        private final String requestNumber;
        private final String buttonName;

        //private RequestsController requestsController = new RequestsController();

        public RequestButtonHandler(String btnId) {
            this.btnId = btnId;
            requestNumber = btnId.replaceAll("\\D+","");
            buttonName = btnId.replaceAll("[^A-Za-z]+", "");
        }

        @Override
        public void handle(ActionEvent event) {
            if (buttonName.equalsIgnoreCase("accept")) {
                requestsController.acceptButtonClicked(requestNumber);
            } else if (buttonName.equalsIgnoreCase("reject")) {
                requestsController.rejectButtonClicked(requestNumber);
            } else {
                requestsController.geodesyButtonClicked(requestNumber);
            }
        }

    }


    public RequestsStage(User user) throws Exception {
        this.user = user;
        requestList = user.getRequests();

        requestsController = new RequestsController(user);

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

                vBox.getChildren().addAll(new HBox(new Label("From: "), new Text(r.getRequestingUser().getUsername())));

                if (r.getOtherUser() != null) {
                    vBox.getChildren().addAll(new HBox(new Label("To: "), new Text(r.getOtherUser().getUsername())));
                }

                vBox.getChildren().addAll( new HBox(new Label("Request: "), new Text(r.getRequestType().toString())),
                        new HBox(new Label("Object: "), new Text(r.getCadasterObject().toString())));

                if (user.getUserType().equals(UserType.OFFICE)) {

                    Button sendBtn = createButton("Send to geodesy", r.getNumber());
                    Button acceptBtn = createButton("Accept", r.getNumber());
                    Button rejectBtn = createButton("Reject", r.getNumber());

                    vBox.getChildren().addAll(new HBox(sendBtn, acceptBtn, rejectBtn));

                    if (r.getRequestType().equals(RequestType.BUILD) ||
                            r.getRequestType().equals(RequestType.DEMOLITION) ||
                            r.getRequestType().equals(RequestType.TERRAIN)) {

                        acceptBtn.setDisable(true);
                        rejectBtn.setDisable(true);

                    } else {
                        sendBtn.setDisable(true);

                    }

                } else {

                    ProgressIndicator pi = new ProgressIndicator();
                    pi.setPrefSize(30, 30);

                    Text stateText = new Text();


                    if (r.getAccepted()) {
                        pi.setProgress(1.0);
                        stateText.setText("Accepted");
                        vBox.getChildren().addAll(new HBox(pi, stateText));

                    } else if (r.getRejected()) {
                        stateText.setText("Rejected");
                        vBox.getChildren().add(stateText);
                    } else {
                        pi.setProgress(-1.0);
                        vBox.getChildren().add(pi);
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

    private Button createButton(String name, String request) {
        Button button = new Button(name);
        button.setId(name+request);
        button.setOnAction(new RequestButtonHandler(button.getId()));
        return button ;
    }

    public void sceneEvents () {

    }
}

