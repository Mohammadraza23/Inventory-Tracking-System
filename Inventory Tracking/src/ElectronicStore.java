import java.util.Arrays;

public class ElectronicStore {
  public final int MAX_PRODUCTS = 10;
  private int curProducts;
  private String name;
  private Product[] stock;
  private double revenue;
  private int finalsale;
  private Product highest;
  private Product second;
  private Product third;
  private Product[] ls;
  private Product[] rank;
  private Product[] cur;

  public ElectronicStore(String initName) {
    stock = new Product[MAX_PRODUCTS];
    curProducts = 0;
    revenue = 0.0;
    name = initName;
    cur = new Product[0];
    finalsale = 0;
  }

  //Adds a product and returns true if there is space in the array
  //Returns false otherwise
  public boolean addProduct(Product newProduct) {
    if (curProducts < MAX_PRODUCTS) {
      stock[curProducts] = newProduct;
      curProducts++;
      return true;
    }
    return false;
  }

  public void add(Product p, int x) {
    if (Arrays.asList(cur).contains(p)) {
      if (p.getStockQuantity() == 1) {
        stock = arrayremove(stock, x);
        p.addcart();
        p.subtractstock();

      } else if (p.getStockQuantity() > 0) {
        p.addcart();
        p.subtractstock();
      }


    } else {

      if (p.getStockQuantity() == 1) {
        stock = arrayremove(stock, x);
        cur = addtop(cur, p);
        p.addcart();
        p.subtractstock();


      } else {
        cur = addtop(cur, p);
        p.addcart();
        p.subtractstock();
      }
    }

  }


  public Product[] addtop(Product[] add, Product p) {
    Product[] cart = new Product[add.length + 1];
    cart[0] = p;
    for (int i = 1; i < cart.length; i++) {
      cart[i] = add[i - 1];
    }
    return cart;
  }

  public void remove(Product p, int x) {
    if (p.getcQuantity() == 1 && p.getStockQuantity() != 0) {
      cur = arrayremove(cur, x);
      p.addstock();
      p.subtractcart();

    } else if (p.getStockQuantity() == 0 && p.getcQuantity() != 1) {
      stock = addtop(stock, p);
      p.addstock();
      p.subtractcart();


    } else if (p.getStockQuantity() == 0 && p.getcQuantity() == 1) {
      cur = arrayremove(cur, x);
      stock = addtop(stock, p);
      p.subtractcart();
      p.addstock();


    } else {
      p.subtractcart();
      p.addstock();
    }
  }


  public Product[] arrayremove(Product[] remove, int x) {
    Product[] ccart = new Product[remove.length - 1];
    for (int i = 0; i < x; i++) {
      ccart[i] = remove[i];
    }
    for (int i = x + 1; i < remove.length; i++) {
      ccart[i - 1] = remove[i];
    }
    return ccart;
  }


  public void removeempty() {
    int cnt = 0;
    for (int i = 0; i < stock.length; i++) {
      if (stock[i] != null) {
        cnt = cnt + 1;
      }
    }
    stock = new Product[cnt];
    for (int i = 0; i < stock.length; i++) {
      if (ls[i] != null) {
        stock[i] = ls[i];
      }
    }

  }

  public double price(Product[] p) {
    double total = 0;
    for (int i = 0; i < p.length; i++) {

      total = total + ((p[i].getPrice()) * p[i].getcQuantity());
    }
    return total;
  }


  public String[] prod(Product[] p) {
    int cnt = 0;

    for (int i = 0; i < p.length; i++) {

      if (p[i] != null) {

        cnt = cnt + 1;
      }
    }

    String[] prods = new String[cnt];
    for (int i = 0; i < p.length; i++) {

      if (p[i] != null) {
        prods[i] = p[i].toString();
      }
    }
    return prods;
  }


  public String[] cart(Product[] p) {
    String[] cart = new String[p.length];
    for (int i = 0; i < p.length; i++) {

      if (p[i] != null) {

        cart[i] = p[i].getcQuantity() + " x " + p[i].toString();
      }
    }
    return cart;
  }


  public void Complete(Product[] p) {
    for (int i = 0; i < p.length; i++) {
      revenue = revenue + p[i].sellUnits(p[i].getcQuantity());
    }

    cur = new Product[0];
  }


  public void goback() {
    int cnt = 0;

    for (int i = 0; i < stock.length; i++) {
      if (stock[i] != null) {
        cnt = cnt + 1;
      }
    }
    ls = new Product[cnt];
    for (int n = 0; n < ls.length; n++) {
      ls[n] = stock[n];
    }
  }

  public Product[] rank(Product[] p) {
    rank = new Product[3];
    highest = p[0];
    second = p[1];
    third = p[2];
    for (int i = 0; i < p.length; i++) {
      if (p[i] != null) {
        if (p[i].getSoldQuantity() > highest.getSoldQuantity()) {
          third = second;
          second = highest;
          highest = p[i];


        } else if (p[i].getSoldQuantity() > second.getSoldQuantity()) {
          third = second;
          second = p[i];


        } else if (p[i].getSoldQuantity() > third.getSoldQuantity()) {
          third = p[i];

        }

        rank[0] = highest;
        rank[1] = second;
        rank[2] = third;

      }
    }

    return rank;

  }

  //Sells 'amount' of the product at 'index' in the stock array
  //Updates the revenue of the store
  //If no sale occurs, the revenue is not modified
  //If the index is invalid, the revenue is not modified
  public void sellProducts(int index, int amount) {
    if (index >= 0 && index < curProducts) {
      revenue += stock[index].sellUnits(amount);
    }
  }

  //Prints the stock of the store
  public void printStock() {
    for (int i = 0; i < curProducts; i++) {
      System.out.println(i + ". " + stock[i] + " (" + stock[i].getPrice() + " dollars each, " + stock[i].getStockQuantity() + " in stock, " + stock[i].getSoldQuantity() + " sold)");
    }
  }

  public Product[] getLs() {
    return ls;
  }

  public void Salesup() {
    finalsale = finalsale + 1;
  }

  public int getFinalsale() {
    return finalsale;
  }

  public String getName() {
    return name;
  }

  public Product[] getcur() {
    return cur;
  }

  public double getRevenue() {
    return revenue;
  }

  public Product[] getGoods() {
    return stock;
  }


  public static ElectronicStore createStore() {
    ElectronicStore store1 = new ElectronicStore("Watts Up Electronics");
    Desktop d1 = new Desktop(100, 10, 3.0, 16, false, 250, "Compact");
    Desktop d2 = new Desktop(200, 10, 4.0, 32, true, 500, "Server");
    Laptop l1 = new Laptop(150, 10, 2.5, 16, true, 250, 15);
    Laptop l2 = new Laptop(250, 10, 3.5, 24, true, 500, 16);
    Fridge f1 = new Fridge(500, 10, 250, "White", "Sub Zero", 15.5, false);
    Fridge f2 = new Fridge(750, 10, 125, "Stainless Steel", "Sub Zero", 23, true);
    ToasterOven t1 = new ToasterOven(25, 10, 50, "Black", "Danby", 8, false);
    ToasterOven t2 = new ToasterOven(75, 10, 50, "Silver", "Toasty", 12, true);
    store1.addProduct(d1);
    store1.addProduct(d2);
    store1.addProduct(l1);
    store1.addProduct(l2);
    store1.addProduct(f1);
    store1.addProduct(f2);
    store1.addProduct(t1);
    store1.addProduct(t2);

    return store1;
  }
}
