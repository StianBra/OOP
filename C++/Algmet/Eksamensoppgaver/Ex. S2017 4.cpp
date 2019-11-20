// Eksamen S.2017, 4)
// INCLUDES:
#include <iostream>
using namespace std;


// GLOBALE VARIABLER:
const int MAXNUM = 1000000;					 //  Arrayens lengde.
int tall;									 //  Global array av int'er.


// FUNKSJONER:
int finnSiffer(int n) {
	int antSiffer = 0;

	while (n) {
		++antSiffer;
		n /= 10;
	}

	return antSiffer;
}


int finnVenstre(int n, int siffer) {
	return (n / int(pow(10, siffer/2)));
}


int finnHoyre(int n, int siffer) {
	return (n % int(pow(10, siffer/2)));
}


bool sjekkLosn(int n) {
	int venstre = finnVenstre(n, finnSiffer(n));
	int hoyre = finnHoyre(n, finnSiffer(n));

	return (pow(venstre + hoyre, 2) == n);
}


void genNr() {
	for (int i = 1; i < MAXNUM; i++) {
		if (sjekkLosn(i)) cout << "Gyldig losning: " << i << '\n';
	}
}


int main() {
	genNr();                     // Lager/skriver permutasjon av ALLE sifrene.
	return 0;
}