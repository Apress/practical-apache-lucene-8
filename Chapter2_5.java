public class TestClass {
	DemoIndexer demoIndexer;
	List<Files> files;

	public TestClass(List<Files> files) {
		this.files = files;
	}

	public static void main(String[] args) {
		// args[0] is the directory name
		try {
			index();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void index() throws IOException {
		demoIndexer.index(files);
	}
}
