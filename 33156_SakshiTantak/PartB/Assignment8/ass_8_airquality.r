library(readr)
dataset <- read_csv("AirQualityUCI.csv")
View(dataset)  

#BASIC PLOTS

#Strip Charts
help(stripchart)
stripchart(dataset$`CO(GT)`)
stripchart(dataset$`NOx(GT)`)

#Line Charts
temp <- c(1, 3, 6, 4, 9)
plot(temp)
plot(temp, type="o", col="blue")
title(main="Autos", col.main="red", font.main=4)

temp <- c(1, 3, 6, 4, 9)
temp2 <- c(2, 5, 4, 5, 12)
plot(temp, type="o", col="blue", ylim=c(0,12))

#Bar Charts
temp <- c(1, 3, 6, 4, 9)
barplot(temp)

#Histogram
suvs <- c(4,4,6,6,16)
hist(suvs)

#Pie Charts
temp <- c(1, 3, 6, 4, 9)
pie(temp)
pie(temp, main="temp", col=rainbow(length(temp)),
    labels=c("Mon","Tue","Wed","Thu","Fri"))

#Dotcharts
dotchart(t(dataset$`CO(GT)`))

#Boxplot
boxplot(dataset$`NMHC(GT)`)

#Scatter Plots
plot(dataset$`CO(GT)`,dataset$`C6H6(GT)`)
cor(dataset$`CO(GT)`,dataset$`C6H6(GT)`)
plot(dataset$`CO(GT)`,dataset$`C6H6(GT)`, main="CO vs C6H6", xlab="CO(GT)", ylab="C6H6(GT)")

#Normal QQ Plots
qqnorm(dataset$`NMHC(GT)`)

#ADVANCED PLOTS


