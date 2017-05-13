package com.example.anton.gijonwifi.Data;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by Antón on 07/04/2017.
 * CLASE GSON OBTENIDA DIRECTAMENTE DE GOOGLE DEVELOPERS Y MODIFICADA PARA EL JSON
 * QUE UTILIZA ESTA APLICACIÓN
 */

public class GsonRequest<T> extends Request<T> {
    private final Gson gson = new GsonBuilder().serializeNulls().create();  //objeto gson
    private final Class<T> clazz; //clase java en la que se parsea el json
    private final Map<String, String> headers;  //cabeceras
    private final Response.Listener<T> listener;   //peticion
    private String key;  //key a saltarse en el json y acceder a "directorios" que es donde están los datos

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url URL of the request to make
     * @param clazz Relevant class object, for Gson's reflection
     * @param headers Map of request headers
     */
    public GsonRequest(String url, Class<T> clazz, Map<String, String> headers,
                       Response.Listener<T> listener, Response.ErrorListener errorListener, String key) {
        super(Method.GET, url, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
        this.key = key;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected void deliverResponse(T response) {
        if (response != null)
            listener.onResponse(response);
    }

    @Override
    //método que parsea los datos tras recibir la peticion
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            //objeto json completo a String
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            //método añadido para saltarse una jerarquía -> key que corresponde a"directorios"
            try {
                JSONObject jo = new JSONObject(json);
                json = jo.get(key).toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //retorna el objeto parseado
            return Response.success(
                    gson.fromJson(json, clazz),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }
}