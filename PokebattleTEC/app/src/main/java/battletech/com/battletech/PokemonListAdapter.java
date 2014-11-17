package battletech.com.battletech;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PokemonListAdapter extends BaseAdapter {

    private Activity _Activity;
    private List<Pokemon> _Pokemon;
    private static LayoutInflater inflater=null;

    public PokemonListAdapter(Activity a, List<Pokemon> movies) {
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
            vi = inflater.inflate(R.layout.pokemon_list_adapter, null);
            holder=new ViewHolder();
            holder.PName = (TextView)vi.findViewById(R.id.namePokemon);
            holder.PHeight = (TextView)vi.findViewById(R.id.heightPokemon);
            holder.PWeight = (TextView)vi.findViewById(R.id.weightPokemon);
            holder.PType = (TextView)vi.findViewById(R.id.TypePokemon);
            holder.PPicture = (ImageView)vi.findViewById(R.id.picturepokemon);
            //holder.image = (ImageView)vi.findViewById(R.id.icon);
            vi.setTag(holder);
        }else{
            holder = (ViewHolder)vi.getTag();
        }

        Pokemon item = new Pokemon();
        item = _Pokemon.get(position);

        holder.PName.setText(item.get_NamePokemon());
        holder.PHeight.setText("Height " + item.get_HeightPokemon());
        holder.PWeight.setText("Weight " + item.get_WeightPokemon());
        holder.PType.setText(item.get_TypePokemon());
        holder.PPicture.setImageResource(item.get_PicturePokemon());
        //Picasso.with(_Activity).load(item.getImage()).into(holder.image);
        return vi;
    }

    public void setData(List<Pokemon> movies){
        this._Pokemon.addAll(movies);
        this.notifyDataSetChanged();
    }

    public class ViewHolder
    {
        TextView PName;
        TextView PHeight;
        TextView PWeight;
        TextView PType;
        ImageView PPicture;

    }
}