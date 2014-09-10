package br.com.odc.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class TestBigIP {

	public static void main(final String[] args) {
		try{
			final Properties properties = new Properties();
			properties.load(TestBigIP.class.getResourceAsStream("/bigip.properties"));
			System.out.println("Arquivo de propriedade carregado.");

			final String bigIPURL = properties.getProperty("url");
			System.out.println("Iniciando teste de conexao para a url: " + bigIPURL);
			final URL url = new URL(bigIPURL);
			final HttpURLConnection connectionCode = (HttpURLConnection) url.openConnection();
			System.out.println("Response Code: " + connectionCode.getResponseCode());

			final HttpURLConnection connectionHTML = (HttpURLConnection) url.openConnection();
			System.out.println("Response Content: ");
			String line;
			connectionHTML.setRequestMethod("GET");
			final BufferedReader rd = new BufferedReader(new InputStreamReader(connectionHTML.getInputStream()));
	        while ((line = rd.readLine()) != null) {
	        	System.out.println(line);
	        }
	        rd.close();
		} catch (final Exception e){
			e.printStackTrace();
		}
	}
}
