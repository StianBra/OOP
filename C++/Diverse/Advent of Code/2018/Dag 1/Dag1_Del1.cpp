// Advent of Code Julekalender dag 1: https://adventofcode.com/2018/day/1
#include <iostream>
#include <fstream>
using namespace std;


// CONST:
const int MAXLEN = 20;
const int MAXFREKVENSER = 100000;

// GLOBALE VARIABLER:
int frekvenser[MAXFREKVENSER];


// FUNKSJONER:
bool finnesFrekvens(int freq) {
	int i = 0;

	while (frekvenser[i] != -99999999 && i != MAXFREKVENSER) {
		if (frekvenser[i] == freq) return true;
		i++;
	}

	frekvenser[i] = freq;
	return false;
}


int main() {
	char tall[MAXLEN];
	int frekvens = 0;

	ifstream inn("input.txt");
	inn.getline(tall, MAXLEN);

	for (int i = 0; i <= MAXFREKVENSER; i++) {
		frekvenser[i] = -99999999;
	}

	while (!inn.eof()) {
		int len = strlen(tall) - 1;
		int sifferPlass = len - 1, bufferTall = 0;

		if (tall[0] == '+') {
			for (int i = 1; i <= len; i++) {
				bufferTall += int(tall[i] - 48) * pow(10, sifferPlass--);
			}
		}

		else {
			for (int i = 1; i <= len; i++) {
				bufferTall -= int(tall[i] - 48) * pow(10, sifferPlass--);
			}
		}

		frekvens += bufferTall;
		bufferTall = 0;
		inn.getline(tall, MAXLEN);

		if (finnesFrekvens(frekvens)) cout << "Denne frekvensen finnes allerede: " << frekvens << '\n';
	}

	cout << "Resulterende frekvens er " << frekvens << '\n';
	return 0;
}