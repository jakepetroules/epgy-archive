/*
 * File: sets.c
 * This program works with sets
 */

#include <stdio.h>
#include <string.h>

#define size 26

void unions(int a[], int b[], int c[]);
void intersection(int a[], int b[], int c[]);
int contain(int a[], int b[]);
void init(int set[]);
void process(int set[], char chars[256]);
void display(char* msg1, char* msg2, char set[256]);
void display2(char* msg1, char* msg2, int arr[]);

main()
{
	int setA[size], setB[size], setC[size],i;
	char charsA[256], charsB[256];

	init(setA);
	init(setB);
	init(setC);

	printf("Enter the letters you want in set 1, in no particular order:\n\t");
	gets(charsA);
	process(setA, charsA);

	printf("Enter the letters you want in set 2, in no particular order:\n\t");
	gets(charsB);
	process(setB, charsB);

	printf("\nFor the sets,\n");
	display("A = {", "}\n", charsA);
	display("B = {", "}\n\n", charsB);

	init(setC);
	unions(setA, setB, setC);
	display2("AUB (union) =\t\t\t{", "}\n", setC);

	init(setC);
	intersection(setA, setB, setC);
	display2("AIB (intersection) =\t\t{", "}\n", setC);

	init(setC);
	printf("B[]A (is B a subset of A?) =\t%s\n", ((contain(setA, setB) == 1) ? "TRUE" : "FALSE"));
}

void unions(int a[], int b[], int c[])
{
	int i;
	for (i = 0; i < size; i++)
	{
		if (a[i] == 1 || b[i] == 1)
		{
			c[i] = 1;
		}
	}
}

void intersection(int a[], int b[], int c[])
{
	int i;
	for (i = 0; i < size; i++)
	{
		if (a[i] == 1 && b[i] == 1)
		{
			c[i] = 1;
		}
	}
}

int contain(int a[], int b[])
{
	int c, i;
	if (memcmp(a, b, size) == 0) return 0;
	for (i = 0; i < size; i++)
	{
		if (b[i] == 1)
		{
			if (!(a[i] == 1))
			{
				return 0;
			}
		}
	}
	return 1;
}

void init(int set[])
{
	int i;
	for (i = 0; i < size; i++)
	{
		set[i] = 0;
	}
}

void process(int set[], char chars[256])
{
	int i = 0, j = 0;
	while (chars[i] != '\0')
	{
		if (chars[i] >= 65 && chars[i] <= 90)
		{
			j = (chars[i] - 65);
			set[j] = 1;
		}
		else if (chars[i] >= 97 && chars[i] <= 122)
		{
			j = (chars[i] - 97);
			set[j] = 1;
		}
		i++;
	}
}

void display(char* msg1, char* msg2, char set[256])
{
	int i = 0, dirty = 0;
	printf("%s", msg1);
	while (set[i] != '\0')
	{
		if (set[i] >= 65 && set[i] <= 90)
		{
			printf("%s%c", (dirty == 1 ? "," : ""), set[i]);
			dirty = 1;
		}
		else if (set[i] >= 97 && set[i] <= 122)
		{
			printf("%s%c", (dirty == 1 ? "," : ""), set[i]);
			dirty = 1;
		}
		i++;
	}
	printf("%s", msg2);
}

void display2(char* msg1, char* msg2, int arr[])
{
	int i = 0, dirty = 0;
	printf("%s", msg1);
	for (i = 0; i < size; i++)
	{
		if (arr[i] == 1)
		{
			printf("%s%c", (dirty == 1 ? "," : ""), i + 97);
			dirty = 1;
		}
	}
	printf("%s", msg2);
}