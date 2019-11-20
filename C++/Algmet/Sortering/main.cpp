// Advent of Code Julekalender dag 1: https://adventofcode.com/2018/day/1
#include <iostream>
#include <fstream>
using namespace std;


// CONST:
const int MAXLEN = 20;


// FUNKSJONER:
int main() {
	char tall[MAXLEN];
	int frekvens = 0;

	ifstream inn("input.txt");
	inn.getline(tall, MAXLEN);

	while (!inn.eof()) {
		int sifferPlass = strlen(tall), bufferTall = 0;

		if (tall[0] == '+') {
			for (int i = 1; i <= strlen(tall); i++) {
				bufferTall += tall[i] * pow(10, sifferPlass--);
			}
			frekvens += bufferTall;
		}

		else {
			for (int i = 1; i <= strlen(tall); i++) {
				bufferTall -= tall[i] * pow(10, sifferPlass--);
			}
			frekvens -= bufferTall;
		}

		bufferTall = 0;
		inn.getline(tall, MAXLEN);
	}

	cout << "Resulterende frekvens er " << frekvens << '\n';
	return 0;
}