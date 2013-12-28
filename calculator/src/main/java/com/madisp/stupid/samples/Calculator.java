package com.madisp.stupid.samples;

import com.madisp.stupid.ExpressionFactory;
import com.madisp.stupid.Value;
import com.madisp.stupid.context.ReflectionContext;
import com.madisp.stupid.context.StackContext;
import com.madisp.stupid.context.VarContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * A REPL loop that evaluates arithmetic infix expressions.
 * Additionally, the values for e and π and implementations for
 * sqrt, pow, cos, sin and tan are provided.
 */
public class Calculator {
	private final StackContext ctx;
	private final ExpressionFactory factory;
	private final BufferedReader reader;
	private boolean quit = false;

	public Calculator() {
		ctx = new StackContext();
		factory = new ExpressionFactory();
		// access to API methods
		ctx.pushExecContext(new ReflectionContext(this));
		// create a var scope for PI and E
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("pi", Math.PI);
		vars.put("e", Math.E);
		ctx.pushExecContext(new VarContext(vars));
		reader = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset()));
	}

	// run the loop until we quit
	private void loop() {
		try {
			while (!quit) {
				// read a line from STDIN
				System.out.print("? ");
				System.out.flush();
				String line = reader.readLine();
				// parse the expression
				Value v = factory.parseExpression(line);
				// dereference, e.g., eval the expression and print it
				System.out.println(ctx.dereference(v));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// API methods, accessible for the script
	public String quit() {
		quit = true;
		return "Quitting calculator";
	}

	public double sqrt(double val) {
		return Math.sqrt(val);
	}

	public double pow(double val, double exp) {
		return Math.pow(val, exp);
	}

	public double sin(double val) {
		return Math.sin(val);
	}

	public double cos(double val) {
		return Math.cos(val);
	}

	public double tan(double val) {
		return Math.tan(val);
	}

	// the entry point, just starts the loop
	public static void main(String[] args) {
		(new Calculator()).loop();
	}
}
