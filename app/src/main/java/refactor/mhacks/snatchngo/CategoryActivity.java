package refactor.mhacks.snatchngo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by abc96_000 on 2016-02-20.
 */
public class CategoryActivity extends BaseActivity {

    public String[] Cat;
    ListView categoryChoice;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_activity);
        Intent intent = getIntent();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final int location = Integer.parseInt(intent.getStringExtra("Location"));
        final String lName = intent.getStringExtra("Name");
        TextView t = (TextView) findViewById(R.id.title);
        t.setText(lName);

        Firebase.setAndroidContext(this);
        Firebase ref = new Firebase("https://snatch-and-go.firebaseio.com");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                int len = (int) snapshot.child("locations/" + (location + 1) + "/categories").getChildrenCount();
                Cat = new String[len];
                int counter = 0;
                for (DataSnapshot o : snapshot.child("locations/" + (location + 1) + "/categories").getChildren()) {
                    Cat[counter++] = o.getKey();
                }
                categoryChoice = (ListView) findViewById(R.id.listView);
                ArrayAdapter<String> adapterCat = new ArrayAdapter<String>(CategoryActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, Cat);
                categoryChoice.setAdapter(adapterCat);
                categoryChoice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                          @Override
                                                          public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                                              Log.d("debug", "reached 4");
                                                              String catString = (String) categoryChoice.getItemAtPosition(position);
                                                              Log.d("debug", "reached 3");
                                                              Intent intent = new Intent(CategoryActivity.this, MealActivity.class);
                                                              intent.putExtra("Cat", catString);
                                                              intent.putExtra("Location", location+"");
                                                              intent.putExtra("Name", lName);
                                                              startActivity(intent);
                                                          }
                                                      }
                );
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_menu) {
            Intent intent = new Intent(CategoryActivity.this,MainActivity.class);
            setIntent(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}