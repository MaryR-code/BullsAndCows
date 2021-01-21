package bullsandcows;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;

public class MainController implements Initializable {
    public Spinner<Integer> spinner1;
    public Spinner<Integer> spinner2;
    public Spinner<Integer> spinner3;
    public Spinner<Integer> spinner4;
    public TableView<Turn> history;
    public Button goButton;
    private List<Integer> myNumbers;

    // этот метод вызывается при старте
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        goButton.disableProperty().bind(
                Bindings.createBooleanBinding(() -> {
                            var set = new HashSet<Integer>();
                            set.add(spinner1.getValue());
                            set.add(spinner2.getValue());
                            set.add(spinner3.getValue());
                            set.add(spinner4.getValue());

                            return set.size() < 4;
                        },
                        spinner1.valueProperty(),
                        spinner2.valueProperty(),
                        spinner3.valueProperty(),
                        spinner4.valueProperty()
                )
        );

        resetGame();
    }

    private void resetGame() {

        history.getItems().clear();
        spinner1.getValueFactory().setValue(5);
        spinner2.getValueFactory().setValue(5);
        spinner3.getValueFactory().setValue(5);
        spinner4.getValueFactory().setValue(5);

        var rand = new Random();

        // цикл работает пока в Set не будет записано 4 разных числа
        var set = new LinkedHashSet<Integer>();
        while (set.size() < 4) {
            var n = rand.nextInt(10);
            set.add(n);
        }

        // myNumbers = new ArrayList<>(set);
        // копируем Set в ArrayList (надо получить индекс)
        myNumbers = List.copyOf(set);

        System.out.println(myNumbers);
    }

    public void doTurn() {

        var n1 = spinner1.getValue();
        var n2 = spinner2.getValue();
        var n3 = spinner3.getValue();
        var n4 = spinner4.getValue();
        var guess = "" + n1 + n2 + n3 + n4;

        var userNumbers = List.of(n1, n2, n3, n4);

        int bulls = 0;
        int cows = 0;

        // TODO count bulls
        // FIXME count bulls

        for (int i = 0; i < 4; i++) {
            int myN = myNumbers.get(i);
            for (int j = 0; j < 4; j++) {
                int usrN = userNumbers.get(j);
                if (myN == usrN) {
                    if (i == j) {
                        bulls++;
                    } else {
                        cows++;
                    }
                }
            }
        }

        var turn = new Turn();
        turn.setNr(history.getItems().size() + 1);
        turn.setGuess(guess);
        turn.setBulls(bulls);
        turn.setCows(cows);

        history.getItems().add(0, turn);    // добавление в начало списка

        if (bulls == 4) {
            var winAlert = new Alert(Alert.AlertType.INFORMATION);
            winAlert.setContentText("You won this match!");
            winAlert.setTitle("Winner");
            winAlert.setHeaderText("Congratulation");
            winAlert.showAndWait();
            resetGame();
        }
    }
}
