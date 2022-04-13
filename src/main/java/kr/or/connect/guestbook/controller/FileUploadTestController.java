package kr.or.connect.guestbook.controller;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadTestController {
	@GetMapping("/testUpload")
	public void testUpload() {
		
	}
	
	@PostMapping("/testUploadPost")
	public void testUploadPost(ArrayList<MultipartFile> files) {
		Logger logger = Logger.getLogger(FileUploadTestController.class);
		files.forEach(file -> {
			logger.info("name: " + file.getOriginalFilename());
			logger.info("size: " + file.getSize());
		});
	}
}
