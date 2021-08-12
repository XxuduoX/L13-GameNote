package sg.edu.rp.c346.id20007998.gamenote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewNote extends AppCompatActivity {
    TextView viewName,viewType,viewDes,viewTitle,viewAuthor;
    Button btnEdit,btnBack;
    Note data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);

        viewName=findViewById(R.id.viewGameName);
        viewType=findViewById(R.id.viewNoteType);
        viewDes=findViewById(R.id.viewDescription);
        viewTitle=findViewById(R.id.viewTitle);
        viewAuthor=findViewById(R.id.viewAuthor);
        btnEdit=findViewById(R.id.btnEdit);
        btnBack=findViewById(R.id.btnBack);

        Intent i = getIntent();
        data = (Note) i.getSerializableExtra("data");
        viewTitle.setText(data.getTitle());
        viewAuthor.setText("By "+data.getAuthor());
        viewName.setText(data.getName());
        viewType.setText(data.getType());
        viewDes.setText(data.getDescription());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewNote.this,
                        EditNote.class);
                i.putExtra("data", data);
                startActivity(i);
            }

        });

    }
}