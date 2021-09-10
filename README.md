# Assessment preparation TAF

UI tests for kinopoisk.ru

1 Using Advanced Search mode («Расширенный поиск») select country «СССР», fill genre with multiple values «детский,
мультфильм, семейный», perform search and verify that the 1st in list is «Винни Пух».

2 Using «Топ 250» list collect all information about the movies, verify that the number of movies with less than 100k
views is within 30 and 40.

3 Using the same «Топ 250» list check that the median rating of the 2010 films is lower than the median of 2019.

4 Verify that the count of .png images is more than 5 after the start page is loaded (use any proxy to filter the
traffic, for example BrowserUp proxy https://github.com/browserup/browserup-proxy) - recommended, but optional.

 *********
Run test command example:

mvn clean test -DsuiteXmlFile=testng-all -Dbrowser=opera

testng-all -- testng.xml file located in src/test/resources/

**********
Screenshots location and failed test results in folder:

build/reports/tests/
