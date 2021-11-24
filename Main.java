package fr.dauphine.javavance.td7;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) {
		Path path = Paths.get("src/fr/dauphine/javavance/td7");
		DirMonitor rep = new DirMonitor(path);
		rep.printAll();
		//System.out.println(rep.sizeOfFiles());
		//System.out.println(rep.mostRecent());	
	}

}
