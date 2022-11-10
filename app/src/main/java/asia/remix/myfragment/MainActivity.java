package asia.remix.myfragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity{
	static final String TAG= "MainActivity";
	AppBarConfiguration appBarConfiguration;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		super.onCreate( savedInstanceState );
		Log.d( TAG, "onCreate()" );
		setContentView( R.layout.activity_main );
		Toolbar toolbar = findViewById( R.id.toolbar );
		setSupportActionBar( toolbar );

		NavController navController = Navigation.findNavController( this, R.id.nav_host_fragment_content_main );
		appBarConfiguration = new AppBarConfiguration.Builder( navController.getGraph() ).build();
		NavigationUI.setupActionBarWithNavController( this, navController, appBarConfiguration );

		FloatingActionButton fab = findViewById( R.id.fab );
		fab.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				Log.d( TAG, "onClick()" );
				MyDialog.newInstance( TAG ).show( getSupportFragmentManager(), "Activity -> MyDialog" );
			}
		} );

		//Recive Dialog / Activity
		getSupportFragmentManager().setFragmentResultListener( MyDialog.KEY, MainActivity.this, new FragmentResultListener(){
			@Override
			public void onFragmentResult( @NonNull String key, @NonNull Bundle bundle ){
				Log.d( TAG, "onFragmentResult()" );
				// noinspection ConstantConditions == @SuppressWarnings( "ConstantConditions" )
				if( null != key && key.equals( MyDialog.KEY ) ){
					Log.d( TAG, String.format( "[%s][%s]", key, bundle.getString( MyDialog.RETERN1 ) ) );
				}
			}
		} );
	}

	@Override
	public void onStart(){//onCreate → onStart → onResume ／ onRestart → onStart → onResume
		super.onStart();
		Log.d( TAG, "onStart()" );
	}

	@Override
	public void onResume(){
		super.onResume();
		Log.d( TAG, "onResume()" );
	}

	@Override
	public void onPause(){//→ onResume()
		super.onPause();
		Log.d( TAG, "onPause()" );
	}

	@Override
	public void onStop(){//→ onRestart() → onStart()
		super.onStop();
		Log.d( TAG, "onStop()" );
	}

	@Override
	public void onDestroy(){//onPause() → onStop() → onDestroy()
		super.onDestroy();
		Log.d( TAG, "onDestroy()" );
	}

	@Override
	public void onWindowFocusChanged( boolean hasFocus ){
		Log.d( TAG, "onWindowFocusChanged()" );
	}

	@Override
	public boolean onSupportNavigateUp(){
		NavController navController = Navigation.findNavController( this, R.id.nav_host_fragment_content_main );
		return NavigationUI.navigateUp( navController, appBarConfiguration ) || super.onSupportNavigateUp();
	}
}