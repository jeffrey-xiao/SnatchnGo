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

import com.firebase.client.ChildEventListener;
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
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_activity);
        Firebase.setAndroidContext(this);
        Firebase ref = new Firebase("https://snatch-and-go.firebaseio.com");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                int len = (int)snapshot.child("locations").getChildrenCount();
                names = new String[len];

                for (int i=0;i<len;i++){
                    if (snapshot.child("locations/"+(i+1)+"/name").getValue()!=null) {

                        names[i]=(String) snapshot.child("locations/"+(i+1)+"/name").getValue();
                        Log.d("what the fuck",names[i]+" "+i);
                    }
                    Log.d("debug","reached 1");
                }
                Log.d("debug",names[0]+" "+names[1]);
                locationChoice = (ListView) findViewById(R.id.locatChoices);
                ArrayAdapter<String> adapterLocat = new ArrayAdapter<String>(ChooseActivity.this,android.R.layout.simple_list_item_1, android.R.id.text1, names);
                locationChoice.setAdapter(adapterLocat);
                Log.d("debug", "reached 2");
                locationChoice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                          @Override
                                                          public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                                              Log.d("debug","reached 4");
                                                              String locationString = (String) locationChoice.getItemAtPosition(position);
                                                              Log.d("debug","reached 3");
                                                              int locate = position;
                                                              Intent intent = new Intent(ChooseActivity.this, CategoryActivity.class);
                                                              intent.putExtra("Location", "" + locate);
                                                              intent.putExtra("Name", locationString);
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

}