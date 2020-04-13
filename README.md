# Consumer Complaints


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
                    

