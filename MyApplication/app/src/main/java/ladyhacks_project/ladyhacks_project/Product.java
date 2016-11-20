package ladyhacks_project.ladyhacks_project;


public class Product {

    int product_barcode;
    String product_name;
    String product_exp;


    public Product (){    }

    public Product (String product_name, String product_exp){
      //  this.product_barcode = product_id;
        this.product_name = product_name;
        this.product_exp = product_exp;
    }

    //void setProduct_barcode(int id){this.product_barcode=id;}

    void setProduct_name(String name){this.product_name=name;}

    void setProduct_exp(String exp){this.product_exp=exp;}

    //public int getProduct_barcode(){return product_barcode;}

    public String getProduct_name() {return product_name;}

    public String getProduct_exp() {return product_exp;}

}

