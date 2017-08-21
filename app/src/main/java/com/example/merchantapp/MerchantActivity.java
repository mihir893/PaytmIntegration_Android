package com.example.merchantapp;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * This is the sample app which will make use of the PG SDK. This activity will
 * show the usage of Paytm PG SDK API's.
 **/

public class MerchantActivity extends Activity {

    PaytmPGService Service;

    String CHECKSUMHASH1,orderid,customerid;

    RequestQueue requestQueue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.merchantapp);

        requestQueue = Volley.newRequestQueue(MerchantActivity.this);

		//initOrderId();
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
	}
	
	//This is to refresh the order id: Only for the Sample App's purpose.
	@Override
	protected void onStart(){
		super.onStart();
		//initOrderId();
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
	}
	

	/*private void initOrderId() {
		Random r = new Random(System.currentTimeMillis());
		String orderId = "ORDER" + (1 + r.nextInt(2)) * 10000
				+ r.nextInt(10000);
		EditText orderIdEditText = (EditText) findViewById(R.id.order_id);
		orderIdEditText.setText(orderId);
	}*/

	public void onStartTransaction(View view) {


        CHECKSUMHASH1="";
        orderid="";
        customerid="";

        CallVolley("http://mtaj-development.com/Paytm_App_PHP/generateChecksum.php");




		//Kindly create complete Map and checksum on your server side and then put it here in paramMap.


		/*Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("MID" , "Mihirt47560013295058");
		paramMap.put("ORDER_ID" , "TestMerchant000117087");
		paramMap.put("CUST_ID" , "CUST3874");
		paramMap.put("INDUSTRY_TYPE_ID" , "Retail");
		paramMap.put("CHANNEL_ID" , "WAP");
		paramMap.put("TXN_AMOUNT" , "1");
		paramMap.put("WEBSITE" , "APP_STAGING");
		paramMap.put("CALLBACK_URL" , "https://pguat.paytm.com/paytmchecksum/paytmCallback.jsp");
		paramMap.put("CHECKSUMHASH" , "w2QDRMgp1/BNdEnJEAPCIOmNgQvsi+BhpqijfM9KvFfRiPmGSt3Ddzw+oTaGCLneJwxFFq5mqTMwJXdQE2EzK4px2xruDqKZjHupz9yXev4=");
		PaytmOrder Order = new PaytmOrder(paramMap);*/


		/*Map<String, String> paramMap = new HashMap<String, String>();

		paramMap.put( "MID" , "Mihirt47560013295058");
		paramMap.put( "ORDER_ID" , "ORDER412546743");
		paramMap.put( "CUST_ID" , "CUST2365714547");
		paramMap.put( "INDUSTRY_TYPE_ID" , "Retail");
		paramMap.put( "CHANNEL_ID" , "WAP");
		paramMap.put( "TXN_AMOUNT" , "1");
		paramMap.put( "WEBSITE" , "worldpressplg");
		paramMap.put( "CALLBACK_URL" , "http://10.1.1.163/Paytm_App_PHP/verifyChecksum.php");
		paramMap.put( "CHECKSUMHASH" , "r0YDuJb7ayMpicpmH7hzcwwLfCs6MpLg4beLmZxXEpXgeIroNCXwiZmpRiGdQN1P3J5Ho\\/siJ8OUZZkgIjcmKdUTSn6DtbxhX4202sft\\/8M=");
*/
		/*paramMap.put("MID" , "5qY7p&!#&nXeYtG%");
		paramMap.put("ORDER_ID" , "ORDER412546");
		paramMap.put("CUST_ID" , "CUST545458");
		paramMap.put("INDUSTRY_TYPE_ID" , "Retail");
		paramMap.put("CHANNEL_ID" , "WAP");
		paramMap.put("TXN_AMOUNT" , "10");
		paramMap.put("WEBSITE" , "worldpressplg");
		paramMap.put("CALLBACK_URL" , "https://securegw.paytm.in/theia/paytmCallback?ORDER_ID=");
		paramMap.put("CHECKSUMHASH" , "w2QDRMgp1/BNdEnJEAPCIOmNgQvsi+BhpqijfM9KvFfRiPmGSt3Ddzw+oTaGCLneJwxFFq5mqTMwJXdQE2EzK4px2xruDqKZjHupz9yXev4=");*/

		//PaytmOrder Order = new PaytmOrder(paramMap);


		/*Service.initialize(Order, null);

		Service.startPaymentTransaction(this, true, true,
				new PaytmPaymentTransactionCallback() {

					@Override
					public void someUIErrorOccurred(String inErrorMessage) {
						// Some UI Error Occurred in Payment Gateway Activity.
						// // This may be due to initialization of views in
						// Payment Gateway Activity or may be due to //
						// initialization of webview. // Error Message details
						// the error occurred.

						Log.d("LOG", "UI ERROR " + inErrorMessage);
						Toast.makeText(getBaseContext(), "UI ERROR -- " + inErrorMessage, Toast.LENGTH_LONG).show();


					}

					@Override
					public void onTransactionResponse(Bundle inResponse) {
						Log.d("LOG", "Payment Transaction : " + inResponse);
						Toast.makeText(getApplicationContext(), "Payment Transaction response "+inResponse.toString(), Toast.LENGTH_LONG).show();
					}

					@Override
					public void networkNotAvailable() {
						// If network is not
						// available, then this
						// method gets called.
					}

					@Override
					public void clientAuthenticationFailed(String inErrorMessage) {
						// This method gets called if client authentication
						// failed. // Failure may be due to following reasons //
						// 1. Server error or downtime. // 2. Server unable to
						// generate checksum or checksum response is not in
						// proper format. // 3. Server failed to authenticate
						// that client. That is value of payt_STATUS is 2. //
						// Error Message describes the reason for failure.


						Log.d("LOG", "AUTHENTICATION ERROR " + inErrorMessage);
						Toast.makeText(getBaseContext(), "AUTHENTICATION ERROR -- " + inErrorMessage, Toast.LENGTH_LONG).show();

					}

					@Override
					public void onErrorLoadingWebPage(int iniErrorCode,
							String inErrorMessage, String inFailingUrl) {


						Log.d("LOG", "WEBPAGE ERROR " + inErrorMessage);
						Toast.makeText(getBaseContext(), "WEBPAGE ERROR -- " + inErrorMessage, Toast.LENGTH_LONG).show();

					}

					// had to be added: NOTE
					@Override
					public void onBackPressedCancelTransaction() {
						// TODO Auto-generated method stub
					}

					@Override
					public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {
						Log.d("LOG", "Payment Transaction Failed " + inErrorMessage);
						Toast.makeText(getBaseContext(), "Payment Transaction Failed ", Toast.LENGTH_LONG).show();
					}

				});*/

		//Create new order Object having all order information.

	}


	public void CallVolley(String a)
	{

		try {

            Random r = new Random(System.currentTimeMillis());
            orderid = "ORDER" + (1 + r.nextInt(2)) * 100000
                    + r.nextInt(100000);

            customerid="CUST" + (1 + r.nextInt(2)) * 10000
                    + r.nextInt(10000);

            StringRequest strRequest = new StringRequest(Request.Method.POST, a,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response)
                        {
                            try {

                                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();

                                JSONObject jsonObject = new JSONObject(response.toString());

                                CHECKSUMHASH1 = jsonObject.has("CHECKSUMHASH") ? jsonObject.getString("CHECKSUMHASH") : "";

                                Toast.makeText(MerchantActivity.this, CHECKSUMHASH1, Toast.LENGTH_SHORT).show();

                                CallPaytmIntegration();
                            }
                            catch (JSONException je)
                            {
                                je.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    })
            {
                @Override
                protected Map<String, String> getParams()
                {
                    HashMap<String,String> map=new HashMap<>();
                    map.put("MID","Mihirt47560013295058"); //Provided by Paytm
                    map.put("ORDER_ID",orderid); //unique OrderId for every request
                    map.put("CUST_ID",customerid); // unique customer identifier
                    map.put("INDUSTRY_TYPE_ID", "Retail"); //Provided by Paytm
                    map.put("CHANNEL_ID", "WAP"); //Provided by Paytm
                    map.put("TXN_AMOUNT", "1.00"); // transaction amount
                    map.put("WEBSITE", "APP_STAGING");//Provided by Paytm
                    map.put("CALLBACK_URL","https://pguat.paytm.com/paytmchecksum/paytmCallback.jsp");//Provided by Paytm

                    Log.e("MAP",map.toString());

                    return map;
                }
            };

            requestQueue.add(strRequest);

			/*JsonObjectRequest obreq;
			obreq = new JsonObjectRequest(Request.Method.POST,a,new JSONObject(map),
					new Response.Listener<JSONObject>() {
						@Override
						public void onResponse(JSONObject response) {
							try {

								//Toast.makeText(MerchantActivity.this, response.toString(), Toast.LENGTH_SHORT).show();

                                CHECKSUMHASH=response.has("CHECKSUMHASH")?response.getString("CHECKSUMHASH"):"";

                                Toast.makeText(MerchantActivity.this, CHECKSUMHASH, Toast.LENGTH_SHORT).show();

                                CallPaytmIntegration();

							}
							catch (Exception e)
							{
								Toast.makeText(getApplicationContext(), "Something went wrong..", Toast.LENGTH_SHORT).show();
							}
						}
					},
					new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							Toast.makeText(getApplicationContext(), "wrong.."+error.getMessage(), Toast.LENGTH_SHORT).show();
						}
					});

			obreq.setRetryPolicy(new DefaultRetryPolicy(600000,
					DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
					DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
			// Adds the JSON object request "obreq" to the request queue
			requestQueue.add(obreq);*/

		}
		catch (Exception e) {
			Toast.makeText(getApplicationContext(), "--" + e, Toast.LENGTH_SHORT).show();
		}

	}

	public void CallPaytmIntegration()
    {
        Service = PaytmPGService.getStagingService();

        Map<String, String> paramMap = new HashMap<String,String>();
        paramMap.put("MID","Mihirt47560013295058"); //Provided by Paytm
        paramMap.put("ORDER_ID",orderid); //unique OrderId for every request
        paramMap.put("CUST_ID",customerid); // unique customer identifier
        paramMap.put("INDUSTRY_TYPE_ID", "Retail"); //Provided by Paytm
        paramMap.put("CHANNEL_ID", "WAP"); //Provided by Paytm
        paramMap.put("TXN_AMOUNT", "1.00"); // transaction amount
        paramMap.put("WEBSITE", "APP_STAGING");//Provided by Paytm
        paramMap.put("CALLBACK_URL","https://pguat.paytm.com/paytmchecksum/paytmCallback.jsp");//Provided by Paytm
        paramMap.put( "CHECKSUMHASH" , CHECKSUMHASH1);

        PaytmOrder Order = new PaytmOrder(paramMap);

//Create Client Certificate object holding the information related to Client Certificate. Filename must be without .p12 extension.
//For example, if suppose client.p12 is stored in raw folder, then filename must be client.
        //PaytmClientCertificate Certificate = new PaytmClientCertificate ("password" , "filename" );

//Set PaytmOrder and PaytmClientCertificate Object. Call this method and set both objects before starting transaction.
        //Service.initialize(Order, Certificate);
//OR
        Service.initialize(Order, null);

//Start the Payment Transaction. Before starting the transaction ensure that initialize method is called.

        Service.startPaymentTransaction(this, true, true, new PaytmPaymentTransactionCallback() {

            @Override
            public void someUIErrorOccurred(String inErrorMessage) {
                Log.d("LOG", "UI Error Occur.");
                Toast.makeText(getApplicationContext(), " UI Error Occur. ", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onTransactionResponse(Bundle inResponse) {
                Log.d("LOG", "Payment Transaction : " + inResponse);
                Toast.makeText(getApplicationContext(), "Payment Transaction response "+inResponse.toString(), Toast.LENGTH_LONG).show();

                Log.e("RESPONSE",inResponse.toString());
            }

            @Override
            public void networkNotAvailable() {
                Log.d("LOG", "UI Error Occur.");
                Toast.makeText(getApplicationContext(), " UI Error Occur. ", Toast.LENGTH_LONG).show();
            }

            @Override
            public void clientAuthenticationFailed(String inErrorMessage) {
                Log.d("LOG", "UI Error Occur.");
                Toast.makeText(getApplicationContext(), " Severside Error "+ inErrorMessage, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onErrorLoadingWebPage(int iniErrorCode,
                                              String inErrorMessage, String inFailingUrl) {

            }
            @Override
            public void onBackPressedCancelTransaction() {
// TODO Auto-generated method stub
            }

            @Override
            public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {
                Log.d("LOG", "Payment Transaction Failed " + inErrorMessage);
                Toast.makeText(getBaseContext(), "Payment Transaction Failed ", Toast.LENGTH_LONG).show();
            }

        });
    }


}
