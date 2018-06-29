package creditLine.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UiUtils {
	
	public UiUtils() {
		
	}

	public void errorAlert(String title, String header, String Context) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(Context);

		alert.showAndWait();
	}
	
	public void informationAlert(String title, String header, String Context) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(Context);

		alert.showAndWait();
	}
}
