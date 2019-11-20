#include <iostream>
#include <time.h>
#include <math.h>
#include "utilities.cpp"
#include "iterativ_selection.cpp"
using namespace std;
typedef char itemType;

// MAIN:
int main() {
	int arraySize;
	itemType* randomArray;

	// GENERERING AV ARRAY:
	cout << "Size of array to generate: ";
	cin >> arraySize;
	cout << "\nGenerating array...\n";
	randomArray = charArrayGenerator(arraySize);


	// ARRAY FØR SORTERING:
	cout << "Pre-sort:\n";
	displayArray(randomArray, arraySize);


	// VALG AV SORTERINGSALGORITME:
	iterativeSelectionSort(randomArray);


	// ARRAY ETTER SORTERING:
	cout << "Post-sort:\n";
	displayArray(randomArray, arraySize);
}