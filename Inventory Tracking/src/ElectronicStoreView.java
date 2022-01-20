import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.collections.FXCollections;

public class ElectronicStoreView extends Pane {

    private Label mostpop;
    private Label sStock;
    private Label sSummary;
    private Label rev;
    private Label currcart;
    private Label price;
    private Label sales;
    private Label sale2;

    private Button reset;
    private Button cAdd;
    private Button cRemove;
    private Button complete;

    private TextField salest;
    private TextField revenue;
    private TextField salest2;

    private ListView mostpopitem;
    private ListView storeS;
    private ListView Currc;


    public ElectronicStoreView(){

        setPrefSize(800,400);

        //labels
        mostpop = new Label("Most Popular Items:");
        mostpop.relocate(35,135);

        sStock = new Label(" Store Stock:");
        sStock.relocate(275,-120);

        sSummary = new Label(" Store Summary:");
        sSummary.relocate(5,20);

        rev = new Label("Revenue:");
        rev.relocate(18,75);

        currcart = new Label("Current Cart ($");
        currcart.relocate(565,20);

        price = new Label(" 0.00 ");
        price.relocate(648,20);

        sales = new Label(" # Sales:");
        sales.relocate(23,45);

        sale2 = new Label("$/ Sale:");
        sale2.relocate(25,105);

        //buttons
        reset = new Button(" Reset Store ");
        reset.relocate(30,340);
        reset.setPrefSize(120,50);
        reset.setDisable(false);

        cAdd = new Button(" Add to Cart ");
        cAdd.setPrefSize(120,50);
        cAdd.relocate(260,340);
        cAdd.setDisable(true);
        cAdd.setAlignment(Pos.CENTER);


        cRemove = new Button(" Remove from Cart ");
        cRemove.setPrefSize(145,50);
        cRemove.relocate(490,340);
        cRemove.setDisable(true);
        cRemove.setAlignment(Pos.CENTER);

        complete = new Button(" Complete Sale ");
        complete.relocate(635,340);
        complete.setPrefSize(145,50);
        complete.setDisable(true);
        complete.setAlignment(Pos.CENTER);

        //TextFields
        salest = new TextField();
        salest.setText(" 0 ");
        salest.relocate(70,40);
        salest.setPrefSize(110,20);
        salest.setEditable(false);

        revenue = new TextField();
        revenue.setText(" 0.00 ");
        revenue.relocate(70,70);
        revenue.setPrefSize(110,20);
        revenue.setEditable(false);

        salest2 = new TextField();
        salest2.setText(" N/A ");
        salest2.relocate(70,100);
        salest2.setPrefSize(110,20);
        salest2.setEditable(false);


        //listview
        mostpopitem = new ListView<String>();
        mostpopitem.relocate(10,160);
        mostpopitem.setPrefSize(170,170);

        storeS = new ListView<String>();
        storeS.relocate(190,40);
        sStock.setPrefSize(290,290);

        Currc = new ListView<String>();
        Currc.relocate(490,40);
        Currc.setPrefSize(290,290);


        getChildren().addAll(sSummary,sales,salest,rev,revenue, sale2,salest2,mostpop,mostpopitem,reset,sStock,storeS,
                cAdd,currcart,price,Currc,cRemove,complete);
    }

    public void update(ElectronicStore model){
        if(model.getFinalsale() == 0){
            salest2.setText(" N/A ");


        } else {
            salest2.setText(String.valueOf((model.getRevenue()/model.getFinalsale())));


        }if(model.getcur().length == 0){
            complete.setDisable(true);


        } else{
            complete.setDisable(false);
        }


        price.setText(String.valueOf(model.price(model.getcur())) + " )" );
        storeS.setItems(FXCollections.observableArrayList(
                model.prod(model.getGoods())));

        mostpopitem.setItems(FXCollections.observableArrayList(

                model.prod(model.rank(model.getLs()))));

        revenue.setText(String.valueOf(model.getRevenue()));
        Currc.setItems(FXCollections.observableArrayList(model.cart(model.getcur())));
        salest.setText(String.valueOf(model.getFinalsale()));
    }

    public ListView getstockls(){ return storeS; }
    public ListView getcurls(){ return Currc; }
    public ListView getmostpop(){ return mostpopitem;}
    public Button getreset(){ return reset;}
    public Button getcomplete(){ return complete;}
    public Button getAddToCart(){ return cAdd; }
    public Button getRemoveCart(){ return cRemove; }
}

