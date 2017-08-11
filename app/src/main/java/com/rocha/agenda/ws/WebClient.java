package com.rocha.agenda.ws;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by wesley on 08/02/17.
 */

public class WebClient {
    public String post2(String json){
        try {
            URL url = new URL("https://www.caelum.com.br/mobile");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //informando o tipo de dados que estou enviando e o tipo que aceito
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            //quero fazer um Post
            connection.setDoOutput(true);
            //me retorna uma saída onde devo escrever (stream de saída)
            PrintStream output = new PrintStream(connection.getOutputStream());
            output.println(json);

            connection.connect();

            //stream de entrada
            Scanner scanner = new Scanner(connection.getInputStream());
            String resposta = scanner.next();

            return resposta;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String post(String json){
        try {
            URL url = new URL("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-22.930153,%20-43.207986&radius=1000&type=bank&key=%20AIzaSyDKjqzzJljDhXZuQT1e3cLsSpFS2Y9_YwM");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //informando o tipo de dados que estou enviando e o tipo que aceito
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            //quero fazer um Post
            connection.setDoOutput(true);
            //me retorna uma saída onde devo escrever (stream de saída)
            PrintStream output = new PrintStream(connection.getOutputStream());
            //output.println(json);

            connection.connect();

            //stream de entrada
            Scanner scanner = new Scanner(connection.getInputStream());
            String resposta = scanner.next();

            return resposta;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
