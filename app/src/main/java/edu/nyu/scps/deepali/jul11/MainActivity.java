package edu.nyu.scps.deepali.jul11;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)findViewById(R.id.fragment_list);

        //Create a string Array for all ten list iteam

        String[] a = {"Abdoulaye", "Alrick", "Jeffrey", "Jaxon","Mark", "Joey", "David", "Asa", "Keenen", "Deepali"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                a
        );
        listView.setAdapter(arrayAdapter);


        //Create a IteamClickListner on all list view elements

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                String upperName = (String)parent.getItemAtPosition(position);
                if (upperName.equals("Deepali")) {
                    intent = new Intent(MainActivity.this, DeepaliActivity.class);
                } else {
                    intent = new Intent();
                    String lowerName = upperName.toLowerCase();
                    ComponentName componentName = new ComponentName(
                            "edu.nyu.scps." + lowerName + ".jul11",                          //name of package
                            "edu.nyu.scps." + lowerName + ".jul11." + upperName + "Activity" //name of class
                    );
                    intent.setComponent(componentName);
                }

                //Create a Tone Generator for beep on touch on list elements
                ToneGenerator toneGenerator = new ToneGenerator(AudioManager.STREAM_ALARM, ToneGenerator.MAX_VOLUME / 2);
                //proprietary beep, duration in milliseconds
                toneGenerator.startTone(ToneGenerator.TONE_PROP_BEEP, 100);

                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException activityNotFoundException) {
                    Toast toast = Toast.makeText(MainActivity.this, activityNotFoundException.toString(), Toast.LENGTH_LONG);
                    toast.show();
                }
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
