package com.srl.fileUpload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
public class fileUpload extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // 设置编码
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		Collection<Part> parts = request.getParts();
		System.out.println(parts.isEmpty());
		for (Part part : parts) {
			if (part.getContentType() != null) {
				// 获取form-data; name="file"; filename="asp.NET.docx"
				String filename = part.getHeader("content-disposition");
				// 截取filename
				filename = filename.substring(filename.indexOf("filename=\"")
						+ "filename=\"".length(), filename.length() - 1);
				// 非空判断
				if (filename != null && filename != "") {
					InputStream is = part.getInputStream();
					File file = new File("d:\\" + filename);
					FileOutputStream fos = new FileOutputStream(file);

					byte[] b = new byte[1024];
					int bcount = 0;// 最后读取到哪里
					bcount = is.read(b);
					while (bcount != -1) {
						fos.write(b, 0, bcount);
						bcount = is.read(b);
					}
					is.close();
					fos.close();
				}
			}
		
		}
		
	}
}
