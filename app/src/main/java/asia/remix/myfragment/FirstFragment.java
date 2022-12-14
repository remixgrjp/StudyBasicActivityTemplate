package asia.remix.myfragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;

public class FirstFragment extends Fragment{
	static final String TAG= "FirstFragment";
	static final String KEY= "FirstFragment";//for recevie key

	@Override
	public void onCreate( Bundle savedInstanceState ){
		super.onCreate( savedInstanceState );
		Log.d( TAG, "onCreate()" );
		setHasOptionsMenu( true );//menu move from MainActivity
	}

	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
		Log.d( TAG, "onCreateView()" );
		return inflater.inflate( R.layout.fragment_first, container, false );
	}

	public void onViewCreated( @NonNull View view, Bundle savedInstanceState ){
		super.onViewCreated( view, savedInstanceState );
		Log.d( TAG, "onViewCreated()" );

		Button button = view.findViewById( R.id.button_first );
		button.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				Bundle bundle= new Bundle();
				bundle.putString( SecondFragment.KEY, "from 1" );
				NavHostFragment.findNavController( FirstFragment.this ).navigate( R.id.action_FirstFragment_to_SecondFragment, bundle );
			}
		} );

		TextView textView = view.findViewById( R.id.textview_first );
		Bundle b= ( null == getArguments() ? new Bundle() : getArguments() );
		String s= requireActivity().getString( R.string.hello_first_fragment );
		textView.setText( String.format( s, b.getString( KEY, "-" ) ) );

		textView.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				MyDialog.newInstance( TAG ).show( getParentFragmentManager(), "Fragment1 -> MyDialog" );
			}
		} );

		//Recive Dialog / Fragment
		getParentFragmentManager().setFragmentResultListener( MyDialog.KEY, this, new FragmentResultListener(){
			@Override
			public void onFragmentResult( @NonNull String key, @NonNull Bundle bundle ){
				Log.d( TAG, "onFragmentResult()" );
				// noinspection ConstantConditions == @SuppressWarnings( "ConstantConditions" )
				if( null != key && key.equals( MyDialog.KEY ) ){
					String s= String.format( "[%s][%s]", key, bundle.getString( MyDialog.RETERN1, "NULL" ) );
					Log.d( TAG, s );
					textView.setText( s );
				}
			}
		} );
	}

	@Override
	public void onStart(){
		super.onStart();
		Log.d( TAG, "onStart()" );
	}

	@Override
	public void onResume(){
		super.onResume();
		Log.d( TAG, "onResume()" );
	}

	@Override
	public void onStop(){
		super.onStop();
		Log.d( TAG, "onStop()" );
	}

	@Override
	public void onPause(){
		super.onPause();
		Log.d( TAG, "onPause()" );
	}

	@Override
	public void onDestroy(){
		super.onDestroy();
		Log.d( TAG, "onDestroy()" );
	}

	@Override
	public void onDestroyView(){
		super.onDestroyView();
		Log.d( TAG, "onDestroyView()" );
	}

	@Override
	public void onCreateOptionsMenu( @NonNull Menu menu, MenuInflater inflater ){
		inflater.inflate( R.menu.menu_main, menu );
		super.onCreateOptionsMenu( menu,inflater );
	}

	@Override
	public boolean onOptionsItemSelected( MenuItem item ){
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if( id == R.id.action_settings ){
			MyDialog.newInstance( TAG ).show( getParentFragmentManager(), "Fragment1 -> MyDialog" );
			return true;
		}

		return super.onOptionsItemSelected( item );
	}
}