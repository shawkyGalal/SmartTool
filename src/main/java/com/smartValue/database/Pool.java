package com.smartValue.database;

import java.util.EmptyStackException;
import java.util.Stack;

public class Pool {

	private Stack stack = null;
	
	public Stack getStack() {
		return stack;
	}

	public void setStack(Stack stack) {
		this.stack = stack;
	}

	public Pool(int pm_size)
	{
		this.stack = new Stack();
		this.stack.setSize(pm_size);
	}
	
	public void push(Object o )
	{
		stack.push(o);
		//Console.log("============Return Connection ========" , this.getClass());
	}

	public synchronized Object pop( )
	{
		Object object = null;
		
		try{
		object = stack.pop();
		//Console.log("============Get Connection ========" , this.getClass());
		}
		catch ( EmptyStackException ese )
		{
			ese.printStackTrace();
			//Logger.log(new EventDbug9999("Empty Stack" , ese));
			throw ese;
		}
		return object;
	}

}
