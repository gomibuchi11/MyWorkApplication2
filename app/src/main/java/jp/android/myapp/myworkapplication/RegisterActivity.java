package jp.android.myapp.myworkapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class RegisterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
    @Override
    public void onStart(){
        super.onStart();

        Button regBtn = (Button)findViewById(R.id.Registerbutton);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText etstation_name    = (EditText) findViewById(R.id.station_nameeditText);
                EditText etaddress    = (EditText) findViewById(R.id.addresseditText);
                EditText etfare    = (EditText) findViewById(R.id.fare_editText);
                EditText ettime    = (EditText) findViewById(R.id.time_editText);
                EditText etstore    = (EditText) findViewById(R.id.store_editText);

                String address    = etaddress.getText().toString();
                String station_name = etstation_name .getText().toString();
                String fare = etfare .getText().toString();
                String time = ettime .getText().toString();
                String store = etstore .getText().toString();

                Intent regIntent = new Intent(RegisterActivity.this,ListActivity.class);
                regIntent.putExtra("address",address);
                regIntent.putExtra("station_name",station_name);
                regIntent.putExtra("fare",fare);
                regIntent.putExtra("time",time);
                regIntent.putExtra("store",store);

                startActivity(regIntent);
            }
        });
    }
}
