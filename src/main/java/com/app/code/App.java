package com.app.code;

import com.app.code.exception.TableNotFoundException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	throw new TableNotFoundException("aaa");
    }
}
