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

public class MySimpleArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public MySimpleArrayAdapter(Context context, String[] values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.browsestudent_listview_row_layout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(values[position]);
        // change the icon for Windows and iPhone
        String s = values[position];

        System.out.println("values");
        System.out.println("values");
        System.out.println("values");
        System.out.println("values");
        System.out.println("values");
        System.out.println(values);

        System.out.println("textView.setText(values[position]);");
        System.out.println("textView.setText(values[position]);");
        System.out.println("textView.setText(values[position]);");
        System.out.println("textView.setText(values[position]);");
        System.out.println("textView.setText(values[position]);");
        System.out.println(values[position]);
        System.out.println("String s = values[position]");
        System.out.println("String s = values[position]");
        System.out.println("String s = values[position]");
        System.out.println("String s = values[position]");
        System.out.println("String s = values[position]");
        System.out.println(s);


        // position is Students Lastname. Dont know why it is called position
        // or if we can call any field?
        if (s.startsWith("Dach") || s.startsWith("Ne1") || s.startsWith("Geller")){
            imageView.setImageResource(R.mipmap.guitar_mipmap_mdpi);
        } else {
            imageView.setImageResource(R.mipmap.piano_mipmap_mdpi);
        }

        return rowView;
    }
}