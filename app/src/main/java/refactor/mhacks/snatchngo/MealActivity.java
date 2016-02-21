package refactor.mhacks.snatchngo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
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

/**
 * Created by abc96_000 on 2016-02-20.
 */
public class MealActivity extends ActionBarActivity {
    public String[] Meal;
    public String[] Descrip;
    ListView mealChoice;
    public double[] Costs;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meal_activity);
        Intent intent = getIntent();
        final int location = Integer.parseInt(intent.getStringExtra("Location"));
        final String lName = intent.getStringExtra("Name");
        final String cat = intent.getStringExtra("Cat");
        TextView t = (TextView) findViewById(R.id.locationS);
        t.setText(lName);
        TextView t2 = (TextView) findViewById(R.id.mealsS);
        t2.setText(cat);

        Firebase.setAndroidContext(this);
        Firebase ref = new Firebase("https://snatch-and-go.firebaseio.com");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                int len = (int) snapshot.child("locations/" + (location + 1) + "/categories/"+cat).getChildrenCount();
                Meal = new String[len];
                Descrip = new String[len];
                Costs = new double[len];
                int counter = 0;
                for (DataSnapshot o : snapshot.child("locations/" + (location + 1) + "/categories/"+cat).getChildren()) {
                    Meal[counter] = o.getKey();
                    Descrip[counter] = (String) o.child("description").getValue();
                    Costs[counter++] = (double)o.child("cost").getValue();
                }
                mealChoice = (ListView) findViewById(R.id.mealsView);
                ArrayAdapter<String> adapterCat = new ArrayAdapter<String>(MealActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, Meal);
                mealChoice.setAdapter(adapterCat);
                mealChoice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                          @Override
                                                          public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                                              Log.d("debug", "reached 4");
                                                              String mealString = (String) mealChoice.getItemAtPosition(position);
                                                              Log.d("debug", "reached 3");
                                                              Intent intent = new Intent(MealActivity.this, DescripActivity.class);
                                                              intent.putExtra("Meal", mealString);
                                                              intent.putExtra("Location", location + "");
                                                              intent.putExtra("Cost",Costs[position]+"");
                                                              intent.putExtra("Name", lName);
                                                              intent.putExtra("Descrip",Descrip[position]);
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