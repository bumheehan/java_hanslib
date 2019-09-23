package xyz.bumbing.http.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HttpAppController {

	@RequestMapping("/")
	public String main(HttpServletRequest req) throws IOException {
		return "input";
	}

	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String result(HttpServletRequest req, @RequestParam(name = "url") String url) throws IOException {
//		BufferedReader br = req.getReader();
//		System.out.println(br.read());
		System.out.println(url);
		return "result";
	}

}
