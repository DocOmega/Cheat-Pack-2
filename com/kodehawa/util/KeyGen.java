/*
* Copyright (c) 2013 David Rubio
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in
* all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
* THE SOFTWARE.
*/

package com.kodehawa.util;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public final class KeyGen {
	
	private static final SecureRandom random = new SecureRandom( );
	
	public static String nextSessionId( )
	{
		return new BigInteger( 130, random ).toString( 32 );
	}
	
	public static String nextSessionId( int size )
	{
		Random rand = new Random( );
		int myRandomNumber = rand.nextInt( 0x10 ) + ( 0x10 * size * rand.nextInt( 20 ) ); // Generates
		
		String result = Integer.toHexString( myRandomNumber ); 
		return result;
	}
	
}
