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

public class CalendarActivity extends AppCompatActivity {

    List<Product> product_list;

    List<Product> added_list;
    String text="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent callingIntent = getIntent();


        product_list = (ArrayList<Product>) callingIntent.getSerializableExtra("list");
        added_list = (ArrayList<Product>) callingIntent.getSerializableExtra("added_list");



        int expiry_date1=0;
            int expiry_date2=0;

            if(added_list.size()==2){
                Product item1=added_list.get(0);
                Product item2=added_list.get(1);

                expiry_date1=item1.getProduct_exp();
                expiry_date2=item2.getProduct_exp();
            }
            else if(added_list.size()==1){
                Product item1=added_list.get(0);
                if(item1.getProduct_name().equals("Milk")){
                    expiry_date1=item1.getProduct_exp();

                    expiry_date2=0;
                }
                else{

                    expiry_date1=0;
                    expiry_date2=item1.getProduct_exp();
                }
            }



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
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c = Calendar.getInstance();
        String formattedDate = df.format(c.getTime());
        //text=text+formattedDate+"\n";


//**print next 7 days
        text=text+"NOV20 "+"NOV21 "+"NOV22 "+"NOV23 "+"NOV24 "+"NOV25 "+"NOV26"+"\n";

//**print product name (use +"\n" for new line)
        text=text+"Milk";
        switch(expiry_date1){
            case 1:
                text=text+"__________|";
                break;
            case 2:
                text=text+"__________|";
                break;
            case 3:
                text=text+"__________|";
                break;
            case 4:
                text=text+"__________|";
                break;
            case 5:
                text=text+"__________|";
                break;
            case 6:
                text=text+"__________|";
                break;
            case 7:
                text=text+"____________________________________________________|";
                break;
        }

//**print number of _ until the expiry date then | (next to product name & below expiry date)

        /* example
        Date Nov20 Nov21 Nov22 Nov23  Nov24
        Milk ___________|
        Bread _____________________|
         */
/*
       switch(expiry_date){
           case 1:

           case 2:

           case 3:

           case 4:

           case 5:

           case 6:

           case 7:
       }
*/
        //}

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
                Intent i = new Intent(CalendarActivity.this,AddProductActivity.class);
                i.putExtra("list", (Serializable) product_list);
                i.putExtra("added_list", (Serializable) added_list);
                startActivity(i);
                return true;
            case R.id.menu_editProduct:
                Intent i2 = new Intent(CalendarActivity.this,EditActivity.class);
                i2.putExtra("list", (Serializable) product_list);
                i2.putExtra("added_list", (Serializable) added_list);
                startActivity(i2);
                return true;
            case R.id.menu_calender:
                Intent i3 = new Intent(CalendarActivity.this,MainActivity.class);
                startActivity(i3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
