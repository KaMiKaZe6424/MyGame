package mygame.log;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	
	private static Level curr = Level.DEBUG;
	
	private static void log(Level l, String msg) {
		Date date = new Date();
		SimpleDateFormat tf = new SimpleDateFormat("[HH:mm:ss]");
		System.out.println(tf.format(date) + "[" + l.name() + "] " + msg);
	}
	
	public static void fatal(String msg) {
		if (curr.ordinal() >= 0) log(Level.FATAL, msg);
	}
	
	public static void error(String msg) {
		if (curr.ordinal() >= 1) log(Level.ERROR, msg);
	}
	
	public static void warning(String msg) {
		if (curr.ordinal() >= 2) log(Level.WARNING, msg);
	}
	
	public static void info(String msg) {
		if (curr.ordinal() >= 3) log(Level.INFO, msg);
	}
	
	public static void debug(String msg) {
		if (curr.ordinal() >= 4) log(Level.DEBUG, msg);
	}
	
	public static void verbose(String msg) {
		if (curr.ordinal() >= 5) log(Level.VERBOSE, msg);
	}
	
	public Level getCurrentLevel() {
		return curr;
	}
	
	public void setCurrentLevel(Level l) {
		curr = l;
	}
	
}
