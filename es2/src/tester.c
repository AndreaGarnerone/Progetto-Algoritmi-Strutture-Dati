#include "unity.h"
#include "edit_distance.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void test_edit_distance_empty();
void test_edit_distance_two_empty();
void test_edit_distance_two_equals();
void test_edit_distance_different();

void test_edit_distance_dynamic_null();
void test_edit_distance_dynamic_empty();
void test_edit_distance_dynamic_two_empty();
void test_edit_distance_dynamic_two_equals();
void test_edit_distance_dynamic_different();

int main()
{
    UNITY_BEGIN();

    RUN_TEST(test_edit_distance_empty);
    RUN_TEST(test_edit_distance_empty);
    RUN_TEST(test_edit_distance_two_equals);
    RUN_TEST(test_edit_distance_different);

    RUN_TEST(test_edit_distance_dynamic_null);
    RUN_TEST(test_edit_distance_dynamic_empty);
    RUN_TEST(test_edit_distance_dynamic_two_empty);
    RUN_TEST(test_edit_distance_dynamic_two_equals);
    RUN_TEST(test_edit_distance_dynamic_different);

    return UNITY_END();
}

void test_edit_distance_empty()
{
    char s1[] = "";
    char s2[] = "casa";
    int result;

    result = edit_distance(s1, s2);

    TEST_ASSERT_EQUAL(4, result);
}

void test_edit_distance_two_empty()
{
    char s1[] = "";
    char s2[] = "";
    int result;

    result = edit_distance(s1, s2);

    TEST_ASSERT_EQUAL(0, result);
}

void test_edit_distance_two_equals()
{
    char s1[] = "pioppo";
    char s2[] = "pioppo";
    int result;

    result = edit_distance(s1, s2);

    TEST_ASSERT_EQUAL(0, result);
}

void test_edit_distance_different()
{
    char s1[] = "tassa";
    char s2[] = "passato";
    int result;

    result = edit_distance(s1, s2);

    TEST_ASSERT_EQUAL(4, result);
}

void test_edit_distance_dynamic_null() {
    char *s1 = NULL;
    char *s2 = NULL;

    TEST_ASSERT_EQUAL(-1, edit_distance_dynamic(s1, s2));
}

void test_edit_distance_dynamic_empty()
{
    char s1[] = "";
    char s2[] = "casa";

    TEST_ASSERT_EQUAL(4, edit_distance_dynamic(s1, s2));
}

void test_edit_distance_dynamic_two_empty()
{
    char s1[] = "";
    char s2[] = "";

    TEST_ASSERT_EQUAL(0, edit_distance_dynamic(s1, s2));
}

void test_edit_distance_dynamic_two_equals()
{
    char s1[] = "pioppo";
    char s2[] = "pioppo";

    TEST_ASSERT_EQUAL(0, edit_distance_dynamic(s1, s2));
}

void test_edit_distance_dynamic_different()
{
    char s1[] = "tassa";
    char s2[] = "passato";

    TEST_ASSERT_EQUAL(4, edit_distance_dynamic(s1, s2));
}

// Dummy setup function
void setUp(void)
{
}

// Dummy teardown function
void tearDown(void)
{
}