package ladyhacks_project.ladyhacks_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddProductActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
/*This page for adding new products which are now in your fridge. Once a product is 
added, the app will immediately set today as the start for the the duration
for that item. User is able to choose the specific item and have the option
to change the default expiry date to their liking. The list of user's current items
will show on the bottom of the page, so they know the item has been added.
*/
    ArrayList<Product> product_list = new ArrayList<Product>();//product_list: list of all possible items (and their expiry date) you may select to add to your fridge.

    ArrayList<Product> added_list = new ArrayList<Product>();// added-list: items added to user's fridge and their expiry date

    Spinner list; //list: drop down showing all possible options user can choose from

    String product_name;// product_name: name of the product

    int expiry_date;// expiry_date: their expiry date


    TextView text;//text: what will be displayed


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*This module is used to create the add product page.
        */
        super.onCreate(savedInstanceState);//This module adds on to the parent class code
        setContentView(R.layout.add_activity);// and uses the add activity layout for this page.

        Intent callingIntent = getIntent(); 
        product_list= (ArrayList<Product>) callingIntent.getSerializableExtra("list"); //having transfereed the
        //product list information,

        //the products are then displayed on the spinner. A for loop is used
        //so that the following code is not dependent on the size of the product.
        List<String> strings = new ArrayList<>(product_list.size());
        for (Product object : product_list) { //for all possible product choices,
            strings.add(object.getProduct_name()); //display it as an option on the spinner
        }

        //a drop down view is used as the layout for the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, strings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //the view for the spinner for the product is used to display
        list = (Spinner)findViewById(R.id.spinner);
        list.setAdapter(adapter);
        list.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        /*This module is used to handle the item which has been selected.
        */
        Spinner spinner = (Spinner) parent;
        if(spinner.getId() == R.id.spinner) { //after a item is selected from the product drop down list,
            List<String> dates = new ArrayList<>(product_list.size()); //dates : options to set expiry date

            //an if statement is made since the sample size is only 2, 
            //with larger sample sizes it would be a good idea to create a for loop.
            if (position == 0) { //if milk was selected,
                product_name = (product_list.get(0)).getProduct_name();
                Product object = product_list.get(0); //then the selected object will be recorded as milk
                dates.add(Integer.toString(object.getProduct_exp())); //and the expiry date is set to the default of milk
            } else { //if bread it selected

                product_name = (product_list.get(1)).getProduct_name();
                Product object = product_list.get(1);//then the selected object will be recorded as bread
                dates.add(Integer.toString(object.getProduct_exp()));//and the expiry date is set to the default of bread
            }

            //below are the options to select for te expiry date
            //again manual done, but a for loop would have been better in case the number of options change.
            dates.add("1");
            dates.add("2");
            dates.add("3");
            dates.add("4");
            dates.add("5");
            dates.add("6");
            dates.add("7");

            //a drop down view is used as the layout for the spinner
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dates);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //the view for the spinner for the expiry date is used to display
            list = (Spinner) findViewById(R.id.spinner_day);
            list.setAdapter(adapter);
        }
        else if(spinner.getId() == R.id.spinner_day){//after the number of day(s) is selected from the expiry date drop down list,
            expiry_date=position; //that number is set to the user's desired expiry date.
        }
    }



    public void   add_item_button(View v){
        //This method creates the added list and displays it
        if(expiry_date==0){ //if no expiry date was chosen,
            if(product_name.equals("Milk")){//and if the product is milk, 
                expiry_date=7; //set it to the default of 7 days until expiry
            }else{//If the product is bread,
                expiry_date=3;//set it to the default of 7 days until expiry
            }
        }
        Product added_p=new Product(product_name,expiry_date); //added_p : product chosen by the user that they currently own
        added_list.add(added_p); //this product is added to the list 
        text=(TextView) findViewById(R.id.added_list);
        String content="";
        for(Product p:added_list){
            content=content+(p.getProduct_name()+" "+p.getProduct_exp()+" days \n"); //then displayed to show the user 
        }
        text.setText(content);
    }


    public void   done_button(View v){
        //button allowing the user to return back to the main calendar page once finished adding their new product(s)
        //It display calendar page when it's clicked.

        Intent i3 = new Intent(AddProductActivity.this,CalendarActivity.class);
        i3.putExtra("list", (Serializable) product_list);//Sending product list information
        i3.putExtra("added_list", (Serializable) added_list);// and user's list of products owned
        startActivity(i3); //back to the main page
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        //module used to display the menu on the top right of the screen so the user can go to any page
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);//The display of the pop-up menu is created with all the different page options
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handling selected item on the pop-up menu
        switch (item.getItemId()) {
             // case analysis on selected item, when a operation finishes, it notifies the program if something was chosen.
            case R.id.menu_addNewProduct:// user wants to add a new product that they purchased
                Intent i = new Intent(AddProductActivity.this,AddProductActivity.class);
                i.putExtra("list", (Serializable) product_list);//With the product lists information passed on,
                i.putExtra("added_list", (Serializable) added_list);//and the list on the user's currently owned products,
                startActivity(i);//we reload current page.
                return true;
            case R.id.menu_editProduct:
                Intent i2 = new Intent(AddProductActivity.this,EditActivity.class);
                i2.putExtra("list", (Serializable) product_list);//With the product lists information passed on,
                i2.putExtra("added_list", (Serializable) added_list);//and the list on the user's currently owned products,
                startActivity(i2);//we enter the page to edit the products in our fridge
                return true;
            case R.id.menu_calender:
                Intent i3 = new Intent(AddProductActivity.this,CalendarActivity.class);
                i3.putExtra("list", (Serializable) product_list);//With the product lists information passed on,
                i3.putExtra("added_list", (Serializable) added_list);//and the list on the user's currently owned products,
                startActivity(i3);//we enter the page with the calendar view
                return true;
            default:
                return super.onOptionsItemSelected(item);//notifies program that a menu selection was not made
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //nothing on this page was selected, so nothing happens

    }
}
