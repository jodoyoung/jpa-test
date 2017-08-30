package la.jpa;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public final class ApiGenerator {

	private ApiGenerator() {
		throw new AssertionError("No la.jpa.ApiGenerator instances for you!");
	}

	public static List<ApiInfoVO> makeRequestInfoVO() throws ClassNotFoundException, IOException {
		List<ApiInfoVO> requestInfoVOList = new ArrayList<>();
		List<Class<?>> clazzList = getClasses("la.jpa.controller");
		for (Class<?> clazz : clazzList) {
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
				ApiInfoVO requestInfoVO = new ApiInfoVO();
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

	private static List<Class<?>> getClasses(String packageName) throws ClassNotFoundException, IOException {
		ClassLoader classLoader = JpaTestMain.class.getClassLoader();
		assert classLoader != null;

		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);

		List<Class<?>> clazzList = new ArrayList<>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			clazzList.addAll(findClasses(new File(resource.getFile()), packageName));
		}
		return clazzList;
	}

	private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
		List<Class<?>> classes = new ArrayList<>();
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
				classes.add(Class.forName(packageName + '.' + fileName.substring(0, removeExtentionIdx)));
			} else {
				continue;
			}
		}
		return classes;
	}

}