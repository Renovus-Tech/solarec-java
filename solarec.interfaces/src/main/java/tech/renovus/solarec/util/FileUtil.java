package tech.renovus.solarec.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.SyncFailedException;
import java.util.Collection;
import java.util.HashMap;

/**
 * The <code>FileUtil</code> class contains useful methods to work with <code>File</code> and retrieve/set 
 * information from it.
 */
public final class FileUtil {
	
	public static final String SEPARATOR_WEB	= "/";
	
	//--- Public constants ----------------------
	/** Default content file <code>plain/text</code>. */
	public static final String DEFAULT_CONTENT			= "plain/text";
	public static final String CONTENT_FORCE_DOWNLOAD	= "application/force-download";
	
	public static final double FILE_SIZE_1KB_IN_BYTES	= 1024;
	public static final double FILE_SIZE_1MB_IN_BYTES	= (double) 1024 * 1024;
	public static final double FILE_SIZE_1GB_IN_BYTES	= (double) 1024 * 1024 * 1024;
	public static final double FILE_SIZE_1TB_IN_BYTES	= (double) 1024 * 1024 * 1024 * 1024;
	
	/** A list of extensions and content that are know. */
	private static HashMap<String,String> FILE_EXTENSION_CONTENT_TYPE 			= new HashMap<String,String>();
	
	/** A list of extensions and content that are know to be image. */
	private static HashMap<String,String> IMAGE_EXTENSION_CONTENT_TYPE			= new HashMap<String,String>();
	
	/** A list of extensions and content that are know to be flash. */
	private static HashMap<String,String> FLASH_EXTENSION_CONTENT_TYPE			= new HashMap<String,String>();
	
	/** A list of extensions and content that are know to be flash video. */
	private static HashMap<String,String> FLASH_VIDEO_EXTENSION_CONTENT_TYPE	= new HashMap<String,String>();
	
	/** A list of extensions and content that are know to be text. */
	private static HashMap<String,String> TEXT_EXTENSION_CONTENT_TYPE			= new HashMap<String,String>();
	
	/** A list of extensions and content that are know to be office. */
	private static HashMap<String,String> OFFICE_EXTENSION_CONTENT_TYPE			= new HashMap<String,String>();
	
	/** A list of extensions and content that are know to be valid excel. */
	private static HashMap<String,String> EXCEL_EXTENSION_CONTENT_TYPE			= new HashMap<String,String>();
	
	/** A list of extensions and content that are know to be valid ics. */
	private static HashMap<String,String> ICS_EXTENSION_CONTENT_TYPE			= new HashMap<String,String>();
	
	static {
		FileUtil.IMAGE_EXTENSION_CONTENT_TYPE.put(".bmp","image/bmp");
		FileUtil.IMAGE_EXTENSION_CONTENT_TYPE.put(".gif","image/gif");
		FileUtil.IMAGE_EXTENSION_CONTENT_TYPE.put(".jpe","image/jpeg");
		FileUtil.IMAGE_EXTENSION_CONTENT_TYPE.put(".jpeg","image/jpeg");
		FileUtil.IMAGE_EXTENSION_CONTENT_TYPE.put(".jpg","image/jpeg");
		FileUtil.IMAGE_EXTENSION_CONTENT_TYPE.put(".png","image/png");
		FileUtil.IMAGE_EXTENSION_CONTENT_TYPE.put(".tif","image/tiff");
		FileUtil.IMAGE_EXTENSION_CONTENT_TYPE.put(".tiff","image/tiff");
		FileUtil.IMAGE_EXTENSION_CONTENT_TYPE.put(".svg","image/svg+xml");		
		
		FileUtil.FLASH_EXTENSION_CONTENT_TYPE.put(".swf","application/x-shockwave-flash");		
		
		FileUtil.FLASH_VIDEO_EXTENSION_CONTENT_TYPE.put(".flv","video/x-flv");		
		
		FileUtil.TEXT_EXTENSION_CONTENT_TYPE.put(".htm","text/html");
		FileUtil.TEXT_EXTENSION_CONTENT_TYPE.put(".html","text/html");
		FileUtil.TEXT_EXTENSION_CONTENT_TYPE.put(".txt","text/plain");
		FileUtil.TEXT_EXTENSION_CONTENT_TYPE.put(".log","text/plain");
		FileUtil.TEXT_EXTENSION_CONTENT_TYPE.put(".xml","text/xml");
		FileUtil.TEXT_EXTENSION_CONTENT_TYPE.put(".xsl","text/xml");

		FileUtil.OFFICE_EXTENSION_CONTENT_TYPE.put(".doc","application/msword");
		FileUtil.OFFICE_EXTENSION_CONTENT_TYPE.put(".ppt","application/powerpoint");
		FileUtil.OFFICE_EXTENSION_CONTENT_TYPE.put(".pdf","application/pdf");

		FileUtil.FILE_EXTENSION_CONTENT_TYPE.putAll(FileUtil.IMAGE_EXTENSION_CONTENT_TYPE);
		FileUtil.FILE_EXTENSION_CONTENT_TYPE.putAll(FileUtil.FLASH_EXTENSION_CONTENT_TYPE);
		FileUtil.FILE_EXTENSION_CONTENT_TYPE.putAll(FileUtil.FLASH_VIDEO_EXTENSION_CONTENT_TYPE);
		FileUtil.FILE_EXTENSION_CONTENT_TYPE.putAll(FileUtil.TEXT_EXTENSION_CONTENT_TYPE);
		FileUtil.FILE_EXTENSION_CONTENT_TYPE.putAll(FileUtil.OFFICE_EXTENSION_CONTENT_TYPE);
		
		FileUtil.EXCEL_EXTENSION_CONTENT_TYPE.put(".xls","excel/xls");
		
		FileUtil.ICS_EXTENSION_CONTENT_TYPE.put(".ics","text/plain");
	}
	
	//--- Constructor ---------------------------
	private FileUtil() {
	}
	
	//--- Helper methods ------------------------
	/**
	 * Saves the content of <code>String</code> to the file located at the path <b>pathFile</b>.
	 * 
	 * @param str			The text to save
	 * @param pathFile		The destination file location
	 * @throws IOException	In case of error while saving the information
	 * 
	 * @see #saveToFile(String, File)
	 */
	public static void saveToFile(String str, String pathFile) throws IOException {
		FileUtil.saveToFile(str,new File(pathFile));
	}
	
	/**
	 * Saves the content of <code>String</code> to the <code>File</code>.
	 * 
	 * @param str			The text to save
	 * @param pathFile		The destination file
	 * @throws IOException	In case of error while saving the information
	 * 
	 * @see #saveToFile(String, String)
	 */
	public static void saveToFile(String str, File file) throws IOException {
		if (! file.exists()) {
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		
		FileWriter o = null;
		try {
			o = new FileWriter(file, false);
			o.write(str);
			o.flush();
		} finally {
			if (o != null) {
				o.close();
			}
		}
	}
	
	/**
	 * Reads the file located at <b>pathFile</b> and returns its. If the <b>pathFile</b> does not exist or
	 * is not a file then <code>StringUtil.EMPTY_STRING</code> is return.
	 * 
	 * @param pathFile		The file location to read
	 * @return				The file content
	 * @throws IOException	If an error occurs while reading
	 * 
	 * @see uy.com.pf.sdk.util.StringUtil#EMPTY_STRING
	 * @see #readFile(File)
	 */
	public static String readFile(String pathFile) throws IOException {
		return FileUtil.readFile(new File(pathFile));
	}
	
	/**
	 * Reads the <code>File</code> and returns its. If the <code>File</code> does not exist or
	 * is not a file then <code>StringUtil.EMPTY_STRING</code> is return.
	 * 
	 * @param pathFile		The file location to read
	 * @return				The file content
	 * @throws IOException	If an error occurs while reading
	 * 
	 * @see uy.com.pf.sdk.util.StringUtil#EMPTY_STRING
	 * @see #readFile(String)
	 */
	public static String readFile(File aFile) throws IOException {
		if (! aFile.exists()) {
			return StringUtil.EMPTY_STRING;
		}
		if (! aFile.isFile()) {
			return StringUtil.EMPTY_STRING;
		}

		FileReader r = null;
		try {
			StringBuffer strBuf = new StringBuffer();
			r = new FileReader(aFile);
			int c;
			while ((c = r.read()) != -1) {
				strBuf.append((char) c);
			}
			return strBuf.toString();
		} finally {
			if (r != null) {
				r.close();
			}
		}
	}
	
	/**
	 * Reads the file located at <b>pathFile</b> and returns its. If the <b>pathFile</b> does not exist or
	 * is not a file then <code>StringUtil.EMPTY_STRING</code> is return.
	 * 
	 * @param pathFile		The file location to read
	 * @return				The file content
	 * @throws IOException	If an error occurs while reading
	 * 
	 * @see uy.com.pf.sdk.util.StringUtil#EMPTY_STRING
	 * @see #readFile(File)
	 */
	public static byte[] readFileAsByte(String pathFile) throws IOException {
		return FileUtil.readFileAsByte(new File(pathFile));
	}
	
	/**
	 * Reads the <code>File</code> and returns its. If the <code>File</code> does not exist or
	 * is not a file then <code>StringUtil.EMPTY_STRING</code> is return.
	 * 
	 * @param pathFile		The file location to read
	 * @return				The file content
	 * @throws IOException	If an error occurs while reading
	 * 
	 * @see uy.com.pf.sdk.util.StringUtil#EMPTY_STRING
	 * @see #readFile(String)
	 */
	public static byte[] readFileAsByte(File aFile) throws IOException {
		if (! aFile.exists()) {
			return null;
		}
		if (! aFile.isFile()) {
			return null;
		}

		try (
			FileInputStream fis = new FileInputStream(aFile);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
		) {
	        byte[] buf = new byte[1024];
	        for (int readNum; (readNum = fis.read(buf)) != -1;) {
	            bos.write(buf, 0, readNum);
	        }
	        return bos.toByteArray();
		}
	}
	
	/**
	 * Appends the <b>str</b> to the content of the file located at <b>pathFile</b>. If <b>pathFile</b>
	 * does not exists or is not a file, then the content is not added.
	 * 
	 * @param str			The content to append
	 * @param pathFile		The file location where to append the content
	 * @throws IOException	If an error occurs while writing
	 * 
	 * @see #appendToFile(String, File)
	 */
	public static void appendToFile(String str, String pathFile) throws IOException {
		FileUtil.appendToFile(str,new File(pathFile));
	}
	
	/**
	 * Appends the <b>str</b> to the content of the file located at <b>aFile</b>. If <b>aFile</b>
	 * does not exists or is not a file, then the content is not added.
	 * 
	 * @param str			The content to append
	 * @param pathFile		The file location where to append the content
	 * @throws IOException	If an error occurs while writing
	 * 
	 * @see #appendToFile(String, String)
	 */
	public static void appendToFile(String str, File aFile) throws IOException {
		if (! aFile.exists()) {
			aFile.getParentFile().mkdirs();
			aFile.createNewFile();
		}
		
		if (! aFile.exists()) {
			return;
		}
		if (! aFile.isFile()) {
			return;
		}
		
		FileWriter o = null;
		try {
			o = new FileWriter(aFile, true);
			o.write(str);
			o.flush();
		} finally {
			if (o != null) {
				o.close();
			}
		}
	}

	/**
	 * Returns the extension of a <b>file</b> with out the dot. If there is no dot in the <b>file</b>
	 * then <code>StringUtil.EMPTY_STRING</code> is return.
	 * 
	 * @param file	The file name
	 * @return		The file extension
	 * 
	 * @see uy.com.pf.sdk.util.StringUtil#EMPTY_STRING
	 * @see #getExtension(String)
	 */
	public static String getExtensionNoDot(String file) {
		if (file.lastIndexOf(".") == -1) {
			return StringUtil.EMPTY_STRING;
		}
		return file.substring(file.lastIndexOf(".") + 1).toLowerCase();
	}
	
	/**
	 * Returns the extension of a <b>file</b> with the dot. If there is no dot in the <b>file</b>
	 * then <code>StringUtil.EMPTY_STRING</code> is return.
	 * 
	 * @param file	The file name
	 * @return		The file extension
	 * 
	 * @see uy.com.pf.sdk.util.StringUtil#EMPTY_STRING
	 * @see #getExtensionNoDot(String)
	 */
	public static String getExtension(String file) {
		if (file == null || file.lastIndexOf(".") == -1) {
			return StringUtil.EMPTY_STRING;
		}
		return file.substring(file.lastIndexOf(".")).toLowerCase();
	}
	
	/**
	 * Returns the name of the <b>file</b> without the extension neither the dot. If the <b>file</b>
	 * has not dot, then <b>file</b> is return. If the <b>file</b> includes the parentes, they will be 
	 * return in the name.
	 * 
	 * @param file	The file name with extension
	 * @return		The file name without extension
	 */
	public static String getFileName(String file) {
		if (file.lastIndexOf(".") == -1) {
			return file;
		}
		return file.substring(0, file.lastIndexOf("."));
	}
	
	/**
	 * Returns the <b>file</b> content according to the file extension. If the extension is not 
	 * recognized, then <code>FileUtil.DEFAULT_CONTENT</code> is return.
	 * 
	 * @param file	The file to determinate its content
	 * @return		The content type of the file
	 * 
	 * @see #DEFAULT_CONTENT
	 */
	public static String getContentType(String file) {
		String extension = FileUtil.getExtension(file);
		if (FileUtil.FILE_EXTENSION_CONTENT_TYPE.containsKey(extension)) {
			return FileUtil.FILE_EXTENSION_CONTENT_TYPE.get(extension);
		}
		
		return FileUtil.DEFAULT_CONTENT;
	}

	/**
	 * Copies the content of <b>sourceFile</b> to <b>destFile</b>. If the destination path does not exist then
	 * is created. If the destination file is does not exist then is created.
	 * 
	 * @param sourceFile	The content to copy from
	 * @param destFile		The location to copy to
	 * @throws IOException	If an error occurs while coping
	 * 
	 * @see #move(File, File)
	 * @see #replace(File, File)
	 */
	public static void copy(File sourceFile, File destFile) throws IOException {
		if (! destFile.exists()) {
			if (! destFile.createNewFile()) {
				throw new IOException("Can't create file " + destFile.getAbsolutePath());
			}
		}
		
		FileUtil.createPath(destFile.getParentFile());
		try (
			FileInputStream in = new FileInputStream(sourceFile);
			FileOutputStream out = new FileOutputStream(destFile);
		) {
			try {
				in.getFD().sync();
			} catch (SyncFailedException sfe) {
				/* do nothing */
			}

			byte[] buffer = new byte[8 * 1024];
			int count = 0;
			do {
				out.write(buffer, 0, count);
				count = in.read(buffer, 0, buffer.length);
			} while (count != -1);
	
			try {
				out.getFD().sync();
			} catch (SyncFailedException sfe) {
				/* do nothing */
			}
		}

	}
	
	/**
	 * Replaces the content of <b>destFile</b> with the content <b>sourceFile</b>. Before doing the copy, the file <b>destFile</b> is deleted.
	 * 
	 * @param sourceFile	The content to copy from
	 * @param destFile		The content to replace
	 * @throws IOException	If an error occurs while coping or deleting
	 * 
	 * @see #copy(File, File)
	 * @see #move(File, File)
	 */
	public static void replace(File sourceFile, File destFile) throws IOException {
		if (destFile.exists() && !destFile.delete()) {
			throw new IOException("Can't delete file " + destFile.getAbsolutePath());
		}
		FileUtil.copy(sourceFile, destFile);
	}
	
	/**
	 * Determinate if the <b>file</b> is a file with content type image according to the file extension.
	 * 
	 * @param file	The file to determinate the content.
	 * @return		<code>true</code> if the extension is recognized as an image file extension.
	 */
	public static boolean isImage(String file) {
		return FileUtil.IMAGE_EXTENSION_CONTENT_TYPE.containsKey(FileUtil.getExtension(file));
	}

	/**
	 * Determinate if the <b>file</b> is a file with content type text according to the file extension.
	 * 
	 * @param file	The file to determinate the content.
	 * @return		<code>true</code> if the extension is recognized as a text file extension.
	 */
	public static boolean isText(String file) {
		return FileUtil.TEXT_EXTENSION_CONTENT_TYPE.containsKey(FileUtil.getExtension(file));
	}

	/**
	 * Determinate if the <b>file</b> is a file with content type flash according to the file extension.
	 * 
	 * @param file	The file to determinate the content.
	 * @return		<code>true</code> if the extension is recognized as a flash file extension.
	 */
	public static boolean isFlash(String file) {
		return FileUtil.FLASH_EXTENSION_CONTENT_TYPE.containsKey(FileUtil.getExtension(file));
	}

	/**
	 * Determinate if the <b>file</b> is a file with content type flash video according to the file extension.
	 * 
	 * @param file	The file to determinate the content.
	 * @return		<code>true</code> if the extension is recognized as a flash video file extension.
	 */
	public static boolean isFlashVideo(String file) {
		return FileUtil.FLASH_VIDEO_EXTENSION_CONTENT_TYPE.containsKey(FileUtil.getExtension(file));
	}
	
	/**
	 * Determinate if the <b>file</b> is a file with content type office according to the file extension.
	 * 
	 * @param file	The file to determinate the content.
	 * @return		<code>true</code> if the extension is recognized as an office file extension.
	 */
	public static boolean isOffice(String file) {
		return FileUtil.OFFICE_EXTENSION_CONTENT_TYPE.containsKey(FileUtil.getExtension(file));
	}
	
	/**
	 * Determinate if the <b>file</b> is a file with content type valid excel according to the file extension.
	 * 
	 * @param file	The file to determinate the content.
	 * @return		<code>true</code> if the extension is recognized as a valid excel file extension.
	 */
	public static boolean isExcel(String file) {
		return FileUtil.EXCEL_EXTENSION_CONTENT_TYPE.containsKey(FileUtil.getExtension(file));
	}
	
	/**
	 * Determinate if the <b>file</b> is a file with content type valid ics according to the file extension.
	 * 
	 * @param file	The file to determinate the content.
	 * @return		<code>true</code> if the extension is recognized as a valid excel file extension.
	 */
	public static boolean isICS(String file) {
		return FileUtil.ICS_EXTENSION_CONTENT_TYPE.containsKey(FileUtil.getExtension(file));
	}

	/**
	 * Deletes all the files in the <code>Collection</code> that are in the <b>location</b>.
	 * 
	 * @param location	Path where the files are
	 * @param files		The files to be deleted
	 * @return			<code>true</code> if all files where deleted, <code>false</code> otherwise
	 * 
	 * @see #deleteFile(String, String)
	 * @see #deleteFile(String)
	 */
	public static boolean deleteFiles(String location, Collection<String> files) {
		boolean allDeleted = true;
		if (CollectionUtil.notEmpty(files)) {
			for (String file : files) {
				allDeleted = FileUtil.deleteFile(location, file) && allDeleted;
			}
		}
		
		return allDeleted;
	}
	
	/**
	 * Deletes the file located at <b>path</b>.
	 * 
	 * @param path	The file path to delete
	 * @return		<code>true</code> if the file was deleted, <code>false</code> otherwise
	 */
	public static boolean deleteFile(String path) {
		return new File(path).delete();
	}
	
	/**
	 * Deletes the file located at <b>path</b> with the name <b>name</b>.
	 * 
	 * @param path	The file path to delete
	 * @param name	The file name to delete
	 * @return		<code>true</code> if the file was deleted, <code>false</code> otherwise
	 */
	public static boolean deleteFile(String path, String name) {
		return new File(path,name).delete();
	}

	/**
	 * Creates a file path if does not exists.
	 * 
	 * @param path			The path to create.
	 * @throws IOException	If the path coundn't be created.
	 */
	public static void createPath(File path) throws IOException {
		if (! path.exists()) {
			if (! path.mkdirs()) {
				throw new IOException("Can't create path: " + path.getAbsolutePath());
			}
		}
	}
	
	/**
	 * Creates a file path if does not exists.
	 * 
	 * @param path			The path to create.
	 * @throws IOException	If the path coundn't be created.
	 */
	public static void createPath(String path) throws IOException {
		createPath(new File(path));
	}

	/**
	 * Checks if the file exits.
	 * 
	 * @param filePath Path of the file to check
	 * @return <code>true</code> if the file exists in the path, <code>false</code> otherwise.
	 */
	public static boolean exists(String filePath) {
		if (StringUtil.isEmpty(filePath)) {
			return false;
		}
		return FileUtil.exists(new File(filePath));
	}
	
	/**
	 * Checks if the file exits.
	 * 
	 * @param file The <code>File</code> to check
	 * @return <code>true</code> if the file exists, <code>false</code> otherwise.
	 */
	public static boolean exists(File file) {
		if (file == null) {
			return false;
		}
		return file.exists();
	}
	
	/**
	 * Checks if the file exits and can be read.
	 * 
	 * @param file The <code>File</code> to check
	 * @return <code>true</code> if the file exists and can be read, <code>false</code> otherwise.
	 */
	public static boolean existsAndCanRead(File file) {
		if (file == null) {
			return false;
		}
		return file.exists() && file.canRead();
	}
	
	/**
	 * Checks if the file doesn't exits or can't read.
	 * 
	 * @param file The <code>File</code> to check
	 * @return <code>true</code> if the file doesn't exists or can't be read, <code>false</code> otherwise.
	 */
	public static boolean notExistsOrCantRead(File file) {
		if (file == null) {
			return false;
		}
		return ! file.exists() || ! file.canRead();
	}
}
