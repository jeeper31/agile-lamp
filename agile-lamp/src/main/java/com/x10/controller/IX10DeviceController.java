/*
 * (C) 2005 National Australia Bank [All rights reserved]. This product and related documentation
 * are protected by copyright restricting its use, copying, distribution, and decompilation. No part
 * of this product or related documentation may be reproduced in any form by any means without prior
 * written authorization of National Australia Bank. Unless otherwise arranged, third parties may
 * not have access to this product or related documents.
 */
package com.x10.controller;

import com.x10.exception.X10Exception;

/**
 * Controls two X10 devices: one used to indicate that something was successful
 * and the other that something failed.
 * 
 * @author <b>Pak-Tjun Chin</b>
 * @author National Australia Bank Ltd.
 */
public interface IX10DeviceController {
    /**
     * Turns the fail device off and the pass device on.
     * 
     * @throws X10Exception if an exception occurs
     */
    public void pass() throws X10Exception;
    
    /**
     * Turns the pass device off and the fail device on.
     * 
     * @throws X10Exception if an exception occurs
     */
    public void fail() throws X10Exception;
    
    /**
     * Turns a device on.
     * 
     * @param deviceCode X10 device code
     * @throws X10Exception if an exception occurs
     */
    public void switchOn(String deviceCode) throws X10Exception;
    
    /**
     * Turns a device off.
     * 
     * @param deviceCode X10 device code
     * @throws X10Exception if an exception occurs
     */
    public void switchOff(String deviceCode) throws X10Exception;
}
