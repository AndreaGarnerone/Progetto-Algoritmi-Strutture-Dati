#include "edit_distance.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <ctype.h>

#define MAX_WORD_LENGTH 100

/**
 * Free the allocated memory used for the dictionary array.
 */
void free_dictionary(char **dictionary, int word_count) {
    for (int i = 0; i < word_count; i++) {
        free(dictionary[i]);
    }
    free(dictionary);
}

/**
 * Compare the the words taken from an input file with the ones in the dictionary,
 * and write the output in the output file.
 */
void process_input(FILE *input_file, FILE *output_file, char **dictionary, int word_count, int w) {
    char current_word[MAX_WORD_LENGTH];
    while (fscanf(input_file, "%99s", current_word) == 1) {
        remove_punctuation(current_word);
        fprintf(output_file, "%s: ", current_word);

        for (int i = 0; i < word_count; i++) {
            int current_edit_value = edit_distance_dynamic(current_word, dictionary[i]);
            if (current_edit_value <= w) {
                fprintf(output_file, "%s; ", dictionary[i]);
            }
        }
        fprintf(output_file, "\n");
    }
}

/**
 * Read the words from the dictionary file and store them into an array dinamically allocated
 */
char **read_dictionary(const char *filename, int *word_count) {
    FILE *dictionary_file = fopen(filename, "r");
    if (dictionary_file == NULL) {
        printf("Error: Unable to open dictionary file.\n");
        return NULL;
    }

    char **dictionary = NULL;
    int capacity = 0;
    *word_count = 0;

    char word[MAX_WORD_LENGTH];
    while (fscanf(dictionary_file, "%99s", word) == 1) {
        char *new_word = strdup(word);
        if (new_word == NULL) {
            printf("Error: Memory allocation failed.\n");
            fclose(dictionary_file);
            free_dictionary(dictionary, *word_count);
            return NULL;
        }

        if (*word_count >= capacity) {
            int new_capacity = (capacity == 0) ? 1 : capacity * 2;
            char **temp = realloc(dictionary, sizeof(char *) * new_capacity);
            if (temp == NULL) {
                printf("Error: Memory reallocation failed.\n");
                fclose(dictionary_file);
                free(new_word);
                free_dictionary(dictionary, *word_count);
                return NULL;
            }
            dictionary = temp;
            capacity = new_capacity;
        }

        dictionary[*word_count] = new_word;
        (*word_count)++;
    }
    fclose(dictionary_file);

    return dictionary;
}

int main() {
    FILE *dictionary_file = fopen("dictionary.txt", "r");
    if (dictionary_file == NULL) {
        printf("Error: Unable to open dictionary file.\n");
        return EXIT_FAILURE;
    }

    FILE *input_file = fopen("correctme.txt", "r");
    if (input_file == NULL) {
        printf("Error: Unable to open input file.\n");
        fclose(dictionary_file);
        return EXIT_FAILURE;
    }

    FILE *output_file = fopen("output.txt", "w");
    if (output_file == NULL) {
        printf("Error: Unable to open output file.\n");
        fclose(dictionary_file);
        fclose(input_file);
        return EXIT_FAILURE;
    }

    char **dictionary = NULL;
    int word_count = 0;
    int w;

    printf("Enter w factor:");
    scanf("%d", &w);

    dictionary = read_dictionary("dictionary.txt", &word_count);
    if (dictionary == NULL) {
        fclose(dictionary_file);
        fclose(input_file);
        fclose(output_file);
        return EXIT_FAILURE;
    }

    process_input(input_file, output_file, dictionary, word_count, w);

    free_dictionary(dictionary, word_count);

    fclose(dictionary_file);
    fclose(input_file);
    fclose(output_file);

    return 0;
}
