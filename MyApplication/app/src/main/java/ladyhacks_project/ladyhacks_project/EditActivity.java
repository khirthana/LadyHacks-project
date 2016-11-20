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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class EditActivity  extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    ArrayList<Product> product_list = new ArrayList<Product>();
    ArrayList<Product> added_list = new ArrayList<Product>();
    Product selected;
    Spinner list;
    List<String> strings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);


        Intent callingIntent = getIntent();
        product_list= (ArrayList<Product>) callingIntent.getSerializableExtra("list");
        added_list=(ArrayList<Product>) callingIntent.getSerializableExtra("added_list");

        //products are displayed on spinner
        strings = new ArrayList<>(added_list.size());
        for (Product object : added_list) {
            strings.add(object.getProduct_name()+" exp: "+object.getProduct_exp()+" days");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, strings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        list = (Spinner)findViewById(R.id.spinner);
        list.setAdapter(adapter);
        list.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        char first=(strings.get(position)).charAt(0);
        if(first=='M'){
            selected=new Product("Milk",7);
        }
        else{
            selected=new Product("Milk",7);
        }
    }

    public void   delete_selected_button(View v){
//remove product from added_list
        added_list.remove(selected);
    }

    public void   done_button(View v){
        //display calendar
        Intent i3 = new Intent(EditActivity.this,CalendarActivity.class);
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
                Intent i = new Intent(EditActivity.this,AddProductActivity.class);
                startActivity(i);
                return true;
            case R.id.menu_editProduct:
                Intent i2 = new Intent(EditActivity.this,EditActivity.class);
                startActivity(i2);
                return true;
            case R.id.menu_calender:
                Intent i3 = new Intent(EditActivity.this,CalendarActivity.class);
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