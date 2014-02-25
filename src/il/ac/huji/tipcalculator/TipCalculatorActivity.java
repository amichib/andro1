package il.ac.huji.tipcalculator;

import java.text.DecimalFormat;

import com.amichai.helleworld.R;

import android.R.string;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class TipCalculatorActivity extends Activity {

	private String txt;
	private final double rate = 0.12;
	private final String tipText = "Tip: $";
	private boolean isPressed ;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/**
		 * create a check box and a Listener for it
		 */
		final CheckBox chkBox = (CheckBox) findViewById(R.id.chkRound);
		chkBox.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//is the checkBox press
				if(chkBox.isChecked()){
					isPressed =true;
				}
				else{
					isPressed = false;
				}
			}
		});
		
		/**
		 * create button which will calculate the tip
		 * depend on the round button
		 */
		Button calcButton = (Button) findViewById(R.id.btnCalculate);
		calcButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				EditText et = (EditText) findViewById(R.id.edtBillAmount);
				TextView tipRes= (TextView) findViewById(R.id.txtTipResult);
				txt = et.getText().toString();
				//calc the tip
				double tipDouble = calcTip(txt);
				//return the match format for each case.
				String tipStr = !isPressed ? String.format("%.2f", (double)tipDouble) :
					String.format("%.00f", (double)tipDouble);
				//set the text
				tipRes.setText(tipText  + tipStr);
			}		
		});
		
		


	}




	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	/**
	 * calculate 12% from the bill
	 * @param txt the bill
	 * @return the tip
	 */
	private double calcTip(String txt) {
		if(!txt.isEmpty()){
			DecimalFormat df = new DecimalFormat("#.##");
			double bill = Double.parseDouble(txt);
			bill *= rate;
			if (isPressed){
				return bill;
			}
			bill = Double.valueOf(df.format(bill));
			return bill;
		}
		return 0;

	}
}




