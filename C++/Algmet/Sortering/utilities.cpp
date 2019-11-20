#ifndef __UTILITIES_CPP
#define __UTILITIES_CPP
#include <iostream>
#include <time.h>
#include <math.h>
using namespace std;
typedef char itemType;

//Fil for diverse utilities til bruk i sorteringsalgoritmene

/*
TODO


genArray()
printArray()

*/

void swap(itemType array[], int a, int b){
    itemType temp = a;
    array[a] = array[b];
    array[b] = array[temp];
}


char* charArrayGenerator(const long long int MAXSIZE = 200000000) { // Tar imot ønsket array-størrelse, 
	srand(time(nullptr));											// Returnerer en randomly-generert char-array med store bokstaver
	char* a = new char[MAXSIZE + 1];
	for (int i = 1; i <= MAXSIZE; i++) {
		a[i] = char(rand() % (int('Z') - int('A')) + int('A'));		// Char fra 0 - (90-65 = 25) + 65 -> char(65-90) (A-Z)
	}

	return a;
}


int* intArrayGenerator(const long long int MAXSIZE = 200000000) {	// Tar imot ønsket array-størrelse, 
	srand(time(nullptr));											// Returnerer en randomly-generert int-array (kan inneholde gjentagende tall)
	int* a = new int[MAXSIZE + 1];
	for (int i = 1; i <= MAXSIZE; i++) {
		a[i] = rand() % (MAXSIZE + 1) + 1;							// Tall fra 1 - MAXSIZE
	}

	return a;
}


void displayArray(itemType a[], int arraySize) {
	for (int i = 1; i <= arraySize; i++) {
		cout << "#" << i << ": " << a[i] << '\n';
	}
}
#endif