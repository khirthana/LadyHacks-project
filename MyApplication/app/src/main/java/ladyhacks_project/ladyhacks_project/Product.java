package ladyhacks_project.ladyhacks_project;


import java.io.Serializable;

public class Product implements Serializable{

    String product_name;
    int product_exp;


    public Product (){    }

    public Product (String product_name, int product_exp){
        this.product_name = product_name;
        this.product_exp = product_exp;
    }


    void setProduct_name(String name){this.product_name=name;}

    void setProduct_exp(int exp){this.product_exp=exp;}

    public String getProduct_name() {return product_name;}

    public int getProduct_exp() {return product_exp;}

}

