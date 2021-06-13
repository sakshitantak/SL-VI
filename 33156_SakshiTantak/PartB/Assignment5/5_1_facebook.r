#readr for reading rectangular data such as csv,tsv etc.
library(readr)

dataset_Facebook <- read_csv("dataset_Facebook.csv")
dataset_Facebook
View(dataset_Facebook)
head(dataset_Facebook)

# Data Subsetting

# select variables 
myvars <- c("Type", "Category", "Paid")
new_data <- dataset_Facebook[myvars] 
View(new_data)

var1 <- dataset_Facebook[c(1,5:10)]
View(var1)
var1

var2 <- subset(dataset_Facebook,comment>30)
View(var2)
var2

#merge data
m1 <- dataset_Facebook[c(10:12),c(2,6:7)]
m2 <- dataset_Facebook[c(11:14),c(2,7:9)]
m3 <- merge(m1,m2,by="Paid")
View(m3)
m3

#sort data
s1 <- dataset_Facebook[order(dataset_Facebook$comment),]
View(s1)
s1

s2 <- dataset_Facebook[order(dataset_Facebook$comment, -dataset_Facebook$share),]
View(s2)
s2

# Transposing Data (reversing rows and columns)
temp <- dataset_Facebook[1:5,1:4]
temp
t(temp)

# Melting Data to long format
install.packages('reshape2')
library(reshape2)
# Melt, i.e. each row is a unique ID-variable combination
m1 <- melt('Category', id <-  c("Type","Lifetime.Post.Total.Reach"))
m2 <- m1 # Extra variable
View(m1)
head(m1)
tail(m1)





