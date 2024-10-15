package tech.renovus.solarec.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class FileUtilTest {

	@Test public void test() {
		String text = "asdadad";
		String text2 = "132131231";

		assertEquals("", FileUtil.getExtensionNoDot("adadas"));
		assertEquals("txt", FileUtil.getExtensionNoDot("adadas.txt"));
		assertEquals("txt2", FileUtil.getExtensionNoDot("adadas.txt.txt2"));
		assertEquals("", FileUtil.getExtensionNoDot("adadas"));
		assertEquals(".txt", FileUtil.getExtension("adadas.txt"));
		assertEquals(".txt2", FileUtil.getExtension("adadas.txt.txt2"));

		
		assertEquals("", FileUtil.getFileName(""));
		assertEquals("adadas", FileUtil.getFileName("adadas"));
		assertEquals("adadas", FileUtil.getFileName("adadas.txt"));
		assertEquals("adadas.txt", FileUtil.getFileName("adadas.txt.txt2"));

		try {
			File testFile = File.createTempFile("test", ".txt");
			testFile.delete();
			
			assertFalse(testFile.exists());
			assertFalse(FileUtil.exists(testFile));
			assertFalse(FileUtil.exists(testFile.getAbsolutePath()));
			assertTrue(FileUtil.notExistsOrCantRead(testFile));
			FileUtil.saveToFile(text, testFile.getAbsolutePath());
			assertTrue(testFile.exists());
			assertTrue(FileUtil.exists(testFile));
			
			assertFalse(FileUtil.existsAndCanRead(null));
			assertTrue(FileUtil.existsAndCanRead(testFile));
			
			String read = FileUtil.readFile(testFile.getAbsolutePath());
			assertEquals(text, read);

			byte[] bytes = FileUtil.readFileAsByte(testFile.getAbsolutePath());
			assertEqualsBytes(text.getBytes(), bytes);

			FileUtil.appendToFile(text2, testFile.getAbsolutePath());
			read = FileUtil.readFile(testFile.getAbsolutePath());
			assertEquals(text + text2, read);
			
			File destFile = new File(testFile.getAbsolutePath() + ".copy");
			assertFalse(destFile.exists());
			FileUtil.copy(testFile, destFile);
			assertTrue(destFile.exists());
			
			FileUtil.replace(testFile, destFile);
			assertTrue(destFile.exists());
			
			assertTrue(FileUtil.deleteFile(testFile.getPath()));
			assertFalse(testFile.exists());
			
		} catch (IOException e) {
			assertTrue(e == null);
		}
		
		assertEquals(FileUtil.DEFAULT_CONTENT, FileUtil.getContentType(null));
		assertEquals("text/plain", FileUtil.getContentType("adasd.txt"));
		assertTrue(FileUtil.isImage("sdasdsad.png"));
		assertFalse(FileUtil.isImage("sdasdsad.exe"));
		assertTrue(FileUtil.isText("sdasdsad.txt"));
		assertTrue(FileUtil.isText("sdasdsad.txt"));
		assertTrue(FileUtil.isFlash("sdasdsad.swf"));
		assertFalse(FileUtil.isFlash("sdasdsad.exe"));
		assertTrue(FileUtil.isFlashVideo("sdasdsad.flv"));
		assertFalse(FileUtil.isFlashVideo("sdasdsad.exe"));
		assertTrue(FileUtil.isOffice("sdasdsad.doc"));
		assertFalse(FileUtil.isOffice("sdasdsad.exe"));
		assertTrue(FileUtil.isExcel("sdasdsad.xls"));
		assertFalse(FileUtil.isExcel("sdasdsad.exe"));
		assertTrue(FileUtil.isICS("sdasdsad.ics"));
		assertFalse(FileUtil.isICS("sdasdsad.exe"));
	}

	private void assertEqualsBytes(byte[] bytes, byte[] bytes2) {
		assertEquals(bytes == null, bytes2 == null);
		if (bytes == null) {
			return;
		}
		
		assertEquals(bytes.length, bytes2.length);
		
		for (int i = 0; i < bytes.length; i++) {
			assertEquals(bytes[i], bytes2[i]);
		}
		
	}
}
