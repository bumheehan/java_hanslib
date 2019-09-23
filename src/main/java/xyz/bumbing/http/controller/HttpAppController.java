package xyz.bumbing.http.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import xyz.bumbing.http.module.MultipartUtility;

@Controller
@RequestMapping("/http")
public class HttpAppController {

	@RequestMapping("/input")
	public String main(HttpServletRequest req) throws IOException {
		return "http/input";
	}

	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String result(MultipartHttpServletRequest req, Model mo) throws IOException {

		MultipartUtility multipart = new MultipartUtility(req.getParameter("url"), "UTF-8");

		MultipartFile mf = req.getFile("file");
		if (mf.getBytes().length != 0) {
			File convFile = new File(mf.getOriginalFilename());
			mf.transferTo(convFile);
			Map<String, File> files = new HashMap<>();
			files.put("testfile", convFile);

			for (String key : files.keySet()) {
				multipart.addFilePart(key, files.get(key));
			}
		}

		Map<String, String> fields = new HashMap<>();
		fields.put("param", req.getParameter("param"));
		for (String key : fields.keySet()) {
			multipart.addFormField(key, fields.get(key));
		}

		List<String> response = multipart.finish();
		for (String line : response) {
			System.out.println(line);
		}

		mo.addAttribute("list", response);

		return "http/result";
	}

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public @ResponseBody String submit(HttpServletRequest req) throws IOException {
		System.out.println(req.getParameter("testparam"));

		return "ResponseBody";
	}

}
