package refactor.mhacks.snatchngo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by abc96_000 on 2016-02-20.
 */
public class CategoryActivity extends ActionBarActivity {
    public int count;
    public String[][] names = new String[][] {{"KFCKFC","Queen Dairy","alwkgehjaw", "aweglkjanbwo", "q4waghabr","jgnlawkeughlawe"}};
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_activity);
        Intent intent = getIntent();

        int location = Integer.parseInt(intent.getStringExtra("Location"));
        String lName = intent.getStringExtra("Name");
        TextView t = (TextView) findViewById(R.id.title);
        t.setText(lName);
        //Put in listview
        // Assign adapter to ListView
        ListView mealChoice = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, names[0]);
        mealChoice.setAdapter(adapter);
    }
}