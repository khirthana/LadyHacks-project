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

    ArrayList<Product> product_list = new ArrayList<Product>();
    ArrayList<Product> added_list = new ArrayList<Product>();

    Spinner list;

    String product_name;

    int expiry_date;


    TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        Intent callingIntent = getIntent();
        product_list= (ArrayList<Product>) callingIntent.getSerializableExtra("list");

        //products are displayed on spinner
        List<String> strings = new ArrayList<>(product_list.size());
        for (Product object : product_list) {
            strings.add(object.getProduct_name());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, strings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        list = (Spinner)findViewById(R.id.spinner);
        list.setAdapter(adapter);
        list.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        if(spinner.getId() == R.id.spinner) {
            //expiry date displayed on spinner
            List<String> dates = new ArrayList<>(product_list.size());

            if (position == 0) {
                product_name = (product_list.get(0)).getProduct_name();
                Product object = product_list.get(0);
                dates.add(Integer.toString(object.getProduct_exp()));
            } else {

                product_name = (product_list.get(1)).getProduct_name();
                Product object = product_list.get(1);
                dates.add(Integer.toString(object.getProduct_exp()));
            }
            dates.add("1");
            dates.add("2");
            dates.add("3");
            dates.add("4");
            dates.add("5");
            dates.add("6");
            dates.add("7");

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dates);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            list = (Spinner) findViewById(R.id.spinner_day);
            list.setAdapter(adapter);
        }
        else if(spinner.getId() == R.id.spinner_day){
            expiry_date=position;
        }
    }



    public void   add_item_button(View v){
        //create added_list & display on textView(added_list)
        if(expiry_date==0){
            if(product_name.equals("Milk")){
                expiry_date=7;
            }else{
                expiry_date=3;
            }
        }
        Product added_p=new Product(product_name,expiry_date);
        added_list.add(added_p);
        text=(TextView) findViewById(R.id.added_list);
        String content="";
        for(Product p:added_list){
            content=content+(p.getProduct_name()+" "+p.getProduct_exp()+" days \n");
        }
        text.setText(content);
    }


    public void   done_button(View v){
        //display calendar

        Intent i3 = new Intent(AddProductActivity.this,CalendarActivity.class);
        i3.putExtra("list", (Serializable) product_list);
        i3.putExtra("added_list", (Serializable) added_list);
        startActivity(i3);
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
                Intent i = new Intent(AddProductActivity.this,AddProductActivity.class);
                i.putExtra("list", (Serializable) product_list);
                i.putExtra("added_list", (Serializable) added_list);
                startActivity(i);
                return true;
            case R.id.menu_editProduct:
                Intent i2 = new Intent(AddProductActivity.this,EditActivity.class);
                i2.putExtra("list", (Serializable) product_list);
                i2.putExtra("added_list", (Serializable) added_list);
                startActivity(i2);
                return true;
            case R.id.menu_calender:
                Intent i3 = new Intent(AddProductActivity.this,CalendarActivity.class);
                i3.putExtra("list", (Serializable) product_list);
                i3.putExtra("added_list", (Serializable) added_list);
                startActivity(i3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
