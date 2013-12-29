package com.madisp.stubid.sample;

import com.madisp.stupid.ExecContext;
import com.madisp.stupid.ExpressionFactory;
import com.madisp.stupid.Value;
import com.madisp.stupid.context.VarContext;

import java.util.HashMap;
import java.util.Map;

public class EvalWithVars {
	public static void main(String[] args) {
		ExpressionFactory factory = new ExpressionFactory();
		Value expr = factory.parseExpression("pi*r*r");
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("pi", Math.PI);
		vars.put("r", 2.0f);
		System.out.println("Area of a circle with r=2: " + expr.value(new VarContext(vars)));
	}
}
