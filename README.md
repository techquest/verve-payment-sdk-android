Using Verve Payment SDK for Android
================================================================

Verve payment android SDK lets you easily add payment capability into your Android application. The steps below describe how you can go about integrating the SDK into your application.

Registering your application on the Developer Console
----------------------------------------------------

To begin integration with the Verve payment android SDK, you have to register your application on the Developer Console.To do this, follow the steps below:

1.	Go to http://developer.interswitchng.com and sign up by clicking on the Get Started button. Fill the form presented 	to you and click on sign up. You will get an email to the address you supplied during registration asking you to 		confirm your registration. Follow the link to confirm your registration and you will be logged in.

2.	Welcome to Interswitch Developer Network. Click on “Create Your First App” to create credentials for your first 		Interswitch App. Creating the App will provide you the basic requirement to use Interswitch SDKs. You need to 			supply the following information:

	App Name (required): A desired name for your app, e.g. BlueCups App
	
	Company Name (required): The name of the company that owns the application. E.g Blue Cups Ltd
	
	Company Logo - Your App logo (optional): (Note that there is a limit to the size you can upload and it is expected to be 200 x 200 pixels)
	
	Merchant ID (optional): If you are a WebPAY merchant already, enter your Merchant ID here, otherwise ignore this field.
	
	Description (required): A short message introducing your App to its users. Basically, tell us what it does, the owner etc.	
	
3.	Click on Submit button. Once you have done this, you will see a page displaying your credentials.

4.	Click on 'Manage' button at the top right conner. On this page under 'Details' section you will see your client id and your secret key. This will be needed in the SDK. On the 'Services' section turn on the 'QuickTeller Wallet Services' and 'QuickTeller Service' for your application. Note: without turning on this two services your application will throw access denied error.  

5.	Now all is set for you to start building your app.



Using the SDK in your project
-----------------------------

1. 	Add the dependencies located in the "libs" folder to your project along with the Verve SDK library (See sample 	BlueCups project included).
	
2. 	Add the following lines of codes to your AndroidManifest.xml file to request for network access: 

        <uses-permission android:name="android.permission.INTERNET" />
        <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

	These lines request for permission to access the internet and network state of the phone respectively. 	
	
3. 	Add the following lines of code to your AndroidManifest.xml file to register the activities used by the SDK:
	
        <activity
            android:name="com.interswitchng.techquest.vervepayment.VervePayment"
            android:label="" >
        </activity>
        <activity
            android:name="com.interswitchng.techquest.vervepayment.LoginActivity"
            android:label=""
          >
        </activity>
        <activity
            android:name="com.interswitchng.techquest.vervepayment.VerifyPhoneActivity"
            android:label="" >
        </activity>
        <activity
            android:name="com.interswitchng.techquest.vervepayment.VerifyTokenActivity"
            android:label="" >
        </activity>
        <activity
            android:name="com.interswitchng.techquest.vervepayment.AddCardActivity"
            android:label="" >
        </activity>
        <activity
            android:name="com.interswitchng.techquest.vervepayment.ListCardsActivity"
            android:label="" >
        </activity>
        <activity
            android:name="com.interswitchng.techquest.vervepayment.base.BaseActivity"
            android:label="" >
        </activity>
        <activity
            android:name="com.interswitchng.techquest.vervepayment.PayWithExistingCardActivity"
            android:label="" >
        </activity>
        
        
4. 	To initiate payment, simply create a new intent and pass VervePayment as a class reference. For example
		Intent intent = new Intent(MainActivity.this,VervePayment.class);

5. 	Supply required parameters to the intent as shown below

        intent.putExtra("clientId", "Your client id");
        intent.putExtra("clientSecret", "Your client secret");
        intent.putExtra("customerId", "Customer Id");
        intent.putExtra("paymentCode", "Payment Item Code");
        intent.putExtra("amount", "Transaction amount");
        intent.putExtra("isTestPayment", true);
        
	The meanings of the parameters are:
	* PhoneApplicationPage parent: This refers to the page hosting the SDK, the page where you want the SDK pop-up 	screen to show-up over.
	* string paymentCode: This refers to the payment code for the item which you want to pay for. To get a payment 	code, go to your Developer Console and register a new item. You’ll automatically get a payment code for the 			item.
	* long amount: This refers to the amount of money you need the user of your application to pay.
	* string customerId: This refers to the id of the customer who I trying to perform the payment on your 			application. It is usually issued by you and it can be anything.
	* string clientId: This refers to the client ID you got from Developer Console for the particular application 		that you are developing.
	* string clientSecret: This refers to the secret key you got from Developer Console for the particular 			application that you are developing.
	* bool isTestPayment: This is an optional parameter that is set when switching from test implementation to 		production environment. It is true by default which means that you are running in the test implementation **(NB: it’s very important to set the value to false in a live distribution)**.


6.	Call startActivityForResult method passing the intent as a parameter and a resultCode to detect when the intent		returns after execution.
	i.e., startActivityForResult(intent, 400);

7.	Implement onActivityResult method of your Activity class. This is where you will receive the return value after      	execution. Sample code is shown below:
	    
    	@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			if (resultCode == RESULT_OK && requestCode == 400) {
			// get data from intent
				int txnStatus = data.getIntExtra("qtTransactionStatus", 0);
                        //txnStatus is either 0 if transaction failed or 1 if transaction was successful
 
			}
		}
	
	
8.	Go ahead and implement your application as you please.
