/***************************************************************************** #
#                                                                              #
#                                                         :::      ::::::::    #
#    instruction_map.java                               :+:      :+:    :+:    #
#                                                     +:+ +:+         +:+      #
#    By: thmotaun <marvin@42.fr>                    +#+  +:+       +#+         #
#                                                 +#+#+#+#+#+   +#+            #
#    Created: 2017/10/29 12:36:26 by thmotaun          #+#    #+#              #
#    Updated: 2017/10/29 12:36:27 by thmotaun         ###   ########.fr        #
#                                                                              #
# *****************************************************************************/

import java.util.*;

public class instruction_map
{
		public instruction_map()
		{
			//empty constructor
		}

		public final void initMap()
		{
			expert_system.fact.put('A', false);
			expert_system.fact.put('B', false);
			expert_system.fact.put('C', false);
			expert_system.fact.put('D', false);
			expert_system.fact.put('E', false);
			expert_system.fact.put('F', false);
			expert_system.fact.put('G', false);
			expert_system.fact.put('H', false);
			expert_system.fact.put('I', false);
			expert_system.fact.put('G', false);
			expert_system.fact.put('K', false);
			expert_system.fact.put('L', false);
			expert_system.fact.put('M', false);
			expert_system.fact.put('N', false);
			expert_system.fact.put('O', false);
			expert_system.fact.put('P', false);
			expert_system.fact.put('Q', false);
			expert_system.fact.put('R', false);
			expert_system.fact.put('S', false);
			expert_system.fact.put('T', false);
			expert_system.fact.put('U', false);
			expert_system.fact.put('V', false);
			expert_system.fact.put('W', false);
			expert_system.fact.put('X', false);
			expert_system.fact.put('Y', false);
			expert_system.fact.put('Z', false);
		}

		public final void setFact(String truefacts)
		{
			for (int i = 1; i < (int)truefacts.length(); i++)
			{
				expert_system.fact.put(truefacts.charAt(i), true);
			}
		}

		public final void setFact(char c)
		{
			expert_system.fact.put(c, true);
		}

		public final void getQuery(String query)
		{
			if (query.length() > 1)
			{
				for (int i = 1; i < (int)query.length(); i++)
				{
					if (expert_system.fact.get(query.charAt(i)) == true)
					{
						System.out.println((char)27 + "[32m" + query.charAt(i) + " is true!");
					}
					else
					{
						System.out.println((char)27 + "[33m" + query.charAt(i) + " is false!");
					}
				}
			}
			else
			{
				System.out.println((char)27 + "[35m" + "No queries found!");
			}
		}

		public final void findfacts(ArrayList<String> strings)
		{
			int i = 0;

			while (i < (int)strings.size())
			{
				if (strings.get(i).charAt(0) == '=')
				{
					setFact(strings.get(i));
				}
				i++;
			}
		}

		public final void findquery(ArrayList<String> strings)
		{
			int i = 0;

			while (i < (int)strings.size())
			{
				if (strings.get(i).charAt(0) == '?')
				{
					getQuery(strings.get(i));
				}
				i++;
			}
		}
}