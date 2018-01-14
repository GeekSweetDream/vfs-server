package com.server.vfs.vfs;

import com.server.vfs.myclasses.NodeAndEdge;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

public class VfsApplicationTests {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
		System.setErr(null);
	}



	@Test
	public void contextLoads() {
		NodeAndEdge n = new NodeAndEdge("Татьяна Пискунова", "Виталий Пискунов", "Мать");
		List<String> s = n.getGraph();
		System.out.print(s.get(0));
		System.out.print(s.get(1));
		System.out.print(s.get(2));
	}

}
