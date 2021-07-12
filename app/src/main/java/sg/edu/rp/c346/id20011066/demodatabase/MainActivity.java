package sg.edu.rp.c346.id20011066.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert;
    Button btnGetTask;
    EditText etDescription;
    EditText etDate;
    TextView tvResults;
    ListView lv;

    ArrayList<Task> al;
    ArrayAdapter<Task> aa;

    int num_btnClicked = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etDescription = findViewById(R.id.etDescription);
        etDate = findViewById(R.id.etDate);

        lv = findViewById(R.id.lv);
        al = new ArrayList<>();
        aa = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        btnInsert = findViewById(R.id.buttonInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(MainActivity.this);
                dbHelper.insertTask(etDescription.getText().toString(), etDate.getText().toString());
            }
        });
        tvResults = findViewById(R.id.tvResults);

        btnGetTask = findViewById(R.id.buttonGetTasks);
        btnGetTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(MainActivity.this);
                ArrayList<String> tasks = dbHelper.getTaskContent();

                String text = "";
                for (int i = 0; i < tasks.size(); i++) {
                    text += i + ". " + tasks.get(i) + "\n";
                }
                tvResults.setText(text);

                al.clear();
                al.addAll(dbHelper.getTasks());
                aa.notifyDataSetChanged();



                if(num_btnClicked % 2 == 1) {
                    al.clear();
                    al.addAll(dbHelper.getTasks());
                    aa.notifyDataSetChanged();
                } else if(num_btnClicked % 2 == 0){
                    al.clear();
                    al.addAll(dbHelper.getTasks2());
                    aa.notifyDataSetChanged();
                }
                num_btnClicked++;
            }

        });
    }
}
