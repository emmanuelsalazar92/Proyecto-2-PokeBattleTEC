package battletech.com.battletech;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;


public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent act = getIntent();
        TextView _name = (TextView) findViewById(R.id.tvname);
        _name.setText(act.getStringExtra("Nombre"));
        TextView _peso = (TextView) findViewById(R.id.tvpeso);
        _peso.setText("Weight: " + act.getStringExtra("Weight"));
        TextView _altura = (TextView) findViewById(R.id.tvaltura);
        _altura.setText("Height: " + act.getStringExtra("Height"));
        TextView _tipo = (TextView) findViewById(R.id.tvtipo);
        _tipo.setText("Type " + act.getStringExtra("Type"));
        ImageButton _foto = (ImageButton) findViewById(R.id.imdetailfoto);
        _foto.setImageResource(Integer.parseInt(act.getStringExtra("FOTO")));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
