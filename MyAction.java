package fr.dauphine.javavance.td7;

import java.io.IOException;
import java.nio.file.Path;

public interface MyAction {
	
	void perform(Path p) throws IOException;
	

}
