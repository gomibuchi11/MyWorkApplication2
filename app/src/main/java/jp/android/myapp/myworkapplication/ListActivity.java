package jp.android.myapp.myworkapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


public class ListActivity extends Activity {

    private SQLiteDatabase db;
    private ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {

            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        String regData[]={"NoData","NoData","NoData","NoData","NoData"};
        Intent intent = getIntent();

        if(intent!=null) {

            regData[0]=intent.getStringExtra("station_name");
            regData[1]=intent.getStringExtra("address");
            regData[2]=intent.getStringExtra("fare");
            regData[3]=intent.getStringExtra("time");
            regData[4]=intent.getStringExtra("store");

        }

        myListView = (ListView)findViewById(R.id.listView);

        MyDbHelper dbHelper = new MyDbHelper(this);
        db=dbHelper.getWritableDatabase();

        try{

            insertToDB(regData);

            Cursor c=serchToDB();

            String[] from = {

                    MyDbHelper.STATION,
                    MyDbHelper.ADDRESS,
                    MyDbHelper.FARE,
                    MyDbHelper.TIME,
                    MyDbHelper.STORE

            };

            int[] to = {

                    R.id.station_name,
                    R.id.address,
                    R.id.fare,
                    R.id.time,
                    R.id.store,

            };

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.listitem_layout,c,from,to,0);

            //Regiterに戻る処理
            Button btn = (Button)findViewById(R.id.RegisterButton);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent backintent = new Intent(ListActivity.this,RegisterActivity.class);
                    startActivity(backintent);
                }
            });

            myListView.setAdapter(adapter);

            myListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                    //MapｓActivityにaddressのデータを送る
                    String s1 = ((TextView) view.findViewById(R.id.address)).getText().toString();
                    Intent intent = new Intent(ListActivity.this,MapsActivity.class);
                    intent.putExtra("address",s1);

                    startActivity(intent);
                }
            });

        }catch(Exception e){

            e.printStackTrace();

        }finally{

            db.close();
        }
    }

    private void insertToDB(String[] regData) throws Exception{

        //リストビューへの挿入処理
        if(regData[0]!=null&&regData[1]!=null&&regData[2]!=null&&regData[3]!=null&&regData[4]!=null) {
            db.execSQL("insert into myData("

                            +MyDbHelper.STATION +","
                            +MyDbHelper.ADDRESS +","
                            +MyDbHelper.FARE +","
                            +MyDbHelper.TIME +","
                            +MyDbHelper.STORE +
                            ")values('"
                            + regData[0] +"','"
                            + regData[1] + "','"
                            + regData[2] + "','"
                            + regData[3] + "','"
                            + regData[4] +
                            "')"
            );
        }
    }

    private Cursor serchToDB() throws Exception {
       Cursor c = db.rawQuery("select * from " + MyDbHelper.TABLE_NAME,null);
       return c;
   }
}