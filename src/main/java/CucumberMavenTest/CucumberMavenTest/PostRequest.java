package CucumberMavenTest.CucumberMavenTest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features= "classpath:Feature")
public class PostRequest
{
	String res;
	@When("request sent")
	public String getResponse() throws Exception
	{
		String url = "http://52.209.79.99:8090/v1/validate";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		//adding request header
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		
		String requestProp = "{\"interfaceData\": {\"identification\": \"priya@gmail.com\", \"osId\": \"928637537\",\"psId\": \"priya@gmail.com\",\"psIdList\": [\"ossaifelunwa330@gmail.com\", \"ossaisandra180@gmail.com\", \"ogwudeborah63@gmail.com\", \"oyowejoy4@gmail.com\", \"emmaigube@gmail.com\"],\"installedApps\": [\"PDF Reader\", \"Lite\", \"Oxford English\", \"Chemistry Quiz\", \"Organic Chemistry\", \"Opera Mini\", \"Instagram\", \"Opera Mobile Store\", \"Chemical Formulas\", \"Basic Chemistry e-Learning\", \"100 cakes & bakes recipes\", \"Facebook\", \"BeautyPlus\", \"Zoto\", \"Bible KJV\", \"MultiVersionsBibleStatic\", \"Text Messages For All Occassions\", \"Xender\", \"Adobe Acrobat\", \"MX Player\", \"WhatsApp\", \"360 Security\", \"U-Learn\", \"Carlcare\", \"Bible KJV\", \"PowerCalc\", \"Recipes From Nigeria\", \"Messenger\", \"English\", \"Baking recipes\", \"Photo Grid\", \"Gospel Hymns\", \"AntiVirus\", \"HolyBible\", \"Merriam-Webster Dictionary\"],\"appsCheckSum\": \"0a3cb4f60e98421886f26cd2a860f7cb\",\"version\": \"2.3\",\"type\": \"MOB\",\"data\": {\"os\": \"android\"}},\"user\": {\"msisdn\": \"9091909190\",\"categoryCode\": \"CUST\",\"isNewUser\": \"false\",\"sequenceNumber\":0,\"isTCAccepted\": \"false\"},\"operationCode\": \"VCID\"}";
		byte[] out = requestProp.getBytes(StandardCharsets.UTF_8);
		int length = out.length;
		con.setFixedLengthStreamingMode(length);
		con.setDoOutput(true);
		
		con.connect();
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(requestProp);
		wr.flush();
		wr.close();
		
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		res =  response.toString();    //instead of return
		return res;
	}
	@Then("return response code")
	public void printResponse()
	{
		System.out.println("Response : " + res);
	}
	public static void main(String args[])
	{
		PostRequest obj=new PostRequest();
		obj.printResponse();
	}
}