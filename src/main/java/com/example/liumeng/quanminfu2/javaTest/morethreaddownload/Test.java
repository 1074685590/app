package com.example.liumeng.quanminfu2.javaTest.morethreaddownload;

import java.io.IOException;

public class Test
{

	public static void main(String[] args)
	{
		try
		{
			new MultipartThreadDownloador("http://localhost:8080/nexus.zip",
					"d:/backup/nexus", "nexus.zip", 4).download();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}
}
