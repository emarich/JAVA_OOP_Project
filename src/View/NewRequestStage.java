package View;

import CadasterObjects.Land;
import CadasterObjects.RealEstate;
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
                    RequestType.EXCHANGE.getDescription(),RequestType.MARRIAGE.getDescription(),
                    RequestType.BUILD.getDescription(), RequestType.DEMOLITION.getDescription(),
                    RequestType.TERRAIN.getDescription()));

    private ListView<String> propertyBox = new ListView<>();
    private ListView<String> selected = new ListView<>();
    private ObservableList<String> propertyList = FXCollections.observableArrayList();

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private LocalDate localDate = LocalDate.now();
    private Text footer = new Text("Date: "+dtf.format(localDate));

    private Button sendBtn = new Button("Send");

    public NewRequestStage(User user, Database usersDatabase) throws Exception {
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

        propertyBox.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        if (user.getOwner() instanceof Owner) {
            for (String s : usersDatabase.getUsersDataHM().keySet()) {
                if (usersDatabase.getUser(s).getUserType().toString().equalsIgnoreCase("OFFICE")) {
                    officeList.add(usersDatabase.getUser(s).getUsername());
                }
            }
            officeBox.setItems(officeList);
            officeBox.getSelectionModel().selectFirst();

            //propertyBox.setPrefHeight(100);

            HBox hBox1 = new HBox();
            HBox hBox2 = new HBox();
            hBox1.getChildren().addAll(proposer1, address1, idText1);
            hBox2.getChildren().addAll(proposer2, address2, idText2);

            proposer1.textProperty().addListener((observable, oldValue, newValue) -> {
                Owner owner = (Owner) user.getOwner();
                if (newValue.equalsIgnoreCase((owner).getName())) {
                    address1.setItems(FXCollections.observableArrayList((owner).getMutualAddress()));
                    idText1.setText((owner).getOwnerID());
                    if (!(owner.getOwnedLands().isEmpty()) || owner.getOwnedLands()!=null){
                        for (Land l : owner.getOwnedLands()) {
                            propertyList.add(l.toString());
                        }
                    }
                    if (!(owner.getOwnedRE().isEmpty()) || owner.getOwnedRE()!=null){
                        for (RealEstate l : owner.getOwnedRE()) {
                            propertyList.add(l.toString());
                        }
                    }
                    if (propertyList.size() > 0) {
                        propertyBox.getItems().setAll(propertyList);
                    }

                } else {
                    address1.setItems(FXCollections.observableArrayList(""));
                    idText1.setText("");
                }
            });

            proposer2.textProperty().addListener((observable, oldValue, newValue) -> {
                List<Owner> ownerByName = usersDatabase.findOwnerByName(newValue);
                System.out.println(ownerByName.size());
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
            });

            propertyBox.addEventFilter(MouseEvent.MOUSE_PRESSED, evt -> {
                Node node = evt.getPickResult().getIntersectedNode();

                // go up from the target node until a list cell is found or it's clear
                // it was not a cell that was clicked
                while (node != null && node != propertyBox && !(node instanceof ListCell)) {
                    node = node.getParent();
                }

                // if is part of a cell or the cell,
                // handle event instead of using standard handling
                if (node instanceof ListCell) {
                    // prevent further handling
                    evt.consume();

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


            proposer1.setPromptText("Name Surname (you)");
            proposer2.setPromptText("Not necessary, left it blank");
            idText1.setEditable(false);
            idText1.setPromptText("ID will pop up there");
            idText2.setPromptText("ID will pop up there");
            idText2.setEditable(false);
            address1.setPrefWidth(100);
            address2.setPrefWidth(100);

            nameTextfield.setPromptText("Name Surname");


            vBox.getChildren().addAll(officeLabel, officeBox, textDeposit, proposersLabel, hBox1, hBox2,
                    favorLabel, nameTextfield, requestTypeLabel, requestBox, propertyBox, footer, sendBtn);
        }

        this.getChildren().addAll(vBox);
        this.setAlignment(Pos.CENTER);
        this.setOrientation(Orientation.VERTICAL);
        this.setVgap(20);

        officeBox.setPrefWidth(280);
        textDeposit.setFont(Font.font("Verdana", FontWeight.BOLD, 30));

        sendBtn.setPrefWidth(280);
    }

    private void sceneEvents () {

    }
}
