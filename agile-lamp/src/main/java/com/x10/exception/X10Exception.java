/*
 * (C) 2005 National Australia Bank [All rights reserved]. This product and related documentation
 * are protected by copyright restricting its use, copying, distribution, and decompilation. No part
 * of this product or related documentation may be reproduced in any form by any means without prior
 * written authorization of National Australia Bank. Unless otherwise arranged, third parties may
 * not have access to this product or related documents.
 */
package com.x10.exception;

/**
 * Thrown when an X10 device has a communication problem.
 * 
 * @author <b>Pak-Tjun Chin</b>
 * @author National Australia Bank Ltd.
 */

public class X10Exception extends Exception {

    /**
	 * Default serial Id.
	 */
	private static final long serialVersionUID = 1L;

	public X10Exception() {
        super();
    }

    public X10Exception(String message) {
        super(message);
    }

    public X10Exception(String message, Throwable cause) {
        super(message + " : " + cause.getMessage());
    }
}
