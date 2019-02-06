/**
 * 
 */
package com.java.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

/**
* @author Kidney
* 创建时间：2019年1月27日 下午12:00:18
* Description:多文件上传 
*/
/**MultipartRequest
 * @author KIDNEY
 *
 */
public class FilesUpload {
	public static Map<String, Object> uploadFiles(HttpServletRequest request, MultipartRequest files) {
		Map<String, Object> filesPathMap = new HashMap<String, Object>();
		try {
			Map<String, MultipartFile> filesMap = files.getFileMap();
			Set<String> keyset = filesMap.keySet();
			for (String key : keyset) {
				MultipartFile file = files.getFile(key);
				String originalFilename = file.getOriginalFilename();
				String uuid = UUID.randomUUID().toString();
				
				String path = request.getServletContext().getRealPath("/upload");
				
				String basePath = path.split("webapps")[0] + "webapps\\upload";
//				System.out.println(basePath);
                File fileUpload = new File(basePath);
                if (!fileUpload.exists()) {// 经过此方法后,upload文件夹一定是存在的
                    fileUpload.mkdir();
                }
                // 保存文件
                String filePath = basePath + "\\" + uuid + originalFilename;
                file.transferTo(new File(filePath));
				//返回set<> key=uuid, Vaule = uuid+fileName
				String positivePath = "upload/"+uuid+originalFilename;
				filesPathMap.put(uuid, positivePath);//key必须不一样 uuid key
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filesPathMap;
	}
}
