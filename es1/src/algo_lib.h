#ifndef ALGO_LIB_H
#define ALGO_LIB_H

#include <stddef.h>

// Record struct definition
typedef struct
{
    int id;
    char *fieldString;
    int fieldInt;
    float fieldFloat;
} Record;

// Function prototypes
void merge_sort(void *base, size_t nitems, size_t size, int (*compar)(const void *, const void *));
void quick_sort(void *base, size_t nitems, size_t size, int (*compar)(const void *, const void *));

int compare_int(const void *a, const void *b);
int compare_string(const void *a, const void *b);
int compare_float(const void *a, const void *b);

#endif // ALGO_LIB_H
