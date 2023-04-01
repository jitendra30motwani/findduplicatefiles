/**
 * 
 */
package design.api.duplicatefiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author 91978
 *
 *         Find duplicate files in a given root directory
 *
 *         Reqs:
 *
 *         We need to group files based on content Common file system api
 *         available
 *
 *         listDir(path) isFile(path) isDir(path) readFile(path)
 *         hashFunctionFile(path)
 *
 *
 */
public class FindDuplicateFiles {

	/**
	 * 
	 */
	public FindDuplicateFiles() {
		// TODO Auto-generated constructor stub
	}

	public Map<Integer, List<String>> groupDuplicateFiles(String rootPath) {

		List<String> files = walkFiles(rootPath);

		Map<Integer, List<String>> fileMap = new HashMap<>();

		for (String file : files) {

			Integer hash = hashFunctionFile(file);

			List<String> fileList = null;

			if (fileMap.containsKey(hash)) {
				fileList = fileMap.get(hash);
			} else {
				fileList = new ArrayList<>();
			}

			fileList.add(file);

			fileMap.put(hash, fileList);

		}

		return fileMap;

	}
	
	public Map<Integer, List<String>> groupDuplicateFiles(List<String> paths) {

		Map<Integer, List<String>> fileMap = new HashMap<>();

		for (String file : paths) {

			Integer hash = hashFunctionFile(file);

			List<String> fileList = null;

			if (fileMap.containsKey(hash)) {
				fileList = fileMap.get(hash);
			} else {
				fileList = new ArrayList<>();
			}

			fileList.add(file);

			fileMap.put(hash, fileList);

		}

		return fileMap;

	}

	public List<String> walkFiles(String rootPath) {

		List<String> files = new ArrayList<>();

		Queue<String> bfs = new LinkedList<>();

		bfs.add(rootPath);

		while (!bfs.isEmpty()) {
			String curPath = bfs.poll();

			List<String> paths = listDir(curPath);

			for (String path : paths) {
				if (isFile(path)) {
					files.add(path);
				} else if (isDir(curPath)) {
					bfs.add(path);
				}
			}

		}

		return files;
	}

	public List<String> listDir(String path) {
		
		List<String> dirs = new ArrayList<>();
		
		dirs.add("/home/A");
		dirs.add("/home/A/file1.txt");
		dirs.add("/home/A/B");
		dirs.add("/home/A/B/file2.txt");
		
		return dirs;
	}

	public Boolean isFile(String path) {
		
		Set<String> files = new HashSet<>();
		
		files.add("/home/A/file1.txt");
		files.add("/home/A/B/file2.txt");
		
		return files.contains(path);
	}

	public Boolean isDir(String path) {
		Set<String> dirs = new HashSet<>();
		
		dirs.add("/home/A");
		dirs.add("/home/A/B");
		
		return dirs.contains(path);
	}

	public Integer hashFunctionFile(String path) {
		//return 1;
		return path.hashCode();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		FindDuplicateFiles duplicateFiles = new FindDuplicateFiles();
		
		//System.out.println(duplicateFiles.groupDuplicateFiles("/home"));
		
		System.out.println(duplicateFiles.groupDuplicateFiles(duplicateFiles.walkFiles("/home")));
		
	}

}
