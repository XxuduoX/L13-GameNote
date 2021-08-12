package sg.edu.rp.c346.id20007998.gamenote;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditNote extends AppCompatActivity {
    Note data;
    EditText edID,edName,edType,edDes,edTitl,edAuth;
    Button btnUpdate,btnDelete,btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        edID=findViewById(R.id.edID);
        edName=findViewById(R.id.edEName);
        edType=findViewById(R.id.edEtype);
        edDes=findViewById(R.id.edEDes);
        edTitl=findViewById(R.id.edEtitle);
        edAuth=findViewById(R.id.edEauthor);

        btnUpdate=findViewById(R.id.btnUpdate);
        btnDelete=findViewById(R.id.btnDelete);
        btnCancel=findViewById(R.id.btnCancel);

        Intent i = getIntent();
        data = (Note) i.getSerializableExtra("data");
        Integer id=data.getId();
        edTitl.setText(data.getTitle());
        edAuth.setText(data.getAuthor());
        edID.setText(id.toString());
        edName.setText(data.getName());
        edType.setText(data.getType());
        edDes.setText(data.getDescription());

        edID.setEnabled(false);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditNote.this);
                data.setTitle(edTitl.getText().toString().trim());
                data.setAuthor(edAuth.getText().toString().trim());
                data.setName(edName.getText().toString().trim());
                data.setType(edType.getText().toString().trim());
                data.setDescription(edDes.getText().toString().trim());
                dbh.updateNote(data);
                dbh.close();

                finish();
                Intent i = new Intent(EditNote.this,
                        MainActivity.class);
                startActivity(i);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder=new AlertDialog.Builder(EditNote.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete the note? "+"\n"+data.getName());
                myBuilder.setCancelable(false);

                myBuilder.setPositiveButton("Cancel",null);

                myBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(EditNote.this);
                        dbh.deleteNote(data.getId());

                        finish();
                        Intent i = new Intent(EditNote.this,
                                MainActivity.class);
                        startActivity(i);
                    }
                });
                AlertDialog myDialog=myBuilder.create();
                myDialog.show();

            }
        });
    }
}