package battletech.com.battletech;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.TextView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import battletech.com.battletech.SphericalUtil;
import java.util.List;








//google.maps.geometry.encoding
//        google.maps.geometry.spherical

public class MapsActivity extends FragmentActivity{


    double latitude;
    double longitude;
    GPSTracker gps;
    //LatLng _Ubicacion001 = new LatLng(9.856918,-83.912948); //CIC
    LatLng _Ubicacion001 = new LatLng(9.856357, -83.912569); //B3

    LatLng _Ubicacion002 = new LatLng(9.855946,-83.912612); //
    LatLng _Ubicacion003 = new LatLng(9.855124,-83.909478); //
    LatLng _Ubicacion004 = new LatLng(9.855058,-83.913207); //
    LatLng _Ubicacion005 = new LatLng(9.854339,-83.910165); //
    LatLng _Ubicacion006 = new LatLng(9.854904,-83.912611); //
    LatLng _Ubicacion007 = new LatLng(9.855981,-83.913628); //
    LatLng _Ubicacion008 = new LatLng(9.855681,-83.912828); //
    LatLng _Ubicacion009 = new LatLng(9.854914,-83.907913); //
    LatLng _Ubicacion010 = new LatLng(9.856133,-83.909501); //
    LatLng _Ubicacion011 = new LatLng(9.858081,-83.913326); //
    LatLng _Ubicacion012 = new LatLng(9.857,-83.910791); //
    LatLng _Ubicacion013 = new LatLng(9.854521,-83.908697); //
    LatLng _Ubicacion014 = new LatLng(9.856049,-83.907817); //
    LatLng _Ubicacion015 = new LatLng(9.858601,-83.911253); //
    LatLng _Ubicacion016 = new LatLng(9.853686,-83.911542); //
    LatLng _Ubicacion017 = new LatLng(9.849939,-83.9111); //
    LatLng _Ubicacion018 = new LatLng(9.853655,-83.907922); //
    LatLng _Ubicacion019 = new LatLng(9.852778,-83.9063); //
    LatLng _Ubicacion020 = new LatLng(9.857476,-83.911567); //


    LatLng[] _PokemonLocation={_Ubicacion001,_Ubicacion002,_Ubicacion003,_Ubicacion004,_Ubicacion005,
            _Ubicacion006,_Ubicacion007,_Ubicacion008,_Ubicacion009,_Ubicacion010,
            _Ubicacion011,_Ubicacion012,_Ubicacion013,_Ubicacion014,_Ubicacion015,
            _Ubicacion016,_Ubicacion017,_Ubicacion018,_Ubicacion019,_Ubicacion020};
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private CameraUpdate mCamera;



    int[] _PokemonList={127,138,13,121,110,
            135     ,58   ,  149 ,    63,     67,
            57     ,139   ,  45 ,    114 ,    102,
            17    , 14    , 39 ,    27    , 50 };


    HttpClient httpclient = new DefaultHttpClient();
    HttpResponse response;


    String[] _PokemonName={"Bulbasaur","Ivysaur","Venusaur","Charmander","Charmeleon","Charizard","Squirtle","Wartortle","Blastoise",
            "Caterpie","Metapod","Butterfree","Weedle","Kakuna","Beedrill","Pidgey","Pidgeotto","Pidgeot",
            "Rattata","Raticate","Spearow","Fearow","Ekans","Arbok","Pikachu","Raichu","Sandshrew",
            "Sandslash","Nidoran","Nidorina","Nidoqueen","Nidoran","Nidorino","Nidoking","Clefairy",
            "Clefable","Vulpix","Ninetales","Jigglypuff","Wigglytuff","Zubat","Golbat","Oddish","Gloom","Vileplume","Paras",
            "Parasect","Venonat","Venomoth","Diglett","Dugtrio","Meowth","Persian","Psyduck","Golduck","Mankey","Primeape","Growlithe","Arcanine","Poliwag","Poliwhirl","Poliwrath","Abra",
            "Kadabra","Alakazam","Machop","Machoke","Machamp","Bellsprout","Weepinbell","Victreebel","Tentacool","Tentacruel","Geodude","Graveler","Golem","Ponyta","Rapidash",
            "Slowpoke","Slowbro","Magnemite","Magneton","Farfetch'd","Doduo","Dodrio","Seel","Dewgong","Grimer","Muk","Shellder","Cloyster","Gastly","Haunter","Gengar","Onix",
            "Drowzee","Hypno","Krabby","Kingler","Voltorb","Electrode","Exeggcute","Exeggutor","Cubone","Marowak","Hitmonlee","Hitmonchan","Lickitung","Koffing","Weezing","Rhyhorn","Rhydon","Chansey","Tangela","Kangaskhan",
            "Horsea","Seadra","Goldeen","Seaking","Staryu","Starmie","Mr. Mime","Scyther","Jynx","Electabuzz","Magmar","Pinsir","Tauros","Magikarp","Gyarados","Lapras","Ditto","Eevee",
            "Vaporeon","Jolteon","Flareon","Porygon","Omanyte","Omastar","Kabuto","Kabutops","Aerodactyl","Snorlax","Articuno","Zapdos","Moltres","Dratini","Dragonair","Dragonite","Mewtwo","Mew"};
    int[] _PokemonImage={R.drawable.p001,R.drawable.p002,R.drawable.p003,R.drawable.p004,R.drawable.p005,R.drawable.p006,R.drawable.p007,R.drawable.p008,R.drawable.p009,R.drawable.p010,
            R.drawable.p011,R.drawable.p012,R.drawable.p013,R.drawable.p014,R.drawable.p015,R.drawable.p016,R.drawable.p017,R.drawable.p018,R.drawable.p019,R.drawable.p020,
            R.drawable.p021,R.drawable.p022,R.drawable.p023,R.drawable.p024,R.drawable.p025,R.drawable.p026,R.drawable.p027,R.drawable.p028,R.drawable.p029,R.drawable.p030,
            R.drawable.p031,R.drawable.p032,R.drawable.p033,R.drawable.p034,R.drawable.p035,R.drawable.p036,R.drawable.p037,R.drawable.p038,R.drawable.p039,R.drawable.p040,
            R.drawable.p041,R.drawable.p042,R.drawable.p043,R.drawable.p044,R.drawable.p045,R.drawable.p046,R.drawable.p047,R.drawable.p048,R.drawable.p049,R.drawable.p050,
            R.drawable.p051,R.drawable.p052,R.drawable.p053,R.drawable.p054,R.drawable.p055,R.drawable.p056,R.drawable.p057,R.drawable.p058,R.drawable.p059,R.drawable.p060,
            R.drawable.p061,R.drawable.p062,R.drawable.p063,R.drawable.p064,R.drawable.p065,R.drawable.p066,R.drawable.p067,R.drawable.p068,R.drawable.p069,R.drawable.p070,
            R.drawable.p071,R.drawable.p072,R.drawable.p073,R.drawable.p074,R.drawable.p075,R.drawable.p076,R.drawable.p077,R.drawable.p078,R.drawable.p079,R.drawable.p080,
            R.drawable.p081,R.drawable.p082,R.drawable.p083,R.drawable.p084,R.drawable.p085,R.drawable.p086,R.drawable.p087,R.drawable.p088,R.drawable.p089,R.drawable.p090,
            R.drawable.p091,R.drawable.p092,R.drawable.p093,R.drawable.p094,R.drawable.p095,R.drawable.p096,R.drawable.p097,R.drawable.p098,R.drawable.p099,R.drawable.p100,
            R.drawable.p101,R.drawable.p102,R.drawable.p103,R.drawable.p104,R.drawable.p105,R.drawable.p106,R.drawable.p107,R.drawable.p108,R.drawable.p109,R.drawable.p110,
            R.drawable.p111,R.drawable.p112,R.drawable.p113,R.drawable.p114,R.drawable.p115,R.drawable.p116,R.drawable.p117,R.drawable.p118,R.drawable.p119,R.drawable.p120,
            R.drawable.p121,R.drawable.p122,R.drawable.p123,R.drawable.p124,R.drawable.p125,R.drawable.p126,R.drawable.p127,R.drawable.p128,R.drawable.p129,R.drawable.p130,
            R.drawable.p131,R.drawable.p132,R.drawable.p133,R.drawable.p134,R.drawable.p135,R.drawable.p136,R.drawable.p137,R.drawable.p138,R.drawable.p139,R.drawable.p140,
            R.drawable.p141,R.drawable.p142,R.drawable.p143,R.drawable.p144,R.drawable.p145,R.drawable.p146,R.drawable.p147,R.drawable.p148,R.drawable.p149,R.drawable.p150,
            R.drawable.p151};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        gps = new GPSTracker(MapsActivity.this);

        // check if GPS enabled
        if(gps.canGetLocation())
        {
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
        }
        else
        {
            gps.showSettingsAlert();
        }

        setUpMapIfNeeded();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }



    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                //mMap.setMyLocationEnabled(true);


                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        LatLng myLocation = new LatLng(latitude, longitude);

        //LatLng da = myLocation - myLocation;
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.humano)));
        mCamera = CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude),21);
        mMap.animateCamera(mCamera);
        CircleOptions _CircleOptions = (new CircleOptions().center(new LatLng(latitude, longitude)).strokeColor(Color.TRANSPARENT).radius(1).fillColor(Color.BLUE));
        Circle _Circle = mMap.addCircle(_CircleOptions);
        double medir;
        boolean _UbicacionDetectada = false;
        int _idPokemon = 0;
        for(int x=0;x<20;x++)
        {
            medir = SphericalUtil.computeDistanceBetween(myLocation,_PokemonLocation[x]);
            if(25>medir){_UbicacionDetectada = true;_idPokemon=_PokemonList[x];}
        }
        if(_UbicacionDetectada == false)
        {
            for(int x=0;x<20;x++)
            {
                setMarker(_PokemonLocation[x],_PokemonList[x]);
            }
        }
        else
        {
            Log.d("MapsActivity","Hora de Batallar " + Integer.toString(_idPokemon));
            Intent act = new Intent(this, BattleActivity.class);
            act.putExtra("PokemonEnemigo",getPokemonName(_idPokemon));
            act.putExtra("PokemonNuestro",getPokemonName(6));
            act.putExtra("ImagenPokemonEnemigo",Integer.toString(getPokemonImage(_idPokemon)));
            act.putExtra("ImagenPokemonNuestro",Integer.toString(getPokemonImage(6)));
            startActivity(act);
        }

    }
   private void setMarker(LatLng pPosition,int pPokemon)
   {
       mMap.addMarker(new MarkerOptions()
       .position(pPosition)
       .title(getPokemonName(pPokemon))
       .icon(BitmapDescriptorFactory.fromResource(getPokemonImage(pPokemon))));
       CircleOptions _CircleOptions = (new CircleOptions().center(pPosition).strokeColor(Color.TRANSPARENT).radius(15).fillColor(Color.BLACK));
       Circle _Circle = mMap.addCircle(_CircleOptions);

   }
    private String getPokemonName(int pPokemon)
    {
        return _PokemonName[pPokemon-1];
    }
    private int getPokemonImage(int pPokemon)
    {
        return _PokemonImage[pPokemon-1];
    }


}
