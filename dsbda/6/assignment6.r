library(readxl)
dataset <- read_excel("/home/pict/3956/assign5/AirQualityUCI.xls")
View(dataset)  

#INITIAL EXPLORATORY ANALYSIS
class(dataset)
dim(dataset)
summary(dataset)

#VISUAL EXPLORATORY ANALYSIS
library(plyr)
hist(dataset$`CO(GT)`)
boxplot(dataset$`CO(GT)`)

#//////////////// DATA CLEANING//////////////////////

#REMOVING OUTLIERS
vec1 <- boxplot.stats(dataset$`CO(GT)`)$out
dataset$`CO(GT)`[dataset$`CO(GT)`%in%vec1] <- median(dataset$`CO(GT)`)

#CHECKING FOR MISSING VALUES
any(is.na(dataset))
sum(is.na(dataset))
any(is.na(dataset$`CO(GT)`))

#CHECKING FOR DATA TYPES
class(dataset$`CO(GT)`)

#////////////////// DATA INTEGERATION /////////////////







#//////////////////DATA TRANSFORMATION ///////////////

#CHANGING FOR DATA TYPES
class(dataset$`CO(GT)`)
dataset$`CO(GT)` <- as.character(dataset$`CO(GT)`)
class(dataset$`CO(GT)`)

#TRIMING WHITE SPACES
library(stringr)
dataset$`CO(GT)` <- str_trim(dataset$`CO(GT)`)

#REPLACING NOT PROVIDED WITH NOT AVAILABLE
dataset$`CO(GT)` <- str_replace(dataset$`CO(GT)`,"Not Provided","NA")


#//////////////////ERROR CORRECTING /////////////////

#REMOVING OUTLIERS
vec1 <- boxplot.stats(dataset$`CO(GT)`)$out
dataset$`CO(GT)`[dataset$`CO(GT)`%in%vec1] <- median(dataset$`CO(GT)`)

#/////////////////DATA MODEL BUILDING/////////////////
linearMod <- lm(dataset$`NOx(GT)` ~ dataset$`NO2(GT)`, data=dataset) 
print(linearMod)
summary(linearMod)



