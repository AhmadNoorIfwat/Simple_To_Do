package sg.edu.c346.id21010771.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextView tvTitle;
    Spinner spnAddDelete;
    EditText etAddTask;
    Button btnAddTask;
    Button btnDeleteTask;
    Button btnClearTask;
    ListView lvShowTasks;
    ArrayList<String> alTasks;
    ArrayAdapter<String> aaTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.dark_blue)));

        tvTitle = findViewById(R.id.textViewTitle);
        spnAddDelete = findViewById(R.id.spinnerAddOrDelete);
        etAddTask = findViewById(R.id.editTextAddTasks);
        btnAddTask = findViewById(R.id.buttonAdd);
        btnDeleteTask = findViewById(R.id.buttonDelete);
        btnClearTask = findViewById(R.id.buttonClear);
        lvShowTasks = findViewById(R.id.listViewShowTasks);

        alTasks = new ArrayList<>();

        aaTasks = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTasks);
        lvShowTasks.setAdapter(aaTasks);


        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View){
                alTasks.add(etAddTask.getText().toString());
                etAddTask.getText().clear();
                aaTasks.notifyDataSetChanged();
            }
        });

        btnClearTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etAddTask.getText().clear();
                alTasks.clear();
                aaTasks.notifyDataSetChanged();
            }
        });

        btnDeleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(alTasks.size() > 0){
                    int textIndex = Integer.parseInt(etAddTask.getText().toString());
                    if(textIndex+1 > alTasks.size()){
                        Toast.makeText(getApplicationContext(), "Index unavailable, unable to delete", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        alTasks.remove(textIndex);
                        etAddTask.getText().clear();
                        aaTasks.notifyDataSetChanged();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "There is nothing to be deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        spnAddDelete.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        btnAddTask.setEnabled(true);
                        btnDeleteTask.setEnabled(false);
                        etAddTask.getText().clear();
                        aaTasks.notifyDataSetChanged();

                        break;
                    case 1:
                        btnAddTask.setEnabled(false);
                        btnDeleteTask.setEnabled(true);
                        etAddTask.getText().clear();
                        etAddTask.setHint("Enter index of task");
                        aaTasks.notifyDataSetChanged();

                        break;
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(), "You have yet to select to add or delete", Toast.LENGTH_SHORT).show();
            }
        });



    }
}