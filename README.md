# megaload
Java  program to create multiple tables to load database

It will create tables to generate database with TB of data

The mege_sql_load contains the full java program to create the SQL to blowup the FactProductInventory to hqave the database load with data to do a production like test in the cloud.

Load the additionalSQL.btq and cross_join_table.csv to the same directory you ran the Adventrueworks loading script from Dieter Noeth.

see https://github.com/dnoeth

You can download directly from this link https://github.com/dnoeth/AdventureWorksDW_Teradata/releases/download/v1.0/AdventureWorksDW_Teradata.zip

Please read the insturctions on Dieters site to see which additional steps you need to do when executing the scripts on a Linux computer.

To run the jar from the command line do the following;

java -jar megaSqlLoader.jar <parameter 1> <paramter 2> <paramater 3> <parameter 4>

Parameter 1 = start number of files

Parameter 2 = end number of files

Paramter 3 = database name      (optional) default is AdventureworksDW

Parameter 4 = file location     (optional) default is directory jar is running

Below an example of the command prompt;

java -jar megaSqlLoader.jar 1 50 Adventure /Users/rikthefrog

One file will be create to populate 50 tables which will be loaded in database Adventure and written to /Users/rikthefrog

any question mail me rikthefrog at gmail.com
