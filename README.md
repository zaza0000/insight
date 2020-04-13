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


## Repo directory structure

    ├── README.md
    ├── run.sh
    ├── src
    │   └── Main.java
    │   └── DataManager.py
    │   └── CSVParser.py
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
                    

