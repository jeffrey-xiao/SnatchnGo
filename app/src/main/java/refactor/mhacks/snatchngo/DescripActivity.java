package refactor.mhacks.snatchngo;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;

/**
 * Created by abc96_000 on 2016-02-20.
 */
public class DescripActivity extends BaseActivity {
    Button addTo;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.descrip_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        final int location = Integer.parseInt(intent.getStringExtra("Location"));
        final String lName = intent.getStringExtra("Name");
        final String meal = intent.getStringExtra("Meal");
        final String descrip = intent.getStringExtra("Descrip");
        final String cost = intent.getStringExtra("Cost");
        TextView t = (TextView) findViewById(R.id.mealsS);
        t.setText(meal);
        TextView t2 = (TextView) findViewById(R.id.locationS);
        t2.setText(lName);
        TextView t3 = (TextView) findViewById(R.id.descriptionS);
        t3.setText(descrip);
        TextView t4 = (TextView) findViewById(R.id.costView);
        t4.setText("$"+cost);

        addTo = (Button) findViewById(R.id.addtocart);
        addTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(DescripActivity.this, ChooseActivity.class);
                ///////////access firebase to add on to thing
                Toast.makeText(DescripActivity.this, "Item Added to Cart.", Toast.LENGTH_SHORT).show();
                Firebase.setAndroidContext(DescripActivity.this);
                //Firebase ref = new Firebase("https://snatch-and-go.firebaseio.com/locations/"+(location+1)+"/orders");
                TelephonyManager tm = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
                final String number = tm.getLine1Number().substring(1);
                final Firebase ref = new Firebase("https://snatch-and-go.firebaseio.com/locations/"+(location+1)+"/orders");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.child("" + number).exists()) {
                            //already bought something
                            HashMap<String, Object> childchild = new HashMap<String, Object>();
                            childchild.put(meal, false);
                            ref.child("" + number).child("items").updateChildren(childchild);
                        } else {
                            Firebase reff = ref.child("" + number);
                            Firebase refff = reff.child("items");
                            refff.child(meal).setValue(false);
                            reff.child("phone_number").setValue("7343259651");
                        }
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        System.out.println("The read failed: " + firebaseError.getMessage());
                    }
                });

                //ref.child("name").setValue();


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
            Intent intent = new Intent(DescripActivity.this,MainActivity.class);
            setIntent(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}