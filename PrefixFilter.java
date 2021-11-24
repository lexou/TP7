package fr.dauphine.javavance.td7;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PrefixFilter implements DirectoryStream.Filter<Path>{
	private DirMonitor directory;
	private long n;
	
	//Constructeur
	public PrefixFilter(DirMonitor d, long x) {
		directory = d;
		n = x;
	}
	
	//Getter
	public long getN() {
		return n;
	}
	
	public DirMonitor getDirectory() {
		return directory;
	}
	
	
	//Methode
	
	@Override
	public boolean accept(Path entry) {
		if (directory.sizeOfFiles()<=n) {
			return true;
		}
		else { return false; }
	}
	
	public void printAll() {
		try {
			for (Path path : Files.newDirectoryStream(Paths.get(directory.getChemin().toRealPath().toString()))) {
				if (this.accept(path)) {
					System.out.println(path.getFileName());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
