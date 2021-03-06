package mege_sql_load;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class megaLoadSql {

	public static void main(String[] args) throws IOException {
		
		int j;
		int k ;
		String database;
		String directory;
		
		//check if args are correct 
		
		if(args.length < 2) {
			System.out.println("Please fill in the start and end number in the command line");
		}
		
		if(tryParseInt(args[0]) && args.length > 2) {
			j = Integer.parseInt(args[0]);
		}
		else {
			j = 1;
		}
		
		if(tryParseInt(args[1]) && args.length > 2) {
			k = Integer.parseInt(args[1]);
		}
		else {
			k = 2;
		}
		
		if(k <=j ) {
			System.out.println("first parameter should be smaller than second paramter");
		}
		
		if(j == 1 && k == 2) {
			System.out.println("First to parameters should be an integer");
		}
		
		if(args.length > 2) {
			}
		else {
			j = 0;
			k = 0;
		}
		
		if(args.length == 3) {
			database = args[2].toString();
		}
		else {
			database = "AdventureWorksDW";
		}
		
		if(args.length == 4) {
			directory = args[3].toString();
		}
		else {
			directory = System.getProperty("user.dir");
		}
		
		String fileName = directory + "/" + j + "-" + k + ".sql";
		try {
			File file = new File(fileName);
			if(!file.canWrite()) {
				System.out.println("Directory is writeable proceed");
			}
			else {
				System.out.println("Directory is not writable");
				System.out.println(fileName + " is not writeable please check");
				System.exit(99);
			}
			
			FileWriter fileWriter = new FileWriter(fileName);
		    PrintWriter printWriter = new PrintWriter(fileWriter);
		    printWriter.print("DATABASE " + database + " ;\n");
		    for(int i=j;i<=k;i++) {
		    	    String table = "CREATE TABLE FactProductInventory_BlowUp" + i + " AS (SELECT  A1.ColId,\n";
		    		printWriter.print(table);
		    		printWriter.print("	        B1.ProductKey,\n");
					printWriter.print("        B1.DateKey,\n");
					printWriter.print("        B1.MovementDate,\n");
					printWriter.print("        B1.UnitCost,\n");
					printWriter.print("        B1.UnitsIn,\n");
					printWriter.print("        B1.UnitsOut,\n");
					printWriter.print("        B1.UnitsBalance\n");
					printWriter.print("        FROM FactProductInventory B1\n");
					printWriter.print("        CROSS JOIN \n");
					printWriter.print("        CrossJoinTable A1)\n");
					printWriter.print("     WITH DATA;\n");
		    	
			}
		    
		    printWriter.close();
		    
		    fileName = directory + "/" + j + "-" + k + ".btq";
		    fileWriter = new FileWriter(fileName);
		    printWriter = new PrintWriter(fileWriter);
		    printWriter.print("/*============================================================================\n");
		    printWriter.print("File: _install.btq\n");
		    printWriter.print("\n");
		    printWriter.print("Summary: Creates the AdventureWorks 2014 Warehouse sample database.\n");
		    printWriter.print("\n");
		    printWriter.print("Date: Februar 23, 2014\n");
		    printWriter.print("Teradata Version: 14.10/15/15.10/16/16.10/16.20+\n");
		    printWriter.print("Source: https://github.com/dnoeth/AdventureWorksDW_Teradata\n");
		    printWriter.print("\n");
		    printWriter.print("------------------------------------------------------------------------------\n");
		    printWriter.print("### ORIGINAL LICENSE ###\n");
		    printWriter.print("This file is part of the Microsoft SQL Server Code Samples.\n");
		    printWriter.print("\n");
		    printWriter.print("Copyright (C) Microsoft Corporation. All rights reserved.\n");
		    printWriter.print("\n");
		    printWriter.print("This source code is intended only as a supplement to Microsoft\n");
		    printWriter.print("Development Tools and/or on-line documentation. See these other\n");
		    printWriter.print("materials for detailed information regarding Microsoft code samples.\n");
		    printWriter.print("\n");
		    printWriter.print("All data in this database is ficticious.\n");
		    printWriter.print("\n");
		    printWriter.print("THIS CODE AND INFORMATION ARE PROVIDED \"AS IS\" WITHOUT WARRANTY OF ANY\n");
		    printWriter.print("KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE\n");
		    printWriter.print("IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A\n");
		    printWriter.print("PARTICULAR PURPOSE.\n");
		    printWriter.print("============================================================================*/\n");
		    printWriter.print("\n");
		    printWriter.print(".SET SESSION TRANSACTION BTET;\n");
		    printWriter.print("\n");
		    printWriter.print(".SET ECHOREQ ERRORONLY;\n");
		    printWriter.print(".SET MAXERROR 0;\n");
		    printWriter.print(".SET TIMEMSG NONE;\n");
		    printWriter.print(".SET QUIET ON ALL;\n");
		    printWriter.print("\n");
		    printWriter.print("-- logon and create database AdventureWorksDW\n");
		    printWriter.print(".RUN FILE _logon.txt;\n");
		    printWriter.print("\n");
		    printWriter.print("-- Create tables\n");
		    printWriter.print("DATABASE " + database + ";\n");
		    for(int i=j;i<=k;i++) {
			    printWriter.print(" CREATE MULTISET TABLE FactProductInventory_BlowUp" + i + " \n");
			    printWriter.print("    (\n");
			    printWriter.print("      ColId VARCHAR(10) CHARACTER SET LATIN NOT CASESPECIFIC NOT NULL,  \n");
			    printWriter.print("      ProductKey INTEGER NOT NULL,\n");
			    printWriter.print("      DateKey INTEGER NOT NULL,\n");
			    printWriter.print("      MovementDate DATE FORMAT 'YYYY-MM-DD' NOT NULL,\n");
			    printWriter.print("      UnitCost NUMBER(18,4) NOT NULL COMPRESS (.41 ,.18 ,.36 ,.31 ,.26 ,.44 ,.21 ,.39 ,.16 ,.34 ,.29 ,.24 ,.42 ,.19 ,.37 ,.32 ,.27 ,.45 ,.22 ,.4 ,.17 ,.35 ,.3 ,.25 ,.43 ,.2 ,.38 ,.33 ,.51 ,.28 ,.23 ),\n");
			    printWriter.print("      UnitsIn INTEGER NOT NULL COMPRESS 0 ,\n");
			    printWriter.print("      UnitsOut INTEGER NOT NULL COMPRESS 0 ,\n");
			    printWriter.print("      UnitsBalance INTEGER NOT NULL COMPRESS (4 ,52 ,84 ,88 ,875 ,438 ,700 ),\n");
			    printWriter.print("PRIMARY KEY ( ColId, ProductKey ,DateKey ));\n");
			    printWriter.print("\n");
			    printWriter.print("INSERT INTO FactProductInventory_BlowUp" + i + "\n");
			    printWriter.print("(\n");
			    printWriter.print("    ColId,\n");
			    printWriter.print("    ProductKey,\n");
			    printWriter.print("    DateKey,\n");
			    printWriter.print("    MovementDate,\n");
			    printWriter.print("    UnitCost,\n");
			    printWriter.print("    UnitsIn,\n");
			    printWriter.print("    UnitsOut,\n");
			    printWriter.print("    UnitsBalance\n");
			    printWriter.print(")\n");
			    printWriter.print("SELECT  A1.ColId,\n");
			    printWriter.print("        B1.ProductKey,\n");
			    printWriter.print("        B1.DateKey,\n");
			    printWriter.print("        B1.MovementDate,\n");
			    printWriter.print("        B1.UnitCost,\n");
			    printWriter.print("        B1.UnitsIn,\n");
			    printWriter.print("        B1.UnitsOut,\n");
			    printWriter.print("        B1.UnitsBalance\n");
			    printWriter.print("        FROM FactProductInventory B1\n");
			    printWriter.print("        CROSS JOIN \n");
			    printWriter.print("        CrossJoinTable A1;\n");
		    }
		    printWriter.print(".SET QUIET OFF;\n");
		    printWriter.print("\n");
		    printWriter.print("SELECT CAST(Sum(CurrentPerm) AS INT) AS PermSpace, Cast(TableName AS VARCHAR(45)) AS TableName\n");
		    printWriter.print("FROM dbc.TableSizeV\n");
		    printWriter.print("WHERE DatabaseName = DATABAS\n");
		    printWriter.print("GROUP BY TableName\n");
		    printWriter.print("ORDER BY TableName\n");
		    printWriter.print(";\n");
		    printWriter.print("\n");
		    printWriter.print("SELECT Current_Timestamp(2) AS \"finished loading\"\n");
		    printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	    System.out.println("SQL file can be found here: " + directory + "/" + j + "-" + k + ".sql");
	    System.out.println("BTEQ file can be found here: " + directory + "/" + j + "-" + k + ".btq");
	}
	
	static boolean tryParseInt(String value) {  
	     try {  
	         Integer.parseInt(value);  
	         return true;  
	      } catch (NumberFormatException e) {  
	         return false;  
	      }  
	}

}
