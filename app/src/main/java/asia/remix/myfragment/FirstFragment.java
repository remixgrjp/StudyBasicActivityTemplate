package asia.remix.myfragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class FirstFragment extends Fragment{
	static final String TAG= "FirstFragment";
	static final String KEY= "FirstFragment";//for recevie key

	@Override
	public void onCreate( Bundle savedInstanceState ){
		super.onCreate( savedInstanceState );
		Log.d( TAG, "onCreate()" );
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
}