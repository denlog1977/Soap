package com.example.soap;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class DataLoader extends AsyncTask<Void, Void, String> {
    private static final String NAMESPACE = "http://web/tfk/ExchangeTFK";
    private static final String URL = "http://kamaz.ddns.net:10100/ut10tfk/ws/ExchangeTFK.1cws";
    private static final String METHOD_NAME = "SayHello";
    //    private static final String SOAP_ACTION = "http://kamaz.ddns.net:10100/wsdlAcceptor";
//    private static final String SOAP_ACTION = NAMESPACE + "/" +  METHOD_NAME;
    private static final String SOAP_ACTION = "http://web/tfk/ExchangeTFK#ExchangeTFK:SayHello";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {

        Log.i("SOAP", "doInBackground");


        try {

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
//            request.addProperty("IsFirstRequest", true);
            envelope.setOutputSoapObject(request);

            Log.i("SOAP", "envelope");

            //HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            //Для применения HttpTransportBasicAuthSE достаточно просто изменить строку в DataLoader
            //HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            //на
            //HttpTransportBasicAuthSE androidHttpTransport = new HttpTransportBasicAuthSE(URL, "basicLogin", "authPassword");

            HttpTransportBasicAuthSE androidHttpTransport = new HttpTransportBasicAuthSE(URL, "ws", "2018");

            androidHttpTransport.debug = true;

            try {
                Log.i("SOAP", " - 1 - androidHttpTransport");
                androidHttpTransport.call(SOAP_ACTION, envelope);
                Log.i("SOAP", " - 2 - androidHttpTransport");
                SoapObject resultsRequestSOAP = (SoapObject) envelope.bodyIn;
                Log.i("SOAP", "Ответ вэбсервиса: " + resultsRequestSOAP.toString());
                //System.out.println("Response: "+resultsRequestSOAP.toString());


                //Toast.makeText(MainActivity, " resultsRequestSOAP: " + resultsRequestSOAP.toString(), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("SOAP", e.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("SOAP", e.toString());
        }
        return "";
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}