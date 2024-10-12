package ada6;
public class Pipe {
	private Filter filter;

	public Pipe(Filter nextFilter) {
		this.filter = nextFilter;
	}

	public void sendToFilter(Object inputString) {
		System.out.println("in pipe");
		filter.sendToPipe(inputString);
	}

}
