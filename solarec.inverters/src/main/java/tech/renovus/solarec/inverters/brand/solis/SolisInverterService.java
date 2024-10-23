package tech.renovus.solarec.inverters.brand.solis;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import tech.renovus.solarec.inverters.common.InverterService;
import tech.renovus.solarec.vo.db.data.ClientVo;

/**
 * Documentation: https://drive.google.com/file/d/1NpSK5Dc4ricvTQsMUXoAMafrRnh7GgoS/view
 */
public class SolisInverterService implements InverterService {

	//--- Private constants ---------------------
	private static final String URL_PROD	= "https://www.soliscloud.com:13333";
	
	private static final String ENDPOINT_DEVICE_LIST	= "/v1/api/inverterList";
	
	//--- Private properties --------------------
	private final SimpleDateFormat formatDate							= new SimpleDateFormat("yyyyMMdd");
	private ClientVo cliVo;

	
	//--- Private methods -----------------------
	private String getDigest(String test) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");

		md.update(test.getBytes());
		return Base64.getEncoder().encodeToString(md.digest());
	}

	private String getGMTTime() {
		Calendar cd = Calendar.getInstance();
		SimpleDateFormat sdf = new

		SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		return sdf.format(cd.getTime());
	}
	
	private String generateHMACSHA1(String key, String message) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(secretKeySpec);
        byte[] hmacSha1Bytes = mac.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(hmacSha1Bytes);
    }
	
	private Map<String, String> getHeaders(String contentMd5, String apiId, String apiSecret, String endpoint) throws InvalidKeyException, NoSuchAlgorithmException {
		String date = this.getGMTTime();
		String Sign = Base64.getEncoder().encodeToString(generateHMACSHA1(apiSecret, "POST" + "\n" + contentMd5 + "\n" + "application/json;charset=UTF-8" + "\n" + date + "\n" + endpoint).getBytes());
		String authorization = "API " + apiId + ":" + Sign;

		Map<String, String> headers = new HashMap<>();
		headers.put("Content-MD5", contentMd5);
		headers.put("Date", date);
		headers.put("Authorization", authorization);
		
		return headers;
	}
	
	//--- Overridden methods --------------------
	@Override public void prepareFor(ClientVo client) {
		this.cliVo = client;
	}
	
	@Override public boolean canRetrieve() { return true; }
	
	@Override public boolean continueWithStats() { return true; }
	
	@Override public String getReasonWhyCantRetrieve() { return null; }
	
	@Override
	public InverterData retrieveData() {
		// TODO Auto-generated method stub
		return null;
	}

}
