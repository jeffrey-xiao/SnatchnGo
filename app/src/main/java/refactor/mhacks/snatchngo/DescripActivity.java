package refactor.mhacks.snatchngo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by abc96_000 on 2016-02-20.
 */
public class DescripActivity extends ActionBarActivity {
    Button addTo;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.descrip_activity);
        Intent intent = getIntent();
        final int location = Integer.parseInt(intent.getStringExtra("Location"));
        final String lName = intent.getStringExtra("Name");
        final String meal = intent.getStringExtra("Meal");
        final String descrip = intent.getStringExtra("Descrip");
        TextView t = (TextView) findViewById(R.id.mealsS);
        t.setText(meal);
        TextView t2 = (TextView) findViewById(R.id.locationS);
        t2.setText(lName);
        TextView t3 = (TextView) findViewById(R.id.descriptionS);
        t3.setText(descrip);

        addTo = (Button) findViewById(R.id.addtocart);
        addTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DescripActivity.this, ChooseActivity.class);
                ///////////access firebase to add on to thing
                new AlertDialog.Builder(DescripActivity.this).setTitle("Added to Cart").setMessage(meal+" has been added to your cart").setNeutralButton("Close", null).show();
                startActivity(intent);
            }
        });
    }
}