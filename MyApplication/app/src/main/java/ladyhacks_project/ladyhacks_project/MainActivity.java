package ladyhacks_project.ladyhacks_project;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /* This Android app is designed to prevent food waste by keeping 
    track of you groceries and when your food expires. For this main calendar page, there
    is a display for the current date and have a menu button on the top right 
    hand corner.
    The app uses 2 lists: one to store what items you have added to your 
    fridge and the other to store all the products you may choose from. 
    We will make the assumptions what the second list covers all the options
    the user would put in their fridge.
    */

    List<Product> product_list; //product_list: list of all possible items (and their expiry date) you may select to add to your fridge.

    List<Product> added_list;  // added-list: items added to user's fridge and their expiry date
    String text=""; //text: created to repeatedly use after change of value

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    /* This module is made to send user notifications about their current grocery status and 
    stores items to the list of available products and list of products you own. All arraylists shown are sample size.
    */
        super.onCreate(savedInstanceState); //This module adds on to the parent class code
        setContentView(R.layout.activity_main);// and uses the activity layout for this page.


            //Each product has a name and their expiry date defined. 
            //Ideally it covers all possible item options user would want to seleect.
            Product p1 = new Product("Milk", 7);
            Product p2 = new Product("Bread", 3);

            product_list = new ArrayList<>();//product_list is made with an ArrayList because it is easily resizable.
            product_list.add(p1); //Products are added in this format because of the small sample size;
            product_list.add(p2); //a for loop would de implemented if the sample size grew.

            List<Product> added_list = new ArrayList<>();//added_list is made with an ArrayList because it is easily resizable.

            added_list.add(p1);//Products are added in this format to because of the small sample size;
            added_list.add(p2);//a for loop would de implemented if the sample size grew.


            //Messages are created to help inform users the status of their items.
            List<String> strings = new ArrayList<>(added_list.size());//strings: list of all possible messages displayed in notifications depending on what is inside the user's list.
            if(added_list.size()==0){ //if there are is nothing in the user's list,
                strings.add("No grocery was not bought this week"); //no groceries were bought;
            }
            else if(added_list.size()==1){ //if there are is one item in the user's list,
                //case analysis on the single item bought
                if((added_list.get(0)).getProduct_name().equals("Milk")){ //if that item is Milk,
                    strings.add("Bread was not bought this week"); //then no bread was bought
                    strings.add((added_list.get(0)).getProduct_name()+ " will expire in: " + (added_list.get(0)).getProduct_exp() + " days");//and user is notified the when the milk will expire
                }
                else{//if that item is not Milk,
                    strings.add("Milk was not bought this week");//then no milk was bought
                    strings.add((added_list.get(0)).getProduct_name()+ " will expire in: " + (added_list.get(0)).getProduct_exp() + " days");//and user is notified the when the bread will expire
                }
            }
            else if(added_list.size()==2){//if there are 2 items in the user's list,
                for (Product object :added_list) { //for each object in the user's list
                     strings.add(object.getProduct_name() + " will expire in: " + object.getProduct_exp() + " days"); //notify user when the object will expire

                }
            }

            //Notifications are created to alert the user about the app and display message(s)
            //first part of message
            for(String s:strings) {//for each message in the string array
                NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this); 
                nBuilder.setSmallIcon(R.mipmap.ic_launcher) //the remainder uses an icon
                        .setContentTitle("groceReemindr") //with the app name groceReemindr as the title
                        .setContentText(strings.get(0)) //and displays the stored message to the user so they know their product's status.
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(s)); //The app uses large-format notifications display messsage.
                Notification notif = nBuilder.build();
                NotificationManager nManager = (NotificationManager) this.getSystemService(this.NOTIFICATION_SERVICE); //Gaining the ability to notify user of background events

                nManager.notify(001, notif);//and is shown in the status bar of user's phone

            }
        //repeat above steps, but with second part of message
        for(String s:strings) {
            NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this);
            nBuilder.setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("groceReemindr")
                    .setContentText(strings.get(1))
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(s));
            Notification notif = nBuilder.build();
            NotificationManager nManager = (NotificationManager) this.getSystemService(this.NOTIFICATION_SERVICE);

            nManager.notify(002, notif);

        }


        text="Date ";
            //diaplays the current date
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy"); //Using simple date format, 
            Calendar c = Calendar.getInstance(); //access the date
            String formattedDate = df.format(c.getTime()); // and time
            text=text+formattedDate; //to create message with all necessary information

        TextView textElement=(TextView) findViewById(R.id.textView2); //Uses a text view
        textElement.setText(text); //to present the date
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //Creating the Pop-up menu 
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu); //The display of the pop-up menu is created with all the different page options
        return true; 
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handling selected item on the pop-up menu
        switch (item.getItemId()) { // for the selected item
        // case analysis on selected item, when a operation finishes, it notifies the program if something was chosen.
            case R.id.menu_addNewProduct: // user wants to add a new product that they purchased
                Intent i = new Intent(MainActivity.this,AddProductActivity.class); 
                i.putExtra("list", (Serializable) product_list); //With the product lists information passed on,
                startActivity(i); //we enter the page to add new products.
                return true;
            case R.id.menu_editProduct:// user wants to edit a product
                Intent i2 = new Intent(MainActivity.this,EditActivity.class);
                i2.putExtra("list", (Serializable) product_list);//With the product lists information passed on,
                startActivity(i2);//we enter the page to edit the products in our fridge
                return true;
            case R.id.menu_calender:// user wants to see the calendar of product 
                Intent i3 = new Intent(MainActivity.this,MainActivity.class); 
                startActivity(i3); //remain on the same page
                return true;
            default:
                return super.onOptionsItemSelected(item); //notifies program that a menu selection was not made
        }

    }
}
