package com.madisp.stubid.sample;

import com.madisp.stupid.ExecContext;
import com.madisp.stupid.ExpressionFactory;
import com.madisp.stupid.Value;
import com.madisp.stupid.context.ReflectionContext;
import com.madisp.stupid.context.StackContext;
import com.madisp.stupid.context.VarContext;

import java.util.HashMap;
import java.util.Map;

public class CombinedContext {
	public static void main(String[] args) {
		ExpressionFactory parser = new ExpressionFactory();
		Value expr = parser.parseExpression("println('Area of a circle with r=2: ' + pi*r*r)");
		// a stack context to keep both our vars and binding to System.out
		StackContext ctx = new StackContext();
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("pi", Math.PI);
		vars.put("r", 2.0f);
		ctx.pushExecContext(new VarContext(vars));
		// push a reflection context that binds to system.out onto the stack
		ctx.pushExecContext(new ReflectionContext(System.out));
		// prints "Area of a circle with r=2: 12.566370614359172"
		expr.value(ctx);
	}
}
