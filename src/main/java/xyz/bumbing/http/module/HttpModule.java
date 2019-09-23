package xyz.bumbing.http.module;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class HttpModule {

	public HttpModule() {
	}

	public static void connect(String urls) throws Exception {
		URL url = new URL(urls);
		// HTTP Connection 구하기
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		// 요청 방식 설정 ( GET or POST or .. 별도로 설정하지않으면 GET 방식 )
		conn.setRequestMethod("GET");
		// 연결 타임아웃 설정
		conn.setConnectTimeout(3000); // 3초
		// 읽기 타임아웃 설정
		conn.setReadTimeout(3000); // 3초
		// Request Header값 셋팅 setRequestProperty(String key, String value)
//		conn.setRequestProperty("NAME", "name");
//		conn.setRequestProperty("MDN", "mdn");
//		conn.setRequestProperty("APPID", "appid");

		// 서버 Response Data를 xml 형식의 타입으로 요청.
//		conn.setRequestProperty("Accept", "application/xml");

		// 서버 Response Data를 JSON 형식의 타입으로 요청.
//		conn.setRequestProperty("Accept", "application/json");

		// 타입설정(text/html) 형식으로 전송 (Request Body 전달시 text/html로 서버에 전달.)
		conn.setRequestProperty("Content-Type", "text/html");

		// 타입설정(text/html) 형식으로 전송 (Request Body 전달시 application/xml로 서버에 전달.)
//		conn.setRequestProperty("Content-Type", "application/xml");

		// 타입설정(application/json) 형식으로 전송 (Request Body 전달시 application/json로 서버에 전달.)
//		conn.setRequestProperty("Content-Type", "application/json");

		// 셋트 doOutput 쓰고 OutputStream 써야함
		// OutputStream으로 POST 데이터를 넘겨주겠다는 옵션.
//		conn.setDoOutput(true);
		// InputStream으로 서버로 부터 응답을 받겠다는 옵션.
//		conn.setDoInput(true);
		// Request Body에 Data를 담기위해 OutputStream 객체를 생성.
//		OutputStream os = conn.getOutputStream();
		// Request Body에 Data 셋팅.
//		os.write(body.getBytes("Receive?"));
		// Request Body에 Data 입력.
//		os.flush();
		// OutputStream 종료.
//		os.close();

		// 요청 방식 구하기
//		System.out.println("getRequestMethod():" + conn.getRequestMethod());
		// 응답 콘텐츠 유형 구하기
//		System.out.println("getContentType():" + conn.getContentType());
		// 응답 코드 구하기
//		System.out.println("getResponseCode():" + conn.getResponseCode());
		// 응답 메시지 구하기
//		System.out.println("getResponseMessage():" + conn.getResponseMessage());

		// 요청 헤더의 정보를 모두 출력

		for (Map.Entry<String, List<String>> header : conn.getRequestProperties().entrySet()) {
			for (String value : header.getValue()) {
				System.out.println(header.getKey() + " : " + value);
			}
		}
		// 응답 헤더의 정보를 모두 출력
//		for (Map.Entry<String, List<String>> header : conn.getHeaderFields().entrySet()) {
//			for (String value : header.getValue()) {
//				System.out.println(header.getKey() + " : " + value);
//			}
//		}

		// 응답 내용(BODY) 구하기
		try (InputStream in = conn.getInputStream(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {

			byte[] buf = new byte[1024 * 8];
			int length = 0;
			while ((length = in.read(buf)) != -1) {
				out.write(buf, 0, length);
			}
			System.out.println(new String(out.toByteArray(), "UTF-8"));
		}

		// 접속 해제
		conn.disconnect();
	}

}
