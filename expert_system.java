/***************************************************************************** #
#                                                                              #
#                                                         :::      ::::::::    #
#    expert_system.java                                 :+:      :+:    :+:    #
#                                                     +:+ +:+         +:+      #
#    By: thmotaun <marvin@42.fr>                    +#+  +:+       +#+         #
#                                                 +#+#+#+#+#+   +#+            #
#    Created: 2017/10/29 12:35:57 by thmotaun          #+#    #+#              #
#    Updated: 2017/10/29 12:36:00 by thmotaun         ###   ########.fr        #
#                                                                              #
# *****************************************************************************/

import java.util.*;

public class expert_system
{
    public static TreeMap<Character, Boolean> fact = new TreeMap<Character, Boolean>();

	public static void main(String[] args)
	{
		setup a = new setup();
		instruction_map b = new instruction_map();
		rules c = new rules();
		error d = new error();
		ArrayList<String> rulearray = null;
		
		
		try
		{
			if (args.length < 1)
			{
				System.out.println((char)27 + "[31m" + "Error, No arguments! Please try again");
			}
			else
			{
				System.out.println((char)27 + "[35m" + "\nRunning...\n");
				rulearray = new ArrayList<String>(a.start((String)args[0]));
				b.initMap();
		
				if (d.errorCheck(rulearray) == false)
        		{
					b.findfacts(rulearray);
					c.Programloop(rulearray);
					b.findquery(rulearray);
				}
				System.out.println((char)27 + "[35m" + "\nDone...");
			}
		}
		catch (Exception e)
		{
			System.out.println("expert_system does not run biconditional (If-and-only-If) and OR and XOR\nin the conclusion.\n\nProgram closing...\n");
		}
	}
}