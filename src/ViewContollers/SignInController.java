package ViewContollers;

import OtherFunctionality.PopUpAlert;
import UserObject.Database;
import View.*;
import javafx.scene.control.*;
import javafx.stage.Stage;


/**
 * Controller pre SignInScene (GUI)
 */
public class SignInController {

    private Database usersDatabase;

    //Constructor
    public SignInController(Database usersDatabase) {
        this.usersDatabase = usersDatabase;
    }

    /**
     * Pri vybratí adekvátneho typu prihlasovaného použivateľa a kontrole existujúceho uživateľského mena sa metódou
     * @see #switchScenes(ChoiceBox, String, Stage)
     * prepne na adekvátny stage.
     *
     * @param signIn po stlačení daného tlačidla sa spustí akcia metódy
     * @param username prihlasovacie meno uživateľa (Textfield s daným údajom)
     * @param password heslo uživateľa (Textfield s daným údajom)
     * @param userType  typ uživateľa (ChoiceBox, z ktorého sa vyberá typ uživateľa)
     * @param primaryStage  hlavný stage
     */
    public void buttonClicked(Button signIn, TextField username, TextField password, ChoiceBox<String> userType,
                              Stage primaryStage) {
        signIn.setOnAction(e -> {

            //check, if text fields are filled
            if((username.getText() == null || username.getText().trim().isEmpty()) ||
                    (password.getText() == null || password.getText().trim().isEmpty())) {
                PopUpAlert alert = new PopUpAlert(Alert.AlertType.ERROR, "Username and password fields must be filled");
                alert.setTitle("Missing username or password");

            } else {
                //if user exists under concrete username, switch to correct scene
                if (usersDatabase.existingUser(username.getText()) &&
                        userType.getValue().equalsIgnoreCase(usersDatabase.getUser(username.getText()).getUserType().toString())){
                    switchScenes(userType, username.getText(), primaryStage);
                //if user isn't found, or if inputs are incorrect
                } else {
                    PopUpAlert alert = new PopUpAlert(Alert.AlertType.WARNING, "Check if username or password or " +
                            "selected user type is correct");
                    alert.setTitle("Incorrect input");
                }
            }
        });
    }

    /**
     * Prepne na adekvátny stage podľa typu prihlasovaného používateľa.
     *
     * @exception Exception ak vznikne nejaká chyba pri prepínaní podľa uživateľa, prepne na {@link GuestPrimaryScene}
     *
     * @param usertype typ prihlasovaného užívateľa
     * @param username uživateľské meno
     * @param primaryStage hlavný stage
     */
    //choose which scene will be showing
    private void switchScenes (ChoiceBox<String> usertype, String username, Stage primaryStage) {
        if (usertype.getValue().equalsIgnoreCase("Citizen")) {
            UserPrimaryScene userStage = new UserPrimaryScene(primaryStage, username, usersDatabase);

        } else if (usertype.getValue().equalsIgnoreCase("Office")){
            OfficePrimaryScene officeStage = new OfficePrimaryScene(primaryStage, username, usersDatabase);

        } else {
            try {
                GuestPrimaryScene guestPrimaryScene = new GuestPrimaryScene(primaryStage, usersDatabase);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Sleduje zmeny výberu v ChoiceBoxe. Ak na ChoiceBoxe zaklikneme hodnotu "Guest", automaticky nás to
     * prepne na {@link GuestPrimaryScene}.
     * @param userTypeBox sledovaná hodnota ChoiceBoxu
     * @param primaryStage hlavný stage
     */
    //if guest is checked in check box, automatically change scenes
    public void checkGuest(ChoiceBox<String> userTypeBox, Stage primaryStage) {
        userTypeBox.setOnAction(e -> {
            if (userTypeBox.getValue().equalsIgnoreCase("Guest")) {
                GuestPrimaryScene guestStage = new GuestPrimaryScene(primaryStage, usersDatabase);
            }
        });
    }

    /**Ak je zakliknutý argument <code>reqisterBtn</code>, vyvoláme zobrazenie ďalšieho okna, kde sa uživateľ
     * vie registrovať
     * @param registerBtn metóda pre nakliknutie daného tlačidla
     */
    //switch to register formula
    public void switchRegisterStage(Button registerBtn) {
        registerBtn.setOnAction(e -> {
            try {
                RegisterStage registerStage = new RegisterStage(usersDatabase);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}
