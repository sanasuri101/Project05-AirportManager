public class DataObject {
	private InputType inputType;
	private String hostName;
	private int port;	
	private Integer result;
	
	public DataObject(InputType inputType)
	{
		this.inputType = inputType;
	}

	public InputType getInputType() {
		return inputType;
	}

	public String getHostName() {
		return hostName;
	}

	public int getPort() {
		return port;
	}

	public Integer getResult() {
		return result;
	}

	public void setInputType(InputType inputType) {
		this.inputType = inputType;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setResult(Integer result) {
		this.result = result;
	}
}
