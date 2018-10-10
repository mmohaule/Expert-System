/***************************************************************************** #
#                                                                              #
#                                                         :::      ::::::::    #
#    setup.java                                         :+:      :+:    :+:    #
#                                                     +:+ +:+         +:+      #
#    By: thmotaun <marvin@42.fr>                    +#+  +:+       +#+         #
#                                                 +#+#+#+#+#+   +#+            #
#    Created: 2017/10/29 12:37:03 by thmotaun          #+#    #+#              #
#    Updated: 2017/10/29 12:37:05 by thmotaun         ###   ########.fr        #
#                                                                              #
# *****************************************************************************/

import java.util.*;
import java.io.*;

public class setup extends instruction_map
{
		public setup()
		{
			//empty constructor
		}

		private boolean isValid(char c)
		{
			if ((c >= 'A' && c <= 'Z') || c == '+' || c == '|' || c == '?' || c == '<'
			|| c == '>' || c == '!' || c == '^' || c == '=' || c == '(' || c == ')')
			{
				return true;
			}
			return false;
		}

		private String instructions(String line)
		{
			String linedup = "";
			
			for (int i = 0; i < (int)line.length(); i++)
			{
				if (line.charAt(i) == '#')
				{
					break;
				}
				if (isValid(line.charAt(i)))
				{
					linedup += line.charAt(i);
				}
			}
			if (linedup.indexOf("<") != -1)
			{
				linedup = setONLYif(linedup);
			}
			return (linedup);
		}

		private String setONLYif(String str)
		{
			int i = 0;
			String line;

			while (str.charAt(i) != '>')
			{
				i++;
			}
			line = Character.toString(str.charAt(++i));
			line += "=>";
			i = 0;
			while (str.charAt(i) != '<')
			{
				line += str.charAt(i);
				i++;
			}
			return line;
		}

		public final ArrayList<String> start(String argv) throws IOException
		{
			BufferedReader file = null;
			String inread;
			String line;
			ArrayList<String> stringarray = new ArrayList<String>();

			try
			{
				file = new BufferedReader(new FileReader(argv));

				while ((inread = file.readLine()) != null)
				{
					line = instructions(inread);
					if (line.length() > 0)
					{
						stringarray.add(line);
					}
				}
				return stringarray;
			}
			catch(Exception e)
			{
				System.out.println((char)27 + "[31m" + "Error opening file, " + e.getMessage());
			}
			finally
			{
				if (file != null)
				{
					try
					{
						file.close();
					}
					catch(Throwable t)
					{

					}
				}
			}
			return null;
		}
}
