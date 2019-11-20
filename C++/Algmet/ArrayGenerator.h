#ifndef __ARRAYGENERATOR_H
#define __ARRAYGENERATOR_H
#include <iostream>
#include <time.h>
#include <math.h>
using namespace std;
typedef long long int itemType;

class ArrayGen {
private:
	itemType * a;

public:
	ArrayGen(const long long int MAXSIZE = 2000000000000000000) {
		srand(time(nullptr));
		a = new itemType[MAXSIZE];
		for (int i = 0; i <= MAXSIZE; i++) {
			a[i] = rand() % MAXSIZE + 1;		// Tall fra 1 - MAXSIZE
		}
	}

	~ArrayGen() { delete[] a; }
}
#endif