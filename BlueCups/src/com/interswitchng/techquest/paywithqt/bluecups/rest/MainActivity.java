package com.interswitchng.techquest.paywithqt.bluecups.rest;

import com.interswitchng.techquest.paywithqt.bluecups.rest.R;
import com.interswitchng.techquest.vervepayment.VervePayment;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText paymentCode, customerId, amount;


	 //Mobile
	 protected String clientSecret =
	 "WxpvlNH5j1OYfRMGv5ygJZSQjvk0zu2WBL67Z1wJ9dQ=";
	 protected String clientId =
	 "IKIA6815818239CBBBB20F84C3F4CEB1443436D49412";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void proceed(View v) {
		paymentCode = (EditText) findViewById(R.id.paymentCodeEditText);
		customerId = (EditText) findViewById(R.id.cidEditText);
		amount = (EditText) findViewById(R.id.amtEditText);

		String strPaymentCode = paymentCode.getText().toString();
		// int paymnentCode = Integer.parseInt(strPaymentCode);
		String strAmt = amount.getText().toString();
		// double amt = Double.parseDouble(strAmt);
		String cid = customerId.getText().toString();

		Intent intent = new Intent(MainActivity.this, VervePayment.class);
		intent.putExtra("clientId", clientId);
		intent.putExtra("clientSecret", clientSecret);
		intent.putExtra("customerId", cid);
		intent.putExtra("paymentCode", strPaymentCode);
		intent.putExtra("amount", strAmt);
		intent.putExtra("isTestPayment", true);
		startActivityForResult(intent, 400);
	}

	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK && requestCode == 400) {
			// get data from intent
			int txnStatus = data.getIntExtra("qtTransactionStatus", 0);
			int duration = Toast.LENGTH_LONG;
			String message = "Transaction returned " + txnStatus;
			Toast toast = Toast.makeText(this.getApplicationContext(), message,
					duration);
			toast.show();
		}
	}

}
