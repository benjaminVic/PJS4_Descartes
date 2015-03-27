package com.example.ent_mobile;

import java.util.ArrayList;

import SQLite.BDDAndroid;
import SQLite.Cours;
import SQLite.Utilitaire;
import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextClock;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	TextView msg;
	//TextView msg2;
	EditText identifiant;
	EditText password;
	Button but;
	BDDAndroid db;
	String user;
	TextClock clock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db= new BDDAndroid(this); 
        user = db.getUser();
        
        if(user!=""){
        	viewDeconnexion();
        }
        else{
        	viewConnexion();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    	case R.id.menu_compte:
    		// Compartment du bouton "compte"
    		
    		return true;
    	case R.id.menu_edt:
    		// Comportement du bouton "Emploi du temps"
    		return true;
    	case R.id.menu_cahier:
    		// Comportement du bouton "cahier de texte"
    		
    		return true;
    	case R.id.menu_annuaire:
    		// Comportement du bouton "annuaire"
    		return true;
    	case R.id.menu_parametre:
    		// Comportement du bouton "Paramètres"
    		return true;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    }
    
    public void viewConnexion(){
    	setContentView(R.layout.connexion);
     	msg = (TextView)findViewById(R.id.msgentMobile);
     	identifiant = (EditText)findViewById(R.id.editIdent);
     	password = (EditText)findViewById(R.id.editMdp);
     	but = (Button)findViewById(R.id.butConnect);
     	but.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				db.deleteUser();
				viewDeconnexion();
			}
		});
     	
    	View v = new View(this);
    	v.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,1));
    	v.setBackgroundColor(Color.parseColor("#B3B3B3"));
    	
    	ArrayList<Cours> listCours = new ArrayList<Cours>();
    	listCours = db.getCours();
    	if(listCours!=null){
    		for(Cours c : listCours){
    			//reprendre le code de coco :PPP
    		}
    		
    	}
    	else
    	{
    		msg = new TextView(this);
    		msg.setText("Aucun cours dans votre BD Local"); 		
    	}
    }
    
    public void viewDeconnexion(){
    	setContentView(R.layout.activity_main);
    	msg =(TextView)findViewById(R.id.msgBienvenue);
    	msg.setText("Bienvenue " + user);
       	//j'espere juste que l'heure est definit par default T__T
    	ArrayList<Cours> listCours = new ArrayList<Cours>();
    	listCours = db.getCours();
    	if(listCours!=null){
    		for(Cours c : listCours){
    			//reprendre le code de coco :PPP
    		}
    		
    	}
    	else
    	{
    		msg = new TextView(this);
    		msg.setText("Aucun cours dans votre BD Local"); 	
    		but = (Button)findViewById(R.id.butDeconnexion);
    		but.setOnClickListener(new OnClickListener() {

    			@Override
    			public void onClick(View v) {
    				db.deleteUser();
    				viewConnexion();
    			}
    		});
    		
    	}
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
