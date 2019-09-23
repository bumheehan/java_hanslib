package xyz.bumbing.http;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xyz.bumbing.http.module.MultipartUtility;

public class HttpAppClient {
	public static void main(String[] args) throws IOException {
		MultipartUtility multipart = new MultipartUtility("http://localhost:8080/http/test", "UTF-8");

		Map<String, String> fields = new HashMap<>();
		fields.put("testparam", "testparamvalue");

		Map<String, File> files = new HashMap<>();
		files.put("testfile", new File("C:/HNC/HwpHjDic.dll"));

		// In your case you are not adding form data so ignore this
		/* This is to add parameter values */

		for (String key : fields.keySet()) {
			multipart.addFormField(key, fields.get(key));
		}

		// add your file here.
		/* This is to add file content */
		for (String key : files.keySet()) {
			multipart.addFilePart(key, files.get(key));
		}

		List<String> response = multipart.finish();
		for (String line : response) {
			System.out.println(line);
		}
	}
}
