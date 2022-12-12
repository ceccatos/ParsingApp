package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import parser.RecordTrackLibrary;
import java.io.IOException;

public class Controller {

	@FXML
	private VBox pnItems = null;
	@FXML
	private Button btnLibrary;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnParse;

	@FXML
	private Button btnExit;

	@FXML
	private Pane pnlParser;

	@FXML
	private Pane pnlAdd;

	@FXML
	private Pane pnlLibrary;

	public void handleClicks(ActionEvent actionEvent) {
		if (actionEvent.getSource() == btnParse) {
			pnlParser.setStyle("-fx-background-color : #282a36");
			pnlParser.toFront();
		}
		if (actionEvent.getSource() == btnExit) {
			System.exit(0);
		}
		if (actionEvent.getSource() == btnLibrary) {
			pnlLibrary.setStyle("-fx-background-color : #282a36");
			pnlLibrary.toFront();
		}
		if (actionEvent.getSource() == btnAdd) {
			pnlAdd.setStyle("-fx-background-color : #282a36");
			pnlAdd.toFront();
		}
	}

	public void refreshLibrary(RecordTrackLibrary recordTrackLibrary) {

		Node[] nodes = new Node[recordTrackLibrary.size()];

		for (int i = 0; i < nodes.length; i++) {
			try {

				final int j = i;
				nodes[i] = FXMLLoader.load(getClass().getResource("Item.fxml"));

				// give the items some effect

				nodes[i].setOnMouseEntered(event -> {
					nodes[j].setStyle("-fx-background-color : #44475a");
				});
				nodes[i].setOnMouseExited(event -> {
					nodes[j].setStyle("-fx-background-color : #282a36");
				});
				pnItems.getChildren().add(nodes[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
