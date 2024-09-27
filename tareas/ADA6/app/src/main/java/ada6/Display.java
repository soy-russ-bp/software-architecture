package ada6;

import java.util.List;

public class Display extends Filter{
	public Display(Pipe pipe){
		super(pipe);
	}

	@Override
	protected Object action(Object input) {
		System.out.println("displaying");
		
		List<String> sortedWords = (List<String>)input;
		sortedWords.forEach(System.out::println);
		return sortedWords;
	}
	
}
