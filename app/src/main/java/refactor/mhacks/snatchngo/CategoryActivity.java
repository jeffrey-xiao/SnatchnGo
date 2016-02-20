package refactor.mhacks.snatchngo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by abc96_000 on 2016-02-20.
 */
public class CategoryActivity extends ActionBarActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_activity);
        Intent intent = getIntent();

        int location = Integer.parseInt(intent.getStringExtra("Location"));
        String lName = intent.getStringExtra("Name");

        TextView t = (TextView)findViewById(R.id.title);
        t.setText(lName);
    }
}