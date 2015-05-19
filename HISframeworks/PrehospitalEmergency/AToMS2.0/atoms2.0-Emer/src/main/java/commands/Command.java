/*******************************************************************************
 * Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 * Read file LICENSE.txt for conditions on the use of this software.
 *******************************************************************************/

package commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servlets.Controller;

/**
 *
 * @author iuri
 */
public interface Command {

    public void execute(HttpServletRequest request, HttpServletResponse response, Controller controller);
}
