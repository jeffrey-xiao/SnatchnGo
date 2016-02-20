package refactor.mhacks.snatchngo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
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
    public String[] names;
    public int locationCount=0;
    public ListView locationChoice;
    public Button moveOn;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_activity);
        Firebase.setAndroidContext(this);
        Firebase rootRef = new Firebase("https://snatch-and-go.firebaseio.com");
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                locationCount = (int) snapshot.child("locations").getChildrenCount();
                names = new String[locationCount];
                for (int i = 0; i < locationCount; i++) {
                    names[i] = (String) snapshot.child("locations/" + (i + 1) + "/name").getValue();
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                locationCount=0;
            }
        });
        moveOn = (Button) findViewById(R.id.moveButton);
        moveOn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ChooseActivity.this, CategoryActivity.class);
                    startActivity(intent);
                }
            });

        //locationChoice = (ListView) findViewById(R.id.locatChoices);
        // Defined Array values to show in ListView

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data
        Log.d("debug",locationCount+"");
        //ArrayAdapter<String> adapterLocat = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, android.R.id.text1, names);

        // Assign adapter to ListView
//        locationChoice.setAdapter(adapterLocat);

    }

}