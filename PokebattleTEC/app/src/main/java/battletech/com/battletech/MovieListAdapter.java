package battletech.com.battletech;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
//import com.squareup.picasso.Picasso;

public class MovieListAdapter extends BaseAdapter {

    private Activity activity;
    private List<Movie> movies;
    private static LayoutInflater inflater=null;

    public MovieListAdapter(Activity a, List<Movie> movies) {
        activity = a;
        this.movies = movies;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return movies.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        ViewHolder holder;
        if(convertView==null){
            vi = inflater.inflate(R.layout.pokemon_list_adapter, null);
            holder=new ViewHolder();
            holder.name = (TextView)vi.findViewById(R.id.name);
            holder.image = (ImageView)vi.findViewById(R.id.icon);
            vi.setTag(holder);
        }else{
            holder = (ViewHolder)vi.getTag();
        }

        Movie item = new Movie();
        item = movies.get(position);

        holder.name.setText(item.getName());
        //Picasso.with(activity).load(item.getImage()).into(holder.image);
        return vi;
    }

    public void setData(List<Movie> movies){
        this.movies.addAll(movies);
        this.notifyDataSetChanged();
    }

    public class ViewHolder
    {
        TextView name;
        ImageView image;
    }
}