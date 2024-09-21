#ifndef EDIT_DISTANCE_H
#define EDIT_DISTANCE_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_LEN 1000
#define UNDEFINED -1

int edit_distance(char *s1, char *s2);
int minimum(int a, int b, int c);
int edit_distance_dynamic(char *s1, char *s2);
int edit_distance_dynamic_recursuve(char *s1, char *s2, int len1, int len2, int memo[MAX_LEN][MAX_LEN]);
void remove_punctuation(char *str);

#endif /* EDIT_DISTANCE_H */