package xyz.bumbing.http.config;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

public class RequestWrapper extends HttpServletRequestWrapper {
	private final Charset encoding;
	private byte[] b;

	public RequestWrapper(HttpServletRequest request) throws IOException {

		super(request);

		String charEncoding = request.getCharacterEncoding(); // 인코딩 설정
		this.encoding = StringUtils.isBlank(charEncoding) ? StandardCharsets.UTF_8 : Charset.forName(charEncoding);

		InputStream is = super.getInputStream();
		b = IOUtils.toByteArray(is);

		System.out.println("들어옴" + b.length);

	}

	public void test() {
		System.out.println("ss");
	}

	@Override
	public BufferedReader getReader() {
		return new BufferedReader(new InputStreamReader(this.getInputStream(), this.encoding));
	}

	@Override
	public ServletInputStream getInputStream() {
		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.b);

		return new ServletInputStream() {
			@Override
			public boolean isFinished() {
				return false;
			}

			@Override
			public boolean isReady() {
				return false;
			}

			@Override
			public void setReadListener(ReadListener readListener) {
				// Do nothing
			}

			public int read() {

				return byteArrayInputStream.read();
			}
		};
	}

}