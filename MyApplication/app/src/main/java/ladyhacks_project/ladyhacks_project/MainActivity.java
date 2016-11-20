package ladyhacks_project.ladyhacks_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ProductDBHelper productdb=null;

    List<Product> allProducts=null;

    int product_position=0;

    Product current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // use the query function in the database helper to find all products
        productdb= new ProductDBHelper(this);

        //insert test data
        productdb.addData("Milk","7 Days");
        productdb.addData("Bread","3 Days");

        // load the test data into a local array list
        allProducts= productdb.getAllData();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    //when 'Add New Product' is selected from menu, AddProductActivity is started
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent(MainActivity.this,AddProductActivity.class);
        startActivity(i);
        return true;

    }
}
