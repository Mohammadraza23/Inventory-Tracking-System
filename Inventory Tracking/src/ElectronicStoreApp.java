import javafx.scene.Scene;
import javafx.event.*;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.application.Application;


public class ElectronicStoreApp extends Application{

    private ElectronicStore model;
    private int selind;
    private int selprod;

    public void start(Stage primaryStage){

        model = ElectronicStore.createStore();
        model.goback();
        model.removeempty();


        Pane aPane = new Pane();
        ElectronicStoreView v = new ElectronicStoreView();
        v.update(model);


        //event handler for listviews
        v.getmostpop().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                v.getRemoveCart().setDisable(true);
                v.getAddToCart().setDisable(true);
            }
        });

        v.getcurls().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                if(v.getcurls().getSelectionModel().getSelectedIndex() >- 1 ){
                    v.getRemoveCart().setDisable(false);
                }
                v.getAddToCart().setDisable(true);
            }
        });

        v.getstockls().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                if(v.getstockls().getSelectionModel().getSelectedIndex() >- 1){
                    v.getAddToCart().setDisable(false);
                    v.getRemoveCart().setDisable(true);
                }
            }
        });


        v.getRemoveCart().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                selind = v.getcurls().getSelectionModel().getSelectedIndex();
                model.remove(model.getcur()[selind],selind);
                v.update(model);
            }
        });

        v.getcomplete().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                model.Salesup();
                model.Complete(model.getcur());
                v.update(model);
            }
        });


        v.getreset().setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent actionEvent) {
                model = ElectronicStore.createStore();
                model.goback();
                model.removeempty();
                v.update(model);

                v.getAddToCart().setDisable(true);
                v.getRemoveCart().setDisable(true);
                v.getcomplete().setDisable(true);
            }
        });



        v.getAddToCart().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                selprod = v.getstockls().getSelectionModel().getSelectedIndex();
                model.add(model.getGoods()[selprod],selprod);
                v.update(model);
            }
        });



        aPane.getChildren().addAll(v);
        primaryStage.setTitle(" Electronic Store Application - " + model.getName());
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(aPane));
        primaryStage.show();


    }
    public static void main(String[] args){
        launch(args);

    }
}
