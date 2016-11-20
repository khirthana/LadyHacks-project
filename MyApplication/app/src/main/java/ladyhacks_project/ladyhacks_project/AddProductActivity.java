package ladyhacks_project.ladyhacks_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddProductActivity extends AppCompatActivity {

    ProductDBHelper dtbs;
    EditText eName, eDesc, ePrice;
    Button btnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    //when 'Add New Product' is selected from menu, AddProductActivity is started
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent(AddProductActivity.this,EditActivity.class);
        startActivity(i);
        return true;

    }

}
