#include "edit_distance.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

/**
 * Calculate the distance between two strings in a recursive way
 */
int edit_distance(char *s1, char *s2)
{
    if (s1 == NULL || s2 == NULL) {
        return -1;
    }

    int dnop, dcanc, dins = 0;
    if (*s1 == '\0') {
        return strlen(s2);
    }
    if (*s2 == '\0') {
        return strlen(s1);
    }

    if (s1[0] == s2[0]) {
        dnop = edit_distance(s1 + 1, s2 + 1);
    }
    else {
        dnop = __INT_MAX__;
    }

    dcanc = 1 + edit_distance(s1, s2 + 1);
    dins = 1 + edit_distance(s1 + 1, s2);
    return minimum(dnop, dcanc, dins);
} // edit_distance

/**
 * Find the minimun value between three  values
 */
int minimum(int x, int y, int z)
{
    int min = __INT_MAX__;
    if (x < min) {
        min = x;
    }
    if (y < min) {
        min = y;
    }
    if (z < min) {
        min = z;
    }
    return min;
} // minimum

/**
 * Dynamic version of the edit_distance. It setup the structure for the actual implementation
 */
int edit_distance_dynamic(char *s1, char *s2)
{
    if (s1 == NULL || s2 == NULL) {
        return -1;
    }
    int len1 = strlen(s1);
    int len2 = strlen(s2);

    int memo[MAX_LEN][MAX_LEN];
    for (int i = 0; i <= len1; i++) {
        for (int j = 0; j <= len2; j++) {
            memo[i][j] = UNDEFINED;
        }
    }

    return edit_distance_dynamic_recursuve(s1, s2, len1, len2, memo);
} // edit_distance_dynamic

/**
 * Implementation of the dynamic version of edit_distance
 */
int edit_distance_dynamic_recursuve(char *s1, char *s2, int len1, int len2, int memo[MAX_LEN][MAX_LEN])
{
    if (len1 == 0) return len2;
    if (len2 == 0) return len1;

    if (memo[len1][len2] != UNDEFINED)
        return memo[len1][len2];

    if (s1[0] == s2[0])
        return memo[len1][len2] = edit_distance_dynamic_recursuve(s1 + 1, s2 + 1, len1 - 1, len2 - 1, memo);
    else {
        int d_no_op = __INT_MAX__;
        if (s1[0] == s2[0])
        {
            d_no_op = edit_distance_dynamic_recursuve(s1 + 1, s2 + 1, len1 - 1, len2 - 1, memo);
        }
        int d_canc = 1 + edit_distance_dynamic_recursuve(s1, s2 + 1, len1, len2 - 1, memo);
        int d_ins = 1 + edit_distance_dynamic_recursuve(s1 + 1, s2, len1 - 1, len2, memo);
        return memo[len1][len2] = minimum(d_no_op, d_canc, d_ins);
    }
} // edit_distance_dynamic_recursuve

/**
 * Remove punctuation signs from a string,
 * to make sure that two equal strings are correctly calculated
 */
void remove_punctuation(char *str) {
    for (int i = 0; str[i] != '\0'; i++) {
        if (ispunct(str[i])) {
            for (int j = i; str[j] != '\0'; j++) {
                str[j] = str[j + 1];
            }
            i--;
        }
    }
} // remove_punctuation
