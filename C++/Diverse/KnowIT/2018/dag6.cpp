// KnowIT Kodekalender dag 6
/* Nulltunge tall
Nulltunge tall er heltall som har flere nuller i seg enn de andre sifferene til sammen. For eksempel er 1050006 nulltungt, og 105006 er ikke.

Hva er summen av alle nulltunge tall fra og med 1 til og med 18 163 106? */
#include <iostream>
using namespace std;


// FUNKSJONER:
bool nullTungt(long long int n) {
	int antNull = 0, antEllers = 0;

	while (n) {
		if (n % 10) antEllers++;
		else antNull++;
		n /= 10;
	}

	if (antNull > antEllers) return true;
	return false;
}


int main() {
	long long int sum = 0;
	cout << "Finner sum....\n";

	for (long long int i = 1; i <= 18163106; i++) {
		if (nullTungt(i)) sum += i;
	}

	cout << "Summen er: " << sum;
	return 0;
}