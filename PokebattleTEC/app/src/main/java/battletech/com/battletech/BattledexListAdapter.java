package battletech.com.battletech;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;




public class BattledexListAdapter extends BaseAdapter {

    private Activity _Activity;
    private List<Battledex> _Pokemon;
    private static LayoutInflater inflater=null;

    public BattledexListAdapter(Activity a, List<Battledex> movies) {
        Log.d("-------------------","BLA");
        _Activity = a;
        this._Pokemon = movies;
        inflater = (LayoutInflater) _Activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return _Pokemon.size();
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
            vi = inflater.inflate(R.layout.battledex_list_adapter, null);
            holder=new ViewHolder();
            holder.BattlePokemon = (TextView)vi.findViewById(R.id.tvNombre);
            holder.BattleResult = (TextView)vi.findViewById(R.id.tvResultado);
            holder.BattleDate = (TextView)vi.findViewById(R.id.tvFecha);
            //holder.image = (ImageView)vi.findViewById(R.id.icon);
            vi.setTag(holder);
        }else{
            holder = (ViewHolder)vi.getTag();
        }

        Battledex item = new Battledex();
        item = _Pokemon.get(position);
Log.d("POKEMON POKEMON ",item.get_SPokemon() + item.get_SResult() +item.get_SDate());
        holder.BattlePokemon.setText(item.get_SPokemon());
        holder.BattleResult.setText(item.get_SResult());
        holder.BattleDate.setText(item.get_SDate());

        //Picasso.with(_Activity).load(item.getImage()).into(holder.image);
        return vi;
    }

    public void setData(List<Battledex> movies){
        this._Pokemon.addAll(movies);
        this.notifyDataSetChanged();
    }

    public class ViewHolder
    {
        TextView BattlePokemon;
        TextView BattleResult;
        TextView BattleDate;
       // ImageView image;
    }
}