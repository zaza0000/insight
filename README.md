# Consumer Complaints

Since we want three different kinds of output (A: the total number of complaints, B: the number of companies receiving a complaint, C: the highest percentage of complaints directed at a single company) categorized by each product and year. A storage structure would be needed like this:
| Product \ Year | year_1 |  year_2 | ... | year_n |
| --------- | --------- |--------- |--------- |--------- |
| **product_1** | (A_11, B_11, C_11) | (A_12, B_12, C_12) | ... | (A_1n, B_1n, C_1n) |
| **product_2** | (A_21, B_21, C_21) | (A_22, B_22, C_22) | ... | (A_2n, B_2n, C_2n) |
| **...** | ... | ... | ... | ... |
| **product_m** |(A_m1, B_m1, C_m1) | (A_m2, B_m2, C_m2) | ... | (A_mn, B_mn, C_mn) |

## Data structure
![image](https://github.com/zaza0000/insight/tree/master/IMG/data_structure.png)

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
                    

