#include "algo_lib.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void merge_sort(void *base, size_t nitems, size_t size, int (*compar)(const void *, const void *));
void merge(void *base, size_t left, size_t middle, size_t right, size_t size, int (*compar)(const void *, const void *));

void quick_sort(void *base, size_t nitems, size_t size, int (*compar)(const void *, const void *));
void quicksort_recursive(void *base, size_t low, size_t high, size_t size, int (*compar)(const void *, const void *));
void three_way_partition(void *base, size_t low, size_t high, size_t size, int (*compar)(const void *, const void *), size_t *lt, size_t *gt);
void swap(void *a, void *b, size_t size);


// Complete merge sort function to implement
void merge_sort(void *base, size_t nitems, size_t size, int (*compar)(const void *, const void *))
{
    if (nitems > 1) {
        size_t middle = nitems / 2;
        merge_sort(base, middle, size, compar);
        merge_sort((char *)base + middle * size, nitems - middle, size, compar);
        merge(base, 0, middle - 1, nitems - 1, size, compar);
    }
} // merge_sort

// Function that merge together and sort two sorted arrays 
void merge(void *base, size_t left, size_t middle, size_t right, size_t size, int (*compar)(const void *, const void *))
{
    size_t i, j, k;
    size_t n1 = middle - left + 1;
    size_t n2 = right - middle;

    void *left_array = malloc(n1 * size);
    void *right_array = malloc(n2 * size);

    memcpy(left_array, (char *)base + left * size, n1 * size);
    memcpy(right_array, (char *)base + (middle + 1) * size, n2 * size);

    i = 0;
    j = 0;
    k = left;
    while (i < n1 && j < n2) {
        if (compar((char *)left_array + i * size, (char *)right_array + j * size) <= 0) {
            memcpy((char *)base + k * size, (char *)left_array + i * size, size);
            i++;
        }
        else {
            memcpy((char *)base + k * size, (char *)right_array + j * size, size);
            j++;
        }
        k++;
    }

    while (i < n1) {
        memcpy((char *)base + k * size, (char *)left_array + i * size, size);
        i++;
        k++;
    }

    while (j < n2) {
        memcpy((char *)base + k * size, (char *)right_array + j * size, size);
        j++;
        k++;
    }

    free(left_array);
    free(right_array);
} // merge

// Main quicksort function that call the 
void quick_sort(void *base, size_t nitems, size_t size, int (*compar)(const void *, const void *))
{
    if (nitems <= 1)
        return;
    quicksort_recursive(base, 0, nitems - 1, size, compar);
} // quick_sort

// Actual quicksort implementation with the parameters to start
void quicksort_recursive(void *base, size_t low, size_t high, size_t size, int (*compar)(const void *, const void *))
{
    if (low < high)
    {
        size_t lt, gt;
        three_way_partition(base, low, high, size, compar, &lt, &gt);
        if (lt > 0)
            quicksort_recursive(base, low, lt - 1, size, compar);
        quicksort_recursive(base, gt + 1, high, size, compar);
    }
} // quicksort_recursive

// Function to partition the array for quicksort
void three_way_partition(void *base, size_t low, size_t high, size_t size, int (*compar)(const void *, const void *), size_t *lt, size_t *gt)
{
    char *array = (char *)base;
    void *pivot = array + low * size;
    size_t i = low;
    *lt = low;
    *gt = high;

    while (i <= *gt)
    {
        int cmp = compar(array + i * size, pivot);
        if (cmp < 0)
        {
            swap(array + (*lt) * size, array + i * size, size);
            (*lt)++;
            i++;
        }
        else if (cmp > 0)
        {
            swap(array + i * size, array + (*gt) * size, size);
            (*gt)--;
        }
        else
        {
            i++;
        }
    }
} // three_way_partition

// Swap two elemets by bytes
void swap(void *a, void *b, size_t size)
{
    char *temp = malloc(size);
    if (temp == NULL)
    {
        fprintf(stderr, "Memory allocation failed\n");
        exit(EXIT_FAILURE);
    }
    memcpy(temp, a, size);
    memcpy(a, b, size);
    memcpy(b, temp, size);
    free(temp);
} // swap

// Comparison function for integers (ascending order)
int compare_int(const void *a, const void *b)
{
    const Record *record_a = (const Record *)a;
    const Record *record_b = (const Record *)b;
    if (record_a->fieldInt < record_b->fieldInt)
        return -1;
    if (record_a->fieldInt > record_b->fieldInt)
        return 1;
    return 0;
}

// Comparison function for strings (lexicographical order)
int compare_string(const void *a, const void *b)
{
    const Record *record_a = (const Record *)a;
    const Record *record_b = (const Record *)b;
    return strcmp(record_a->fieldString, record_b->fieldString);
}

// Comparison function for floats (ascending order)
int compare_float(const void *a, const void *b)
{
    const Record *record_a = (const Record *)a;
    const Record *record_b = (const Record *)b;
    if (record_a->fieldFloat < record_b->fieldFloat)
        return -1;
    if (record_a->fieldFloat > record_b->fieldFloat)
        return 1;
    return 0;
}