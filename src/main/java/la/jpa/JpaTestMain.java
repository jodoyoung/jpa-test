package la.jpa;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
public class JpaTestMain {
	
	public static void main(String[] args) throws Exception {


		writeHomeHtmlByRequestInfoVO(makeRequestInfoVO());
		SpringApplication.run(JpaTestMain.class, args);
	}

	private static void writeHomeHtmlByRequestInfoVO(List<RequestInfoVO> requestInfoVOList) {
		String prefix = "<html><head><title>TEST JPA</title></head>\n<body>\n";
		String postfix = "</body></html>";
		String ss = "<b>%actionName%  %requestMethod%</b><br><form action='%actionName%' method='%requestMethod%'>\n";
		String sss = "";
		String ssss = "%paramName% <input type='text' name='%paramName%'/><br>\n";

		for (RequestInfoVO requestInfoVO: requestInfoVOList) {
			sss += ss.replaceAll("%actionName%", requestInfoVO.getUrl()).replaceAll("%requestMethod%", requestInfoVO.getHttpMethod());
			for (String param : requestInfoVO.getParamList()) {
				sss += ssss.replace("%paramName%", param);
			}
			sss += "<br><input type='submit'/></form><br>";
		}
		String result = prefix + sss + postfix;
		System.out.println(result);
		Path path = Paths.get("./src/main/webapp/home.jsp");
		try {
			Files.deleteIfExists(path);
			Files.write(path, result.getBytes(), StandardOpenOption.CREATE_NEW,StandardOpenOption.WRITE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}




	private static List<RequestInfoVO> makeRequestInfoVO() throws ClassNotFoundException, IOException {
		List<RequestInfoVO> requestInfoVOList = new ArrayList<>();
		List<Class> clazzList = getClasses("la.jpa.controller");
		for (Class clazz : clazzList) {
			Method[] declaredMethods = clazz.getDeclaredMethods();
			for (Method method : declaredMethods) {
				RequestMapping annotation = method.getAnnotation(RequestMapping.class);
				if (annotation == null) {
					continue;
				}
				String[] pathArray = annotation.value();
				if (pathArray.length == 0) {
					continue;
				}
				RequestInfoVO requestInfoVO = new RequestInfoVO();
				requestInfoVO.setUrl(pathArray[0]);
				RequestMethod[] requestMethod = annotation.method();
				if (requestMethod.length != 0) {
					requestInfoVO.setHttpMethod(requestMethod[0].toString());
				}

				Parameter[] parameters = method.getParameters();
				for (Parameter parameter : parameters) {
					if (parameter.isNamePresent()) {
						requestInfoVO.addParamToParamList(parameter.getName());
					}
				}
				requestInfoVOList.add(requestInfoVO);
			}
		}

		return requestInfoVOList;
	}

	private static List<Class> getClasses(String packageName)
			throws ClassNotFoundException, IOException {
		ClassLoader classLoader = JpaTestMain.class.getClassLoader();
		assert classLoader != null;

		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);

		List<Class> clazzList = new ArrayList();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			clazzList.addAll(findClasses(new File(resource.getFile()), packageName));
		}
		return clazzList;
	}

	private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
		List classes = new ArrayList();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			String fileName = file.getName();
			if (file.isDirectory()) {
				classes.addAll(findClasses(file, packageName + "." + fileName));
			} else if (fileName.endsWith(".class")) {
				int removeExtentionIdx = fileName.length() - 6;
				classes.add(Class.forName(packageName + '.' +  fileName.substring(0, removeExtentionIdx)));
			} else {
				continue;
			}
		}
		return classes;
	}

}
