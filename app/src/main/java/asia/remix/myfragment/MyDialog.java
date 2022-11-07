package asia.remix.myfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class MyDialog extends DialogFragment{
	static final String TAG= "MyDialog";
	static final String KEY= "MyDialog";//for recevie key
	static final String RETERN1= "1";//for return key
	static final private String PARAM1= "1";

	/**
	* @param s default input value
	* @return self -> show() -> onCreateDialog
	*/
	public static MyDialog newInstance( String s ){
		Log.d( TAG, "newInstance()" );
		Bundle b= new Bundle();
		b.putString( PARAM1, s );

		MyDialog d= new MyDialog();
		d.setArguments( b );
		return d;
	}

	@NonNull
	@Override
	public Dialog onCreateDialog( Bundle savedInstanceState ){
		Log.d( TAG, "onCreateDialog()" );
		Bundle b= getArguments();
		if( null==b ){
			b= new Bundle();
		}

		AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
		EditText editText = new EditText( getActivity() );
		builder.setView( editText );
		String s;
		if( null != ( s= b.getString( PARAM1 ) ) ){
			editText.setText( s, android.widget.TextView.BufferType.NORMAL );
		}
		builder.setPositiveButton( "ok", new DialogInterface.OnClickListener(){
			@Override
			public void onClick( DialogInterface di, int i ){
				Log.d( TAG, String.format( "PositiveButton#onClick()[%s][%d]", di, i ) );
				Bundle b = new Bundle();
				b.putString( RETERN1, editText.getText().toString() );
				getParentFragmentManager().setFragmentResult( KEY, b );
			}
		} );
		builder.setNegativeButton( "cancel", new DialogInterface.OnClickListener(){
			@Override
			public void onClick( DialogInterface di, int i ){
				Log.d( TAG, String.format( "NegativeButton#onClick()[%s][%d]", di, i ) );
			}
		} );
		return builder.create();
	}
}