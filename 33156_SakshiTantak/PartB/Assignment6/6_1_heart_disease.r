#Reading csv file
heart_data <- read.csv("Cleavland.csv")
class(heart_data)
names(heart_data)
str(heart_data)
dim(heart_data)
summary(heart_data)

# Change the name of headers
names(heart_data)[1] <- "age"
names(heart_data)[2] <- "Gender"
names(heart_data)[3] <- "cp"
names(heart_data)[4] <- "trestbps"
names(heart_data)[5] <- "chol"
names(heart_data)[6] <- "fbs"
names(heart_data)[7] <- "restecg"
names(heart_data)[8] <- "thalach"
names(heart_data)[9] <- "exang"
names(heart_data)[10] <- "oldpeak"
names(heart_data)[11] <- "slope"
names(heart_data)[12] <- "ca"
names(heart_data)[13] <- "thal"
names(heart_data)[14] <- "num"

names(heart_data)

#Checking for errors
class(heart_data$age)
class(heart_data$Gender)
class(heart_data$age)
class(heart_data$cp)
class(heart_data$trestbps)
class(heart_data$chol)
class(heart_data$fbs)
class(heart_data$ca)
class(heart_data$thal)
class(heart_data$num)



#Histogram for overall distribution
install.packages("plyr")
library(plyr)
hist(heart_data$age)

#Boxplot for median
boxplot(heart_data$age)


heart_data$ca
levels(heart_data$ca)[levels(heart_data$ca) == "?"]<-"0.0"
heart_data
heart_data$ca[heart_data$ca == 1.0]
typeof(heart_data$ca)
nrow(heart_data)

# Plotting Fate vs number of records
heart_data$num[heart_data$num >= 1] <- 1 # Edit the fate to 0 and 1
barplot(table(heart_data$num), main="Fate", col="black")


mosaicplot(heart_data$Gender ~ heart_data$num,main="Fate by Gender",
           shade=FALSE,color=TRUE,xlab="Gender", ylab="Heart disease")
# Most important step
levels(heart_data$thal)[levels(heart_data$thal)=="?"]<-"3.0"

heart_data$thal
table(heart_data$thal)

heart_data$thal[is.na(heart_data$thal)]<-'3.0'
table(heart_data$ca)
library(caTools)
n<- sapply(heart_data[, c(1)], mean)
set.seed(123)

v3 <- heart_data[c(11:14),c(2,7:9)]
View(v3)
v3
m<- sapply(v3,max)
m

set.seed(121)

split = sample.split(heart_data$num, SplitRatio = 2/3)
train_heart_data = subset(heart_data, split == TRUE)
test_heart_data = subset(heart_data, split == FALSE)

library(caTools)
regressor=lm(formula = num~age, data=train_heart_data)

View(regressor)

hd_age_predict=predict(regressor, newdata=test_heart_data)
hd_age_predict
round_age=hd_age_predict
rage=round(round_age)
View(rage)
table(rage,test_heart_data$num)

library(e1071)
library(caret)
typeof(rage)

levels(rage)
levels(test_heart_data$num)

str(rage)
r1 = as.data.frame(rage)
r1
df1=confusionMatrix(as.factor(r1$rage),as.factor(test_heart_data$num))
df1

