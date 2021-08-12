package sg.edu.rp.c346.id20007998.gamenote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context context;
    int layout_id;
    ArrayList<Note> noteList;

    public CustomAdapter(Context context, int resource, ArrayList<Note> noteList) {
        super(context, resource,noteList);
        this.context=context;
        this.layout_id=resource;
        this.noteList=noteList;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //R.Layout.row
        View rowView = inflater.inflate(layout_id, parent, false);
        //inflate the view for each row

        TextView tvTitle=rowView.findViewById(R.id.tvTitle);
        TextView tvAuthor=rowView.findViewById(R.id.tvAuthor);
        TextView tvName=rowView.findViewById(R.id.tvGameName);
        TextView tvNoteType=rowView.findViewById(R.id.tvNoteType);
        //Obtain UI component and do the necessary binding.
        Note currentRow=noteList.get(position);
        tvTitle.setText(currentRow.getTitle());
        tvAuthor.setText(currentRow.getAuthor());
        tvName.setText(currentRow.getName());
        tvNoteType.setText(currentRow.getType());

        return rowView;
    }
}
