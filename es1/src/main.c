#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "algo_lib.h"

#define N_ELEMENTS 200
#define MAX_RECORD_LENGTH 200
#define AUTO 1

void sort_records(FILE *infile, FILE *outfile, size_t field, size_t algo);

int main(int argc, char const *argv[])
{
    char input_file_name[100];
    char field_input[10];
    char algo_input[10];
    size_t field_index, algo;
    time_t start_time, current_time;
    double elapsed_time = 0.0;

    printf("Enter field of interest (1 for int, 2 for string, 3 for float):");
    fgets(field_input, sizeof(field_input), stdin);
    field_input[strcspn(field_input, "\n")] = 0;
    field_index = atoi(field_input);

    if (AUTO == 0)
    {
        printf("Select an algorithm (1 for merge sort, 2 for quick sort):");
        fgets(algo_input, sizeof(algo_input), stdin);
        algo_input[strcspn(algo_input, "\n")] = 0;
        algo = atoi(algo_input);
    }
    else
    {
        switch (field_index)
        {
        case 1:
            algo = 1;
            break;

        case 2:
            algo = 2;
            break;
        case 3:
            algo = 1;
            break;

        default:
            break;
        }
    }

    FILE *input_file = fopen("records.csv", "r");
    if (input_file == NULL)
    {
        printf("Error: Unable to open input file.\n");
        return EXIT_FAILURE;
    }

    FILE *output_file = fopen("output.csv", "w");
    if (output_file == NULL)
    {
        printf("Error: Unable to open output file.\n");
        fclose(input_file);
        return EXIT_FAILURE;
    }

    time(&start_time);
    sort_records(input_file, output_file, field_index, algo);
    time(&current_time);
    elapsed_time = difftime(current_time, start_time);
    printf("Task completed in %f seconds\n", elapsed_time);
    fclose(input_file);
    fclose(output_file);

    return EXIT_SUCCESS;
}

void sort_records(FILE *infile, FILE *outfile, size_t field_index, size_t algo)
{
    char *line = NULL;
    size_t read;
    size_t size = 0;
    size_t l = 0;
    Record *listRecord = NULL;

    while ((read = getline(&line, &l, infile)) != -1)
    {
        size++;
    }

    fseek(infile, 0, SEEK_SET);

    listRecord = malloc(sizeof(Record) * size);
    if (listRecord == NULL)
    {
        printf("Memory allocation failed\n");
        exit(EXIT_FAILURE);
    }

    for (size_t i = 0; i < size; i++)
    {
        read = getline(&line, &l, infile);
        if (read == -1)
        {
            printf("Error reading file\n");
            exit(EXIT_FAILURE);
        }

        char *token = strtok(line, ",");
        listRecord[i].id = atoi(token);
        token = strtok(NULL, ",");
        listRecord[i].fieldString = strdup(token);
        token = strtok(NULL, ",");
        listRecord[i].fieldInt = atoi(token);
        token = strtok(NULL, ",");
        listRecord[i].fieldFloat = atof(token);
    }

    int (*compare_func)(const void *, const void *);
    switch (field_index)
    {
    case 1:
        compare_func = compare_int;
        break;
    case 2:
        compare_func = compare_string;
        break;
    case 3:
        compare_func = compare_float;
        break;
    default:
        printf("Invalid field index\n");
        exit(EXIT_FAILURE);
    }

    switch (algo)
    {
    case 1:
        merge_sort(listRecord, size, sizeof(Record), compare_func);
        break;
    case 2:
        quick_sort(listRecord, size, sizeof(Record), compare_func);
        break;
    default:
        printf("Invalid algorithm\n");
        exit(EXIT_FAILURE);
    }

    for (size_t i = 0; i < size; i++)
    {
        // printf("%s\n", listRecord[i].fieldString);
        fprintf(outfile, "%d,%s,%d,%f\n", listRecord[i].id, listRecord[i].fieldString, listRecord[i].fieldInt, listRecord[i].fieldFloat);
    }

    for (size_t i = 0; i < size; i++)
    {
        free(listRecord[i].fieldString);
    }

    free(listRecord);
}