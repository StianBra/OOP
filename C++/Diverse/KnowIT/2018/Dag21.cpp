// KnowIT Julekalender dag 21: https://julekalender.knowit.no/doors/cjpw92pgsubt90167nb7lh9hu

/* Et overskuddstall (abundant number) er et positivt heltall med følgende egenskap: Summen av dets divisorer er større enn tallet selv. 
12 er for eksempel et overskuddstall, fordi 12 kan deles på 1, 2, 3, 4 og 6 (som til sammen er 16). 
Legg merke til at 1 regnes som en divisor, mens tallet i seg selv ikke gjør det.

En annen artig egenskap med overskuddstall er at visse av dem har to primtall som naboer. 
Blant de ti første overskuddstallene, 12, 18, 20, 24, 30, 36, 40, 42, 48, 54, har 12, 18, 30, 42 denne egenskapen. 
La oss kalle et slikt tall en inneklemt riking.

Blant tallene under 10 000 000, hva er summen av alle inneklemte rikinger? */


#include <iostream>
using namespace std;


// CONST:
const unsigned long long int MAXNUM = 10000000;


// GLOBALE VARIABLER:


// FUNKSJONER:
bool erPrimtall(unsigned long long int tall) {
	unsigned long long int i = 2;

	while (i < tall / 2) {
		if (tall % i == 0) return false;
		i++;
	}

	return true;
}


bool erOverskuddstall(unsigned long long int tall) {
	unsigned long long int divisorSum = 0,
		i = 1;

	while (tall > divisorSum && i <= tall / 2) {
		if (tall % i == 0) divisorSum += i;
		i++;
	}

	return (tall < divisorSum);
}




int main() {
	unsigned long long int inneklemtRikingSum = 0;
	int linjeCount = 0;

	for (unsigned long long int i = 2; i <= MAXNUM; i += 2) {
		if (erOverskuddstall(i)) {
			if (erPrimtall(i - 1) && erPrimtall(i + 1)) {
				inneklemtRikingSum += i;
				if (linjeCount++ % 100 == 0) {
					cout << "Tallet " << i << " er et overskuddstall. \n"
						<< "inneklemtRikingSum er foreløpig " << inneklemtRikingSum << ".\n";
				}
			}
		}
	}

	cout << "Sum av inneklemte rikinger: " << inneklemtRikingSum << '\n';

	return 0;
}