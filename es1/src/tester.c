#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include <stddef.h>

#include "unity.h"
#include "algo_lib.h"

void setUp();

void test_merge_sort_empty_arrays();
void test_merge_sort_equal_int();
void test_merge_sort_equal_string();
void test_merge_sort_equal_float();
void test_merge_sort_two_records_arrays();

void test_quick_sort_empty_arrays();
void test_quick_sort_equal_int();
void test_quick_sort_equal_string();
void test_quick_sort_equal_float();
void test_quick_sort_two_records_arrays();

static int v1, v2, v3, v4, v5, v6, v7, v8, v9, v10;
static float d1, d2, d3, d4, d5, d6, d7, d8, d9, d10;

int main(void)
{
    UNITY_BEGIN();

    RUN_TEST(test_merge_sort_empty_arrays);
    RUN_TEST(test_merge_sort_equal_int);
    RUN_TEST(test_merge_sort_equal_string);
    RUN_TEST(test_merge_sort_equal_float);
    RUN_TEST(test_merge_sort_two_records_arrays);

    RUN_TEST(test_quick_sort_empty_arrays);
    RUN_TEST(test_quick_sort_equal_int);
    RUN_TEST(test_quick_sort_equal_string);
    RUN_TEST(test_quick_sort_equal_float);
    RUN_TEST(test_quick_sort_two_records_arrays);

    return UNITY_END();
}

// Test the merge sort for empty arrays
void test_merge_sort_empty_arrays()
{
    Record a[] = {};
    Record b[] = {};
    int res = 1;

    merge_sort(a, 0, sizeof(Record), compare_string);
    for (int i = 0; i < 0; i++)
    {
        if (a[i].fieldFloat == b[i].fieldFloat &&
            strcmp(a[i].fieldString, b[i].fieldString) == 0 &&
            a[i].fieldInt == b[i].fieldInt && res)
            res = 1;
        else
            res = 0;
    }
    assert(res);

    merge_sort(a, 0, sizeof(Record), compare_int);
    for (int i = 0; i < 0; i++)
    {
        if (a[i].fieldFloat == b[i].fieldFloat &&
            strcmp(a[i].fieldString, b[i].fieldString) == 0 &&
            a[i].fieldInt == b[i].fieldInt && res)
            res = 1;
        else
            res = 0;
    }
    assert(res);

    merge_sort(a, 0, sizeof(Record), compare_float);
    for (int i = 0; i < 0; i++)
    {
        if (a[i].fieldFloat == b[i].fieldFloat &&
            strcmp(a[i].fieldString, b[i].fieldString) == 0 &&
            a[i].fieldInt == b[i].fieldInt && res)
            res = 1;
        else
            res = 0;
    }
    assert(res);
} // test_merge_sort_empty_arrays

// Test the merge sort two equals int
void test_merge_sort_equal_int()
{
    int *input[] = {&v1};
    int *expected[] = {&v1};

    merge_sort((void **) input, 0, sizeof(int), compare_int);

    TEST_ASSERT_EQUAL_PTR_ARRAY(expected, input, 1);
} // test_merge_sort_equal_int

// Test the merge sort two equals strings
void test_merge_sort_equal_string()
{
    char *input[] = {"a"};
    char *expected[] = {"a"};

    merge_sort((void **) input, 0, sizeof(char), compare_string);

    TEST_ASSERT_EQUAL_PTR_ARRAY(expected, input, 1);
} // test_merge_sort_equal_string

// Test the merge sort two equals floats
void test_merge_sort_equal_float()
{
    float *input[] = {&d1};
    float *expected[] = {&d1};

    merge_sort((void **) input, 0, sizeof(float), compare_string);

    TEST_ASSERT_EQUAL_PTR_ARRAY(expected, input, 1);
} // test_merge_sort_equal_float

// Test the merge sort for two records
void test_merge_sort_two_records_arrays()
{
    Record a[] = {{1, "ciao", 10, 3.14}, {2, "ciao", 20, 3.14}};

    Record original_a[sizeof(a) / sizeof(a[0])];
    memcpy(original_a, a, sizeof(a));

    merge_sort(a, sizeof(a) / sizeof(a[0]), sizeof(Record), compare_int);

    for (size_t i = 0; i < sizeof(a) / sizeof(a[0]) - 1; i++)
    {
        TEST_ASSERT_TRUE(a[i].fieldString <= a[i + 1].fieldString);
    }
} // test_merge_sort_two_records_arrays


// Test the quick sort for empty arrays
void test_quick_sort_empty_arrays()
{
    Record a[] = {};
    Record b[] = {};
    int res = 1;

    quick_sort(a, 0, sizeof(Record), compare_string);
    for (int i = 0; i < 0; i++)
    {
        if (a[i].fieldFloat == b[i].fieldFloat &&
            strcmp(a[i].fieldString, b[i].fieldString) == 0 &&
            a[i].fieldInt == b[i].fieldInt && res)
            res = 1;
        else
            res = 0;
    }
    assert(res);

    quick_sort(a, 0, sizeof(Record), compare_int);
    for (int i = 0; i < 0; i++)
    {
        if (a[i].fieldFloat == b[i].fieldFloat &&
            strcmp(a[i].fieldString, b[i].fieldString) == 0 &&
            a[i].fieldInt == b[i].fieldInt && res)
            res = 1;
        else
            res = 0;
    }
    assert(res);

    quick_sort(a, 0, sizeof(Record), compare_float);
    for (int i = 0; i < 0; i++)
    {
        if (a[i].fieldFloat == b[i].fieldFloat &&
            strcmp(a[i].fieldString, b[i].fieldString) == 0 &&
            a[i].fieldInt == b[i].fieldInt && res)
            res = 1;
        else
            res = 0;
    }
    assert(res);
} // test_quick_sort_empty_arrays

// Test the quick sort for arrays of two equals elements
void test_quick_sort_equal_int()
{
    int *input[] = {&v1};
    int *expected[] = {&v1};

    quick_sort((void **) input, 0, sizeof(int), compare_int);

    TEST_ASSERT_EQUAL_PTR_ARRAY(expected, input, 1);
} // test_quick_sort_equal_int

// Test the merge sort two equals strings
void test_quick_sort_equal_string()
{
    char *input[] = {"a"};
    char *expected[] = {"a"};

    quick_sort((void **) input, 0, sizeof(char), compare_string);

    TEST_ASSERT_EQUAL_PTR_ARRAY(expected, input, 1);
} // test_quick_sort_equal_string

// Test the merge sort two equals floats
void test_quick_sort_equal_float()
{
    float *input[] = {&d1};
    float *expected[] = {&d1};

    quick_sort((void **) input, 0, sizeof(float), compare_string);

    TEST_ASSERT_EQUAL_PTR_ARRAY(expected, input, 1);
} // test_quick_sort_equal_float

// Test the quick sort for two records
void test_quick_sort_two_records_arrays()
{
    Record a[] = {{1, "ciao", 10, 3.14}, {2, "ciao", 20, 3.14}};

    Record original_a[sizeof(a) / sizeof(a[0])];
    memcpy(original_a, a, sizeof(a));

    quick_sort(a, sizeof(a) / sizeof(a[0]), sizeof(Record), compare_string);
    for (size_t i = 0; i < sizeof(a) / sizeof(a[0]) - 1; i++)
    {
        TEST_ASSERT_TRUE(a[i].fieldString <= a[i + 1].fieldString);
    }

    quick_sort(a, sizeof(a) / sizeof(a[0]), sizeof(Record), compare_int);
    for (size_t i = 0; i < sizeof(a) / sizeof(a[0]) - 1; i++)
    {
        TEST_ASSERT_TRUE(a[i].fieldString <= a[i + 1].fieldString);
    }

    quick_sort(a, sizeof(a) / sizeof(a[0]), sizeof(Record), compare_float);
    for (size_t i = 0; i < sizeof(a) / sizeof(a[0]) - 1; i++)
    {
        TEST_ASSERT_TRUE(a[i].fieldString <= a[i + 1].fieldString);
    }
} // test_quick_sort_two_records_arrays


void setUp(void)
{
    v1 = 1;
    v2 = 2;
    v3 = 3;
    v4 = 4;
    v5 = 5;
    v6 = 6;
    v7 = 7;
    v8 = 8;
    v9 = 9;
    v10 = 10;

    d1 = 1.0;
    d2 = 2.0;
    d3 = 3.0;
    d4 = 4.0;
    d5 = 5.0;
    d6 = 6.0;
    d7 = 7.0;
    d8 = 8.0;
    d9 = 9.0;
    d10 = 10.0;
} // setup


// Dummy teardown function
void tearDown(void)
{
}