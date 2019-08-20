# bloomfilter

Bloom-filter is highly memory-optimized and time efficient way of checking the presence of certain data out of large set of dataset.
In basic bloom-filter, it has two methods: 
   - add  : used for adding data in dataset.
   - test : used for checking data in dataset.

The test method in bloom-filter will provide two outcome very efficienctly, which are:
   - "Definitely not exists" : indicate with 100% certainity that data does not present in the dataset.
   - "Probably exists" : indicate that data may present in the dataset.
   
