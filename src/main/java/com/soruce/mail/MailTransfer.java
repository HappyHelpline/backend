package com.soruce.mail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.springframework.http.HttpEntity;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.source.dao.impl.Appoint_BookDaoImpl;

import lombok.extern.log4j.Log4j;
@Log4j
public class MailTransfer {

	 public static String postDataEmail(String requestUrl,String postData) {
			StringBuilder reqJson = null;
			HttpURLConnection connection = null;
			BufferedReader in = null;
			StringBuilder urlString = new StringBuilder();
			try {
				urlString.append(requestUrl);
				URL url = new URL(urlString.toString());
				connection = (HttpURLConnection) url.openConnection();
				connection.setDoOutput(true);
				connection.setRequestMethod("POST");
				connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				
				out.write("Request="+postData);
				out.close();
				int responseCode = connection.getResponseCode();
				reqJson = new StringBuilder("");
				if (responseCode == 200) {
					in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					String responseString;
					while ((responseString = in.readLine()) != null) {
						reqJson.append(responseString);
					}
				} else {
					in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
					String responseString;
					while ((responseString = in.readLine()) != null) {
						reqJson.append(responseString);
					}
					return reqJson.toString();
				}
				
				System.out.println("Response String From Email Server {}"+reqJson.toString());
				
				in.close();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (ProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (in != null) {
						in.close();
					}
					if (connection != null) {
						connection.disconnect();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return reqJson.toString();
		}
}
