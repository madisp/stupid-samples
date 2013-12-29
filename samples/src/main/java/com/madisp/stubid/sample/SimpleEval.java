package com.madisp.stubid.sample;

import com.madisp.stupid.ExpressionFactory;
import com.madisp.stupid.Value;
import com.madisp.stupid.context.BaseContext;

public class SimpleEval {
	public static void main(String[] args) {
		ExpressionFactory parser = new ExpressionFactory();
		Value expr = parser.parseExpression("3 + 5");
		System.out.println(expr.value(new BaseContext()));
	}
}
