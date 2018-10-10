/***************************************************************************** #
#                                                                              #
#                                                         :::      ::::::::    #
#    rules.java                                         :+:      :+:    :+:    #
#                                                     +:+ +:+         +:+      #
#    By: thmotaun <marvin@42.fr>                    +#+  +:+       +#+         #
#                                                 +#+#+#+#+#+   +#+            #
#    Created: 2017/10/29 12:36:37 by thmotaun          #+#    #+#              #
#    Updated: 2017/10/29 12:36:39 by thmotaun         ###   ########.fr        #
#                                                                              #
# *****************************************************************************/

import java.util.*;

public class rules extends setup
{
		public rules()
		{
			//empty constructor
		}

		private ArrayList<String>		splitstr(String str, char c)
		{
			int				i = 0;
			boolean				open = false;
			boolean 				close = false;
			ArrayList<String> strarray = new ArrayList<String>();
			String				strdup = "";
			
			
				while (i < (int)str.length())
			{
				strdup = "";
				while (i < (int)str.length())
				{
					if (str.charAt(i) == '<' || str.charAt(i) == '=') {
						close = true;
					}
					if (str.charAt(i) == '(')
					{
						open = true;
						if (c == '+')
						{
							i++;
						}
					}
					else if (str.charAt(i) == ')')
					{
						open = false;
						if (c == '+')
						{
							i++;
						}
					}
					if (str.charAt(i) != c && open == false && close == false)
					{
						strdup += str.charAt(i);
					}
					else if (open == true && close == false)
					{
						strdup += str.charAt(i);
					}
					else
					{
						break ;
					}
					i++;
				}
				if (!strdup.isEmpty()) 
				{
					strarray.add(strdup);
				}
				i++;
			}
			return strarray;
		}

		private boolean evalANDBlock(String line)
		{
			ArrayList<String> strblock = new ArrayList<String>();
			TreeMap<Integer, Boolean> block = new TreeMap<Integer, Boolean>();
			String str;
			
			strblock = splitstr(line, '+');
			for (int i = 0; i < (int)strblock.size(); i++)
			{
				str = strblock.get(i);
				if (str.length() == 1 && expert_system.fact.get(str.charAt(0)) == true)
				{
					block.put(i, true);
				}
				else if (str.length() == 1 && expert_system.fact.get(str.charAt(0)) == false)
				{
					block.put(i, false);
				}
				else if (str.length() == 2 && str.charAt(0) == '!')
				{
					if (expert_system.fact.get(str.charAt(1)) == false)
					{
						block.put(i, true);
					}
					else
					{
						block.put(i, false);
					}
				}
				else if (str.length() > 2 && str.charAt(0) != '?')
				{
					if (evalXORblock(str))
					{
						block.put(i, true);
					}
					else
					{
						block.put(i, false);
					}
				}
			}
			if (solveANDblocks(block) == true)
			{
				return true;
			}
			return false;
		}

		private boolean evalORblock(String line)
		{
			TreeMap<Integer, Boolean> block = new TreeMap<Integer, Boolean>();
			ArrayList<String> strblock = new ArrayList<String>();
			
			strblock = splitstr(line, '|');
			for (int i = 0; i < (int)strblock.size(); i++)
			{
				block.put(i, evalANDBlock(strblock.get(i)));
			}
			if (solveORblocks(block) == true)
			{
				return true;
			}
			return false;
		}

		private boolean evalXORblock(String line)
		{
			TreeMap<Integer, Boolean> block = new TreeMap<Integer, Boolean>();
			ArrayList<String> strblock = new ArrayList<String>();
		
			strblock = splitstr(line, '^');
			for (int i = 0; i < (int)strblock.size(); i++)
			{
				block.put(i, evalORblock(strblock.get(i)));
			}
			if (solveXORblocks(block) == true)
			{
				return true;
			}
			return false;
		}

		private boolean solveXORblocks(TreeMap<Integer, Boolean> block)
		{
			if (block.size() > 2)
			{
				return false;
			}
			else if (block.size() == 2)
			{
				if ((block.get(0) == true && block.get(1) == false) || (block.get(0) == false && block.get(1) == true))
				{
					return true;
				}
			}
			else if (block.size() < 2 && block.get(0) == true)
			{
				return true;
			}
			return false;
		}

		private boolean solveORblocks(TreeMap<Integer, Boolean> block)
		{
			for (int i = 0; i < (int)block.size(); i++)
			{
				if (block.get(i) == true)
				{
					return true;
				}
			}
			return false;
		}

		private boolean solveANDblocks(TreeMap<Integer, Boolean> block)
		{
			for (int i = 0; i < (int)block.size(); i++)
			{
				if (block.get(i) == false)
				{
					return false;
				}
			}
			return true;
		}

		private void setConclusions(String string)
		{
			int i = 0;
			String cstr;
			int found;

			while (string.charAt(i) != '>' && i < (int)string.length())
			{
				i++;
			}

			cstr = Character.toString(string.charAt(++i));

			found = cstr.indexOf("|");
			if (found == -1)
			{
				for (int k = 0; k < (int)cstr.length(); k++)
				{
					if (Character.isUpperCase(cstr.charAt(k)))
					{
						setFact(cstr.charAt(k));
					}
				}
			}
		}

		private ArrayList<String> getRules(ArrayList<String> strings)
		{
			ArrayList<String> rule = new ArrayList<String>();

			for (int i = 0; i < (int)strings.size(); i++)
			{
				if (strings.get(i).charAt(0) != '?' && strings.get(i).charAt(0) != '=')
				{
					rule.add(strings.get(i));
				}
			}
			return (rule);
		}

		public final void Programloop(ArrayList<String> strings)
		{
			int ruleamount;
			
			ArrayList<String> rules = new ArrayList<String>(getRules(strings));
			String test;

			ruleamount = (int)rules.size();
			for (int k = 0; k < ruleamount; k++)
			{
				for (int j = 0; j < ruleamount; j++)
				{
					test = rules.get(j);
					if (evalXORblock(test) == true)
					{
						setConclusions(strings.get(j));
					}
				}
			}
		}
}
