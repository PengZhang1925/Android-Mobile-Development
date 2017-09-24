package socialevent.model;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.user.socialevent.EventLists;
import com.example.user.socialevent.R;

import java.util.ArrayList;

/**
 * Created by user on 2016/10/4.
 */
public class EventListsAdapter extends BaseAdapter implements View.OnClickListener {
    private Activity activity;
    private static ArrayList event;
    private static LayoutInflater inflater = null;
    public Resources resources;
    static eventListModel tempValues = null;
    int i = 0;

    public EventListsAdapter(Activity a, ArrayList arr, Resources res){
        activity = a;
        event = arr;
        resources = res;
        inflater = ( LayoutInflater )activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        if(event.size()<=0){
            return 1;
        }
        return event.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView text;
        public TextView text1;
    }
    public View getView(int position,View convertView,ViewGroup parent){
        View v = convertView;
        ViewHolder holder;

        if(convertView == null){
            v = inflater.inflate(R.layout.listitem,null);
            holder = new ViewHolder();
            holder.text = (TextView)v.findViewById(R.id.text);
            holder.text1 = (TextView)v.findViewById(R.id.text1);
            v.setTag(holder);
        }else{
            holder = (ViewHolder)v.getTag();
        }
        if(event.size()<=0){
            holder.text.setText("No Data");
        }else{
            tempValues = null;
            tempValues = (eventListModel) event.get(position);
            holder.text.setText("Name: "+tempValues.getEventName());
            holder.text1.setText("Start Date: "+tempValues.getStartDate()+"\n"+"End Date: "+tempValues.getEndDate());
            v.setOnClickListener(new OnItemClickListener(position));
            v.setOnLongClickListener(new OnItemLongClickListner(position));
        }return v;
    }

    @Override
    public void onClick(View v) {
        Log.v("EventAdapter","======Row button clicked======");
    }

    private class OnItemClickListener implements View.OnClickListener{
        private int mPosition;

        OnItemClickListener(int position){
            mPosition = position;
        }
        @Override
        public void onClick(View arg0) {
            EventLists list = (EventLists) activity;
            list.onItemClick(mPosition);
        }
    }
    private class OnItemLongClickListner implements View.OnLongClickListener{
        private int Position;

        OnItemLongClickListner(int position){Position = position;}
        @Override
        public boolean onLongClick(View arg0) {
            EventLists list = (EventLists) activity;
            list.onItemLongClick(Position);
            return true;
        }
    }
}
