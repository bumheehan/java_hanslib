package xyz.bumbing.http.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class HttpAppController {

	@RequestMapping("/")
	public String main() {

		return "input";
	}

	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String result(MultipartHttpServletRequest req) throws IOException {

		System.out.println(req.getFile(req.getFileNames().next()).getSize() + "");
		System.out.println(req.getRequestHeaders());
		System.out.println(req.getInputStream());
		return "result";
	}

	public String getBody
}
