//Base class for all products the store will sell
public class Product{
    private double price;
    private int stockQuantity;
    private int soldQuantity;
    private int cQuantity = 0;


    public Product(double initPrice, int initQuantity){
        price = initPrice;
        stockQuantity = initQuantity;
    }

    public int getStockQuantity(){
        return stockQuantity;
    }
    public int getSoldQuantity(){
        return soldQuantity;
    }
    public double getPrice(){
        return price;
    }
    public int getcQuantity(){return cQuantity;}
    public void addstock(){stockQuantity += 1;}
    public void addcart(){
        cQuantity += 1;
    }
    public void subtractcart(){cQuantity = cQuantity - 1;}
    public void subtractstock(){stockQuantity = stockQuantity - 1;}


    //Returns the total revenue (price * amount) if there are at least amount items in stock
    //Return 0 otherwise (i.e., there is no sale completed)
    public double sellUnits(int amount){
        if(amount > 0){
            soldQuantity += amount;
            cQuantity = 0;
            return price * amount;
        }
        return 0.0;
    }

}