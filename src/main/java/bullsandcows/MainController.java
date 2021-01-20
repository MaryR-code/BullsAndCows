package bullsandcows;

import javafx.event.ActionEvent;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class MainController {
    public Spinner<Integer> spinner1;
    public Spinner<Integer> spinner2;
    public Spinner<Integer> spinner3;
    public Spinner<Integer> spinner4;
    public TableView<Turn> history;
    public ArrayList<Turn> turns = new ArrayList<>();

    public void doTurn() {

        var nr = turns.size() + 1;

        var n1 = spinner1.getValue();
        var n2 = spinner2.getValue();
        var n3 = spinner3.getValue();
        var n4 = spinner4.getValue();

        var guess = "" + n1 + n2 + n3 + n4;

        var turn = new Turn();
        turn.setNr(nr);
        turn.setGuess(guess);

        turns.add(turn);
        history.getItems().add(turn);

    }
}
