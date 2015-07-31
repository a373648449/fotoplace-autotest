package com.fotoplace.database;

 

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.pajk.test.client.ResultDO;

public class JsonRequestUtil
{
  public static ResultDO doPost(String address_url, String jsonString,HttpSession session)
  {
    ResultDO result = new ResultDO();
    CloseableHttpClient client = HttpClients.createDefault();
    HttpPost post = new HttpPost(address_url);
    StringEntity myEntity = new StringEntity(jsonString, ContentType.APPLICATION_JSON);

    post.setEntity(myEntity);
    String responseContent = null;
    CloseableHttpResponse response = null;
    try {
      response = client.execute(post);
      result.setStatusCode(response.getStatusLine().getStatusCode());
      if (response.getStatusLine().getStatusCode() == 200) {
        HttpEntity entity = response.getEntity();
        responseContent = EntityUtils.toString(entity, "UTF-8");
        result.setResultString(responseContent);
      }
    } catch (ClientProtocolException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (response != null)
          response.close();
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        try {
          if (client != null)
            client.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return result;
  }

  public static String do_urlencoded_post(String url_address, String request_body)
  {
    String body = "";
    try
    {
      URL url = new URL(url_address);
      URLConnection urlConnection = url.openConnection();

      urlConnection.setDoOutput(true);

      urlConnection.setRequestProperty("content-type", "application/x-www-form-urlencoded");

      OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());

      out.write(request_body);
      out.flush();
      out.close();

      InputStream inputStream = urlConnection.getInputStream();

      body = ConvertStream2Json(inputStream);
      System.out.println(body);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    return body;
  }

  private static String ConvertStream2Json(InputStream inputStream)
  {
    String jsonStr = "";

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    byte[] buffer = new byte[1024];
    int len = 0;
    try
    {
      while ((len = inputStream.read(buffer, 0, buffer.length)) != -1)
      {
        out.write(buffer, 0, len);
      }

      jsonStr = new String(out.toByteArray());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return jsonStr;
  }
  
}