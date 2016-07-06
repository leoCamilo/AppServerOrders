package com.app.appsender;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static int count = 0;
    final String dtdPedido = "<?xml version='1.0' encoding='utf-8'?>\n<!DOCTYPE pedidos [\n\n <!ELEMENT pedidos (pedido)>\n\n <!ELEMENT pedido (cliente, itemDeLinha+)>\n <!ATTLIST pedido\n   number  CDATA #REQUIRED>\n\n <!ELEMENT cliente (endereco)>\n <!ATTLIST cliente\n   customerID  CDATA #REQUIRED\n   name  CDATA #REQUIRED>\n\n <!ELEMENT endereco (#PCDATA)>\n <!ATTLIST endereco\n   street  CDATA #REQUIRED\n   city  CDATA #REQUIRED\n   state  CDATA #REQUIRED\n   postalCode  CDATA #REQUIRED\n   country  CDATA #REQUIRED>\n\n <!ELEMENT itemDeLinha (produto)>\n <!ATTLIST itemDeLinha\n   quantity  CDATA #REQUIRED>\n\n <!ELEMENT produto ((software | hardware), fornecedor)>\n <!ATTLIST produto\n   type CDATA #REQUIRED\n   description  CDATA #REQUIRED\n   unityPrice  CDATA #REQUIRED>\n\n <!ELEMENT software (#PCDATA)>\n <!ATTLIST software\n   version  CDATA #REQUIRED>\n\n <!ELEMENT hardware (#PCDATA)>\n <!ATTLIST hardware\n   assembly  CDATA #REQUIRED>\n\n <!ELEMENT fornecedor (endereco)>\n <!ATTLIST fornecedor\n   number  CDATA #REQUIRED>\n ]>";
    final String pedido = "\n\n<pedidos>\n <pedido number='%number%'>\n  <cliente\n    customerID='987654'\n    name='Nome da Silva'>\n   <endereco\n     street='Rua 1'\n     city='Cidade'\n     state='Estado'\n     postalCode='654654'\n     country='Pais'>\n   </endereco>\n  </cliente>\n  <itemDeLinha\n    quantity='%qtd%'>\n   <produto\n     description='%desc%'\n     unityPrice='%unityPrice%'\n     type='software'>\n    <software\n      version='15.3'>\n    </software>\n    <fornecedor\n      number='231321'>\n     <endereco\n      street='Rua 2'\n      city='Cidade3'\n      state='Estado 2'\n      postalCode='654654'\n      country='Pais'>\n     </endereco>\n    </fornecedor>\n   </produto>\n\n  </itemDeLinha>\n </pedido>\n</pedidos>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // new Thread(new Receiver()).start();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String tmp = pedido;

                    EditText et = (EditText) findViewById(R.id.desc);

                    tmp = tmp.replace("%number%", ++count + "");
                    tmp = tmp.replace("%desc%", et.getText().toString());
                    et = (EditText) findViewById(R.id.unityPrice);
                    tmp = tmp.replace("%unityPrice%", et.getText().toString());
                    et = (EditText) findViewById(R.id.quantity);
                    tmp = tmp.replace("%qtd%", et.getText().toString());

                    TextView tv = (TextView)findViewById(R.id.main_tv);
                    tv.setText(tmp); ////Set the text to text view.

                    new Network().execute(dtdPedido + tmp);
                    Snackbar.make(view, "Pedido " + count + " enviado.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
