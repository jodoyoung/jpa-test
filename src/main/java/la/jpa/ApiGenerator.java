package la.jpa;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.java2d.pipe.SpanShapeRenderer;

public final class ApiGenerator {

	private ApiGenerator() {
		throw new AssertionError("No la.jpa.ApiGenerator instances for you!");
	}

	private static List<ApiInfoVO> apiInfoVOList;

	public static List<ApiInfoVO> getApiInfoVOList (){
		return apiInfoVOList;
	}

	static {
		try {
			apiInfoVOList = makeRequestInfoVO();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static List<ApiInfoVO> makeRequestInfoVO() throws ClassNotFoundException, IOException {

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
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		assert classLoader != null;

		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);

		List<Class<?>> clazzList = new ArrayList<>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			ClassFileVisitor classFileVisitor = new ClassFileVisitor(packageName);
			Files.walkFileTree(Paths.get(resource.getFile().substring(1)), classFileVisitor);
			clazzList.addAll(classFileVisitor.getClasses());
		}
		return clazzList;
	}

	private static class ClassFileVisitor extends SimpleFileVisitor<Path> {
		private final List<Class<?>> classes = new ArrayList();
		private final String packageName;

		public ClassFileVisitor (String packageName) {
			this.packageName = packageName;
		}

		public List<Class<?>> getClasses() {
			return classes;
		}

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			String fileName = file.toFile().getName();

			if (!Files.isDirectory(file) && fileName.endsWith(".class")) {
				int removeExtentionIdx = fileName.length() - 6;
				try {
					classes.add(Class.forName(packageName + '.' + fileName.substring(0, removeExtentionIdx)));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			return super.visitFile(file, attrs);
		}
	}
}