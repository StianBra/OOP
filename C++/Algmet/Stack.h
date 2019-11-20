#ifndef __STACK_H
#define __STACK_H
#include <iostream>
using namespace std;

typedef Stack itemType;

class Stack {
private:
	itemType *stack;
	int p, n;

public:
	Stack(const int MAX = 100) {
		stack = new itemType[MAX];
		p = 0, n = MAX;
	}

	~Stack() {
		delete[] stack;
	}

	void push(itemType item) {
		if (p < n) stack[p++] = item;
		else cout << "Stack array is full! \n";
	}

	itemType pop() {
		if (p > 0) return stack[--p];
		else cout << "Stack array is empty! \n";
	}

	int isEmpty() {
		return !p;
	}

	void printStack() {
		for (int i = 0; i < p; i++) {
			cout << stack[i] << " ";
		}
		cout << "\n";
	}
};
#endif