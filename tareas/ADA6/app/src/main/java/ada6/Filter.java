package ada6;

import javax.naming.event.ObjectChangeListener;

public abstract class Filter {
	Pipe pipe;

	public Filter(Pipe nextPipe){
		this.pipe = nextPipe;
	}

	protected abstract Object action(Object input);

	public void sendToPipe(Object input){
		Object outPut = action(input);
		System.out.println("sending data...");
		if(pipe!=null){
            pipe.sendToFilter(outPut);
        }
	}
}
