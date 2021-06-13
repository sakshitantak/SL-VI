#readr for reading rectangular data such as csv,tsv etc.
library(readr)

population <- read_csv("bigcity.csv")
View(population)
head(population)

# Data Subsetting

# select variables 
myvars <- c("u", "x")
new_data <- population[myvars] 
new_data

var1 <- population[c(1)]
var1
var2 <- subset(population,u>100)
var2

#merge data
m1 <- population[c(1)]
m2 <- population[c(2)]
m3 <- merge(m1,m2)
View(m3)
m3

#sort data
s1 <- population[order(population$x),]
View(s1)
s1

s2 <- population[order(population$x, -population$u),]
View(s2)
s2

# Transposing Data (reversing rows and columns)
temp <- population[1:2]
temp
t(temp)

# Melting Data to long format
library(reshape2)
# Melt, i.e. each row is a unique ID-variable combination
m1 <- melt('x', id <-  c("u"))
m2 <- m1 # Extra variable
View(m1)
head(m1)
tail(m1)



