// tutorial used
// http://www.vogella.com/tutorials/AndroidListView/article.html

package com.example.aaa.mystudentdb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListAdapter extends ArrayAdapter<String> {
    private final Context context;
    //private final String[] values;
    private ArrayList<Student> values;

    public ArrayListAdapter(Context context, ArrayList values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.browsestudent_listview_row_layout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.firstLine);
        TextView textView2 = (TextView) rowView.findViewById(R.id.secondLine);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(values.get(position).getFirst_name()+" "+values.get(position).getLast_name());
        textView2.setText(values.get(position).getInstrument());
        // change the icon for Windows and iPhone
        String s = values.get(position).getInstrument();

        if (s.startsWith("Gitarre")){
            imageView.setImageResource(R.mipmap.guitar_mipmap_mdpi);
        } else {
            imageView.setImageResource(R.mipmap.piano_mipmap_mdpi);
        }

        return rowView;
    }
}