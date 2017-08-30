package la.jpa;

import java.util.ArrayList;
import java.util.List;

public class ApiInfoVO {
	private String url = null;
	private String httpMethod = null;
	private List<String> paramList = new ArrayList<>();

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public List<String> getParamList() {
		return paramList;
	}

	public void addParamToParamList(String param) {
		this.paramList.add(param);
	}
}
