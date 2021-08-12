package sg.edu.rp.c346.id20007998.gamenote;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<Note> noteList;
    CustomAdapter ca;
    Button btnInsertPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInsertPage=findViewById(R.id.AddNote);

        lv=findViewById(R.id.lv);

        noteList=new ArrayList<Note>();
        ca = new CustomAdapter(this, R.layout.row, noteList);
        lv.setAdapter(ca);

        DBHelper dbh = new DBHelper(MainActivity.this);
        //dbh.insertNote("SIMS 4","TIPS","Open cheat mode press CTRL+SHIFT+C, then enter testingcheats true. Now you can enter cheat comment!");
        noteList.clear();
        noteList.addAll(dbh.getAllNote());
        ca.notifyDataSetChanged();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Note data = noteList.get(position);
                Intent i = new Intent(MainActivity.this,
                        ViewNote.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });

        btnInsertPage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                LayoutInflater inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View viewDialog= inflater.inflate(R.layout.insert,null);

                final EditText edTitle=viewDialog.findViewById(R.id.edTitle),edAuthor=viewDialog.findViewById(R.id.edAuthor),edName=viewDialog.findViewById(R.id.edName),
                        edType=viewDialog.findViewById(R.id.edType),
                        edDes=viewDialog.findViewById(R.id.edDes);

                AlertDialog.Builder myBuilder=new AlertDialog.Builder(MainActivity.this);

                myBuilder.setView(viewDialog);
                myBuilder.setTitle("Insert New Game Notes");

                myBuilder.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String data_title=edTitle.getText().toString();
                        String data_author=edAuthor.getText().toString();
                        String data_name = edName.getText().toString();
                        String data_type=edType.getText().toString();
                        String data_des=edDes.getText().toString();

                        DBHelper dbh = new DBHelper(MainActivity.this);
                        long inserted_id = dbh.insertNote(data_title,data_author,data_name,data_type,data_des);

                        if (inserted_id != -1){
                            Toast.makeText(MainActivity.this, "Insert successful",
                                    Toast.LENGTH_SHORT).show();

                        }
                        noteList.clear();
                        noteList.addAll(dbh.getAllNote());
                        ca.notifyDataSetChanged();
                    }

                });
                myBuilder.setNegativeButton("CANCEL",null);
                AlertDialog myDialog=myBuilder.create();
                myDialog.show();
            }


        });

    }
    @Override
    protected void onResume(){
        super.onResume();

        DBHelper dbh = new DBHelper(MainActivity.this);
        noteList.clear();
        noteList.addAll(dbh.getAllNote());
        ca.notifyDataSetChanged();
    }
}