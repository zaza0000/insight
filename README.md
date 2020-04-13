# Consumer Complaints

Since we want three different kinds of output (A: the total number of complaints, B: the number of companies receiving a complaint, C: the highest percentage of complaints directed at a single company) categorized by each product and year. A storage structure would be needed like this:
| Product \ Year | year_1 |  year_2 | ... | year_n |
| --------- | --------- |--------- |--------- |--------- |
| **product_1** | (A_11, B_11, C_11) | (A_12, B_12, C_12) | ... | (A_1n, B_1n, C_1n) |
| **product_2** | (A_21, B_21, C_21) | (A_22, B_22, C_22) | ... | (A_2n, B_2n, C_2n) |
| **...** | ... | ... | ... | ... |
| **product_m** |(A_m1, B_m1, C_m1) | (A_m2, B_m2, C_m2) | ... | (A_mn, B_mn, C_mn) |

## Data structure design
To implement this table into real code, I designed two classes. One is the DataManager class while the other is ProductYear class. DataManager contains a hash map whose key is the product type that enable us to divide all the data by different product. The value of the hash map is another hash map (Map<Integer, ProductYear> yearToProductYear) whose key is the year. As a result, we are able to split the data by different years. ProductYear is the other class I have designed, all data in the same ProductYear instance has the same product time and year. Here is a graph to show the structure.
### graph
<img width="1101" alt="Screen Shot 2020-04-13 at 1 22 44 AM" src="https://user-images.githubusercontent.com/31771655/79095963-d6da8e00-7d29-11ea-8c20-1ea0ef411304.png">

The ProductYear class contains three variables: the total number of complaints which is one of our outputs; a hashmap indicates the number of complaints corresponding to different companies, the size of this hash map would be the answer for the number of companies receiving a complaint; an integer value to store the maximum complaint number among all the companies, we need this to compute the highest percentage of complaints directed at a single company. The basic function of the ProductYear class is to insert a new complaint record. Every time a new record comes in, we do an add-one to the total number of complaints, we update the hash map for the related company, and if the update leads to a larger complaint number, then update the maximum complaint number.

## Workflow
1. Scan the CSV folder and get all CSV files, my code is able to handle multiple files in the same folder.
2. Read the first line, which is the field name, then we are able to know which one is needed and where is it. I create a hashmap to indicate the index of different fields.
3. Read a line each time. Do a split by comma. I use regex to split comma only on the outside of double-quotes. 
4. Create the data structure and do the update. For DataManager, I use the singleton design pattern since I want this DataManager to be like a server. So there should be at most one instance. 
5. After inserted all the data, print the output into a new CSV file.

Additional:
To avoid invalid lines, like duplicate records. I checked the complaints id before inserting it. If this id existed I don't insert it a second time. Other cases like invalid content that leads to an improper split, or an invalid number. I also do the check before insert. These records will be skipped. 


## Repo directory structure
Here is the repository directory structure. I create a run.sh so just run source run.sh,  then the program would process all the files in the input file folder.
I have three java files. The Main.java scans the input folder and process all the CSV files sequentially. The CSVParse.java use BufferedReader to read line by line and send each line to the DataManager. The DataManager parses each line and updates the  ProductYear instance of the corresponding product type and year.

    ├── README.md
    ├── run.sh
    ├── src
    │   └── Main.java
    │   └── DataManager.java
    │   └── CSVParser.java
    ├── input
    │   └── complaints.csv
    ├── output
    |   └── report.csv
    ├── insight_testsuite
        └── tests
            └── test_1
            |   ├── input
            |   │   └── complaints.csv
            |   |__ output
            |   │   └── report.csv
            ├── your-own-test_1        // manually add some data        
            |   ├── input
            |   │   └── complaints.csv
            |   |── output
            |       └── report.csv
            ├── your-own-test_2        // multipule input files         
            |   ├── input
            |   │   └── complaints.csv
            │   │   └── complaints2.csv
            |   |── output
            |       └── report.csv
            |       └── report2.csv
            ├── your-own-test_3        // a larger case
                ├── input
                │   └── complaints.csv    // too large to upload
                |── output
                    └── report.csv
                    

