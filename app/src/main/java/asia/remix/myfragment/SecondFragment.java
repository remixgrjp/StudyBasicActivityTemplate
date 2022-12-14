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
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;

public class SecondFragment extends Fragment{
	static final String TAG= "SecondFragment";
	static final String KEY= "SecondFragment";//for recevie key

	@Override
	public void onCreate( Bundle savedInstanceState ){
		super.onCreate( savedInstanceState );
		Log.d( TAG, "onCreate()" );
	}

	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
		Log.d( TAG, "onCreateView()" );
		return inflater.inflate( R.layout.fragment_second, container, false );
	}

	public void onViewCreated( @NonNull View view, Bundle savedInstanceState ){
		super.onViewCreated( view, savedInstanceState );
		Log.d( TAG, "onViewCreated()" );

		Button button = view.findViewById( R.id.button_second );
		button.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				Bundle bundle= new Bundle();
				bundle.putString( FirstFragment.KEY , "From 2" );
				NavHostFragment.findNavController( SecondFragment.this ).navigate( R.id.action_SecondFragment_to_FirstFragment, bundle );
			}
		} );

		TextView textView = view.findViewById( R.id.textview_second );
		Bundle b= ( null == getArguments() ? new Bundle() : getArguments() );
		String s= requireActivity().getString( R.string.hello_second_fragment );
		textView.setText( String.format( s, b.getString( KEY, "-" ) ) );

		textView.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				MyDialog.newInstance( TAG ).show( getParentFragmentManager(), "Fragment2 -> MyDialog" );
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
}