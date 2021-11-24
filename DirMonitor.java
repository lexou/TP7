package fr.dauphine.javavance.td7;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirMonitor {
	private static Path chemin;

	//Constructeur
	public DirMonitor(Path p) {
		if (!(Files.exists(p)) || !(Files.isReadable(p))) { 
			throw new InvalidPathException("Problem with the given path", null); 
		}
		else { 
			chemin = p;
		}
	}
	
	//Getter
	public Path getChemin() {
		return chemin;
	}
	
	//Methode
	public long sizeOfFiles() {
		long somme = 0;
		try {
			for (Path path : Files.newDirectoryStream(Paths.get(chemin.toRealPath().toString()))) {
				if (!Files.isDirectory(path)) {
					somme += Files.size(path);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return somme;
	}
	
	public Path mostRecent() {
		  File[] files = chemin.toFile().listFiles();
		  if (files == null || files.length == 0) {
		      return null;
		  }
		  File curr = files[0];
		   for (int i = 1; i < files.length; i++) {
		      if (curr.lastModified() < files[i].lastModified()) {
		         curr = files[i];
		      }
		   }
		 return curr.toPath();
	}
	
	public void applyAction(String prefix, MyAction action) throws IOException {
	}
	
	
	//INNER CLASS
	public class PrefixFilter implements DirectoryStream.Filter<Path>{
		 long n;
		
		//Constructeur
		public PrefixFilter(Path d, long x) {
			DirMonitor.chemin = d;
			n = x;
		}
		
		//Getter
		public long getN() {
			return n;
		}
		
		
		//Methode
		@Override
		public boolean accept(Path entry) {
			if (DirMonitor.this.sizeOfFiles()<=n) {
				return true;
			}
			else { return false; }
		}
		
		public void printAll() {
			try {
				for (Path path : Files.newDirectoryStream(Paths.get(DirMonitor.this.getChemin().toRealPath().toString()))) {
					if (this.accept(path)) {
						System.out.println(path.getFileName());
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	} //Fin INNER class
	
	
} //Fin class DirMonitor


