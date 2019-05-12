package View;

import CadasterObjects.CadasterObject;
import CadasterObjects.Land;
import CadasterObjects.RealEstate;
import MyExceptions.WrongInputException;
import OtherFunctionality.PopUpAlert;
import Owners.Owner;
import Requests.RequestType;
import UserObject.Database;
import UserObject.User;
import ViewContollers.NewRequestController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class NewRequestStage extends FlowPane {
    //in new thread
    //View controller
    private NewRequestController newRequestController;
    private Database usersDatabase;
    private User requestingUser;
    private User otherUser;

    //New stage
    private Stage stage = new Stage();

    private VBox vBox = new VBox();

    private ObservableList<String> officeList = FXCollections.observableArrayList();
    private ObservableList<String> addressList = FXCollections.observableArrayList();

    private Label officeLabel = new Label("To office:");
    private ChoiceBox<String> officeBox = new ChoiceBox<>();
    private Text textDeposit = new Text(10, 20, "Deposit to cadastre");

    private Label proposersLabel = new Label("Proposers:");
    private TextField proposer1 = new TextField();
    private TextField proposer2 = new TextField();
    private ChoiceBox<String> address1 = new ChoiceBox<>();
    private ChoiceBox<String> address2 = new ChoiceBox<>();
    private TextField idText1 = new TextField();
    private TextField idText2 = new TextField();

    private Label favorLabel = new Label("In favor of:");
    private TextField nameTextfield = new TextField();

    private Label requestTypeLabel = new Label("Type of request: ");
    private ChoiceBox<String> requestBox =
            new ChoiceBox<>(FXCollections.observableArrayList(
                    RequestType.SALE.getDescription(), RequestType.GIVE.getDescription(),
                    RequestType.BUILD.getDescription(), RequestType.DEMOLITION.getDescription(),
                    RequestType.TERRAIN.getDescription()));

    private ListView<String> propertyBox = new ListView<>();
    private ObservableList<String> propertyList = FXCollections.observableArrayList();

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private LocalDate localDate = LocalDate.now();
    private String actualDate = dtf.format(localDate);
    private Text footer = new Text("Date: "+actualDate);

    private Button sendBtn = new Button("Send");

    public NewRequestStage(User user, Database usersDatabase) {
        requestingUser = user;
        newRequestController = new NewRequestController(user, usersDatabase);
        this.usersDatabase = usersDatabase;

        setScene(stage, user);

        sceneEvents();

        stage.show();
    }


    private void setScene(Stage stage, User user) {
        stage.setTitle("Request from "+user.getUsername());
        stage.setWidth(550);
        stage.setHeight(750);
        stage.setScene(new Scene(this, stage.getWidth(), stage.getHeight()));

        vBox.setSpacing(20);

        propertyBox.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        propertyBox.setPrefHeight(100);

        //prevents sending requests from user, that is not owner
        if (user.getOwner() instanceof Owner) {
            for (String s : usersDatabase.getUsersDataHM().keySet()) {
                //set offices to choice box, from where you can choose, to which offices will be send that request
                if (usersDatabase.getUser(s).getUserType().toString().equalsIgnoreCase("OFFICE")) {
                    officeList.add(usersDatabase.getUser(s).getUsername());
                }
            }

            officeBox.setItems(officeList);
            officeBox.getSelectionModel().selectFirst();

            HBox hBox1 = new HBox();
            HBox hBox2 = new HBox();
            hBox1.getChildren().addAll(proposer1, address1, idText1);
            hBox2.getChildren().addAll(proposer2, address2, idText2);

            proposer1.setEditable(false);
            proposer1.setText(((Owner) user.getOwner()).getName());
            address1.getItems().add(((Owner) user.getOwner()).getMutualAddress());
            address1.getSelectionModel().selectFirst();
            idText1.setText(((Owner) user.getOwner()).getOwnerID());
            propertyBox.getItems().setAll(newRequestController.setPropertyList((Owner) user.getOwner(), propertyList));

            proposer2.textProperty().addListener((observable, oldValue, newValue) -> {
                List<Owner> ownerByName = usersDatabase.findOwnerByName(newValue);
                if (ownerByName.size() == 1) {
                    if (newValue.equalsIgnoreCase(ownerByName.get(0).getName())) {
                        address2.setItems(FXCollections.observableArrayList((ownerByName.get(0)).getMutualAddress()));
                        idText2.setText((ownerByName.get(0)).getOwnerID());
                    }

                } else if (ownerByName.size() > 1) {
                    for (Owner o : ownerByName) {
                        addressList.add((o).getMutualAddress());
                    }
                    address2.setItems(addressList);

                    address2.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                        @Override
                        public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                            for (Owner o : ownerByName) {
                                if (address2.getItems().get((Integer) number2).equalsIgnoreCase(o.getMutualAddress())) {
                                    idText2.setText(o.getOwnerID());
                                }
                            }
                        }
                    });

                } else  {
                    address2.setItems(FXCollections.observableArrayList(""));
                    idText2.setText("");
                }
                nameTextfield.setText(newValue);
            });

            requestBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                public void changed(ObservableValue ov, Number value, Number new_value)
                {
                    if (requestBox.getItems().get((int) new_value).equalsIgnoreCase("Build permission") ||
                            requestBox.getItems().get((int) new_value).equalsIgnoreCase("Demolition permission") ||
                            requestBox.getItems().get((int) new_value).equalsIgnoreCase("Terrain treatment")) {

                        vBox.getChildren().clear();
                        vBox.getChildren().addAll(officeLabel, officeBox, textDeposit, requestTypeLabel, requestBox,
                                proposersLabel, hBox1, hBox2, favorLabel, nameTextfield, propertyBox, footer, sendBtn);
                        vBox.getChildren().removeAll(hBox2, favorLabel, nameTextfield);

                        if ( requestBox.getItems().get((int) new_value).equalsIgnoreCase("Demolition permission")) {
                            propertyList.clear();
                            propertyBox.getItems().setAll(newRequestController.setPropertyList((Owner) user.getOwner(),
                                    "realEstate", propertyList));
                        } else {
                            propertyList.clear();
                            propertyBox.getItems().setAll(newRequestController.setPropertyList((Owner) user.getOwner(),
                                    "land", propertyList));
                        }
                    } else {
                        vBox.getChildren().clear();
                        vBox.getChildren().addAll(officeLabel, officeBox, textDeposit, requestTypeLabel, requestBox,
                                proposersLabel, hBox1, hBox2, favorLabel, nameTextfield, propertyBox, footer, sendBtn);
                    }

                }
            });



            //list view... able to click multiple cells without ctrl
            propertyBox.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
                Node node = mouseEvent.getPickResult().getIntersectedNode();

                // go up from target node until list cell is found or it is clear
                // it was not a cell that was clicked
                while (node != null && node != propertyBox && !(node instanceof ListCell)) {
                    node = node.getParent();
                }

                // if is part of a cell or the cell,
                // handle event instead of using standard handling
                if (node instanceof ListCell) {
                    // prevent further handling
                    mouseEvent.consume();

                    ListCell cell = (ListCell) node;
                    ListView lv = cell.getListView();

                    // focus the listview
                    lv.requestFocus();

                    if (!cell.isEmpty()) {
                        // handle selection for non-empty cells
                        int index = cell.getIndex();
                        if (cell.isSelected()) {
                            lv.getSelectionModel().clearSelection(index);
                        } else {
                            lv.getSelectionModel().select(index);
                        }
                    }
                }
            });


            proposer1.setPromptText("Name Surname (YOU)");
            proposer2.setPromptText("Not necessary, left it blank");
            idText1.setEditable(false);
            idText1.setPromptText("ID will pop up there");
            idText2.setPromptText("ID will pop up there");
            idText2.setEditable(false);
            address1.setPrefWidth(100);
            address2.setPrefWidth(100);
            nameTextfield.setEditable(false);

            vBox.getChildren().addAll(officeLabel, officeBox, textDeposit, requestTypeLabel, requestBox,
                    proposersLabel, hBox1, hBox2, favorLabel, nameTextfield, propertyBox, footer, sendBtn);
        }

        this.getChildren().addAll(vBox);
        this.setAlignment(Pos.CENTER);
        this.setOrientation(Orientation.VERTICAL);
        this.setVgap(20);

        officeBox.setPrefWidth(280);
        textDeposit.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        sendBtn.setPrefWidth(280);
    }

    private void sceneEvents() {
        sendBtn.setOnAction(event -> {
            if (vBox.getChildren().size() == 13) {
                if ((idText1.getText() == null || idText1.getText().trim().isEmpty()) ||
                        (idText2.getText() == null || idText2.getText().trim().isEmpty()) ||
                        (address2.getValue() == null || address2.getValue().isEmpty()) ||
                        (requestBox.getValue() == null || requestBox.getValue().isEmpty()) ||
                        (propertyBox.getSelectionModel().getSelectedItems() == null || propertyBox.getSelectionModel().getSelectedItems().isEmpty())) {
                    PopUpAlert alert = new PopUpAlert(Alert.AlertType.WARNING, "Everything must be filled or selected");

                } else {
                    try {

                        newRequestController.sendRequest(usersDatabase.getUser(officeBox.getValue()),
                                usersDatabase.findUserByOwner(usersDatabase.findOwnerByID(idText2.getText())),
                                RequestType.getRequestFromAttribute(requestBox.getValue()), propertyBox.getSelectionModel().getSelectedItems(), stage, actualDate);

                    } catch (WrongInputException e) {
                        PopUpAlert alert = new PopUpAlert(Alert.AlertType.ERROR, e.getMessage());
                    }
                }
            } else if (vBox.getChildren().size() == 10) {
                if ((requestBox.getValue() == null || requestBox.getValue().isEmpty()) ||
                        (propertyBox.getSelectionModel().getSelectedItems() == null || propertyBox.getSelectionModel().getSelectedItems().isEmpty())) {
                    PopUpAlert alert = new PopUpAlert(Alert.AlertType.WARNING, "Everything must be filled or selected");

                } else {
                    try {

                        newRequestController.sendRequest(usersDatabase.getUser(officeBox.getValue()),
                                RequestType.getRequestFromAttribute(requestBox.getValue()), propertyBox.getSelectionModel().getSelectedItems(), stage, actualDate);

                    } catch (WrongInputException e) {
                        PopUpAlert alert = new PopUpAlert(Alert.AlertType.ERROR, e.getMessage());
                    }
                }
            } else {
                System.out.println("New request error");
            }

        });
    }
}
