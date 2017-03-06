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
    track of you groceries and when your food expires. It uses
    lists to keep track of what items you have added to your 
    fridge.
    */

    List<Product> product_list; // all possible options (with expiry date) user can select to add to their list

    List<Product> added_list;  // items added to user's fridge and their expiry date
    String text=""; //created to repeatedly used after change of value

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    /* this module is made to send user notifications about their current grocery status. 
    */
        super.onCreate(savedInstanceState); //adding on to the parent class code
        setContentView(R.layout.activity_main);// and using the main layout for this page


            //samples size for the product list. Each product has a name and their expiry date defined
            Product p1 = new Product("Milk", 7);
            Product p2 = new Product("Bread", 3);

            product_list = new ArrayList<>();
            product_list.add(p1);
            product_list.add(p2);

            List<Product> added_list = new ArrayList<>();

            added_list.add(p1);
            added_list.add(p2);


//notification
            List<String> strings = new ArrayList<>(added_list.size());
            if(added_list.size()==0){
                strings.add("No grocery was not bought this week");
            }
            else if(added_list.size()==1){
                if((added_list.get(0)).getProduct_name().equals("Milk")){
                    strings.add("Bread was not bought this week");
                    strings.add((added_list.get(0)).getProduct_name()+ " will expire in: " + (added_list.get(0)).getProduct_exp() + " days");
                }
                else{
                    strings.add("Milk was not bought this week");
                    strings.add((added_list.get(0)).getProduct_name()+ " will expire in: " + (added_list.get(0)).getProduct_exp() + " days");
                }
            }
            else if(added_list.size()==2){
                for (Product object :added_list) {
                     strings.add(object.getProduct_name() + " will expire in: " + object.getProduct_exp() + " days");

                }
            }


            for(String s:strings) {
                NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this);
                nBuilder.setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("groceReemindr")
                        .setContentText(strings.get(0))
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(s));
                Notification notif = nBuilder.build();
                NotificationManager nManager = (NotificationManager) this.getSystemService(this.NOTIFICATION_SERVICE);

                nManager.notify(001, notif);

            }
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

//**prints current date (you can change format to fit in window)
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            Calendar c = Calendar.getInstance();
            String formattedDate = df.format(c.getTime());
            text=text+formattedDate;


//**print next 7 days

//**print product name (use +"\n" for new line)

        TextView textElement=(TextView) findViewById(R.id.textView2);
        textElement.setText(text);
    }






    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_addNewProduct:
                Intent i = new Intent(MainActivity.this,AddProductActivity.class);
                i.putExtra("list", (Serializable) product_list);
                startActivity(i);
                return true;
            case R.id.menu_editProduct:
                Intent i2 = new Intent(MainActivity.this,EditActivity.class);
                i2.putExtra("list", (Serializable) product_list);
                startActivity(i2);
                return true;
            case R.id.menu_calender:
                Intent i3 = new Intent(MainActivity.this,MainActivity.class);
                startActivity(i3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
