package creditLine;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sun.javafx.css.StyleManager;

import creditLine.view.mvc.MainView;

import java.io.IOException;
import java.io.InputStream;


@SuppressWarnings("restriction")
@Configuration
public class ControllersConfiguration {

    @Bean(name = "mainView")
    public ViewHolder getMainView() throws IOException {
        return loadView("fxml/main.fxml");
    }
    
    
    @Bean
    public MainView getMainController() throws IOException {
        return (MainView) getMainView().getController();
    }
    

	protected ViewHolder loadView(String url) throws IOException {
        InputStream fxmlStream = null;
        try {
            fxmlStream = getClass().getClassLoader().getResourceAsStream(url);
            FXMLLoader loader = new FXMLLoader();
            loader.load(fxmlStream);
            Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
            StyleManager.getInstance().addUserAgentStylesheet(getClass().getResource("/application.css").toString());
            return new ViewHolder(loader.getRoot(), loader.getController());
        } finally {
            if (fxmlStream != null) {
                fxmlStream.close();
            }
        }
    }

    public class ViewHolder {
        private Parent view;
        private Object controller;

        public ViewHolder(Parent view, Object controller) {
            this.view = view;
            this.controller = controller;
        }

        public Parent getView() {
            return view;
        }

        public void setView(Parent view) {
            this.view = view;
        }

        public Object getController() {
            return controller;
        }

        public void setController(Object controller) {
            this.controller = controller;
        }
    }

}
