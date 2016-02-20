package refactor.mhacks.snatchngo;

import java.util.*;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by abc96_000 on 2016-02-20.
 */
public class ChooseActivity extends ActionBarActivity {
    public String[] names = new String[] {"KFC","Dairy Queen","Jeffrey's Diner", "Salty Xiao", "Jeff Zero Xiao","Who the Fuck is Jeff Xiao"};
    public int locationCount=0;
    public ListView locationChoice;
    public Button moveOn;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_activity);

        locationChoice = (ListView) findViewById(R.id.locatChoices);
        // Defined Array values to show in ListView

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapterLocat = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, names);

        // Assign adapter to ListView
        locationChoice.setAdapter(adapterLocat);
        locationChoice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
               String locationString = (String) locationChoice.getItemAtPosition(position);
                int locate = position;
                Intent intent = new Intent(ChooseActivity.this, CategoryActivity.class);
                intent.putExtra("Location",""+locate);
                intent.putExtra("Name",locationString);
                startActivity(intent);
            }
        });

    }

}