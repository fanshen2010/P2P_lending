package cn.com.p2p.framework.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpClientUtils {

	private static final Log logger = LogFactory.getLog(HttpClientUtils.class);

	public static String SendData(String urlStr, String data) {

		URL url = null;
		HttpURLConnection httpURLConnection = null;
		BufferedWriter out = null;
		BufferedReader br = null;
		try {
			logger.debug("[HttpClientUtils][SendData][start][urlStr]" + urlStr
					+ "[data]" + data);
			url = new URL(urlStr);
			httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setConnectTimeout(5000);
			httpURLConnection.setReadTimeout(10000);
			httpURLConnection.setRequestProperty("content-type",
					"application/xml");
			httpURLConnection.connect();
			out = new BufferedWriter(new OutputStreamWriter(
					httpURLConnection.getOutputStream(), "UTF-8"));
			out.write(data);
			out.flush();
			out.close();
			out = null;
			InputStream inStrm = httpURLConnection.getInputStream();

			br = new BufferedReader(new InputStreamReader(inStrm));

			String line = null;
			StringBuilder sb = new StringBuilder();

			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			httpURLConnection.disconnect();
			httpURLConnection = null;

			logger.debug("[HttpClientUtils][SendData][end][return]"
					+ sb.toString());
			return sb.toString();
		} catch (Exception ex) {

			logger.error("[HttpClientUtils][SendData][end][error]", ex);

		} finally {
			if (httpURLConnection != null) {
				httpURLConnection.disconnect();
				httpURLConnection = null;
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {

				}
				out = null;
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {

				}
				br = null;
			}

		}

		return null;
	}
}
